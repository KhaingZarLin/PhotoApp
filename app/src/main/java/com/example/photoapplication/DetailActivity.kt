package com.example.photoapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import coil.api.load
import com.example.photoapplication.adapter.PhotoListAdapter
import com.example.photoapplication.data.vos.PhotosVO
import com.example.photoapplication.fragment.BottomSheetFragment
import com.example.photoapplication.mvp.presenter.PhotoDetailListPresenter
import com.example.photoapplication.mvp.presenter.PhotoListPresenter
import com.example.photoapplication.mvp.views.PhotoDetailView
import com.example.photoapplication.mvp.views.PhotoListView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class DetailActivity : BaseActivity(),PhotoDetailView,PhotoListView {

    private lateinit var photoDetailPresenter: PhotoDetailListPresenter
    private lateinit var photoListPresenter: PhotoListPresenter
    private lateinit var photoItemAdapter: PhotoListAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun displayPhotoDetail(photoData: PhotosVO) {
        bindData(photoData)
    }

    override fun errorMessage(errorMessage: String) {
        Snackbar.make(rootview, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun displayPhotoList(photoList: List<PhotosVO>) {
        photoItemAdapter.setNewData(photoList.toMutableList())
    }

    override fun displayError(errorMessage: String) {
        Snackbar.make(rootview, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun navigateToDetail(photoId: String) {
        startActivity(DetailActivity.newIntent(applicationContext, photoId))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        photoDetailPresenter = PhotoDetailListPresenter()
        photoDetailPresenter.initPresenter(this)

        photoListPresenter = PhotoListPresenter()
        photoListPresenter.initPresenter(this)

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(@NonNull view: View, i: Int) {
                when (i) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        bottomSheetBehavior.setPeekHeight(300)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(@NonNull view: View, v: Float) {

            }
        })

        photoItemAdapter = PhotoListAdapter(photoListPresenter)
        with(rv_related_photos){
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
                2,
                androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
            )
            adapter = photoItemAdapter
        }

        val photoId = intent.getStringExtra(DetailActivity.EXTRA_PHOTO_ID)
        photoDetailPresenter.onUiReady(this, photoId!!)
        photoListPresenter.onUiReady(this)


    }
    companion object {
        const val EXTRA_PHOTO_ID = "photo_id"

        fun newIntent(context: Context, photo_id: String): Intent {

            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_PHOTO_ID, photo_id)
            }
        }
    }
    fun bindData(data: PhotosVO){
        photo_detail_iv.load(data.urls.regular)
        user_iv.load(data.userVO.profileImageVO!!.medium)
        user_tv.text = data.userVO.name
        user_account_tv.text = "@" + data.userVO.twitter_username
    }
}

