package com.example.photoapplication

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photoapplication.adapter.PhotoListAdapter
import com.example.photoapplication.data.vos.PhotosVO
import com.example.photoapplication.mvp.presenter.PhotoListPresenter
import com.example.photoapplication.mvp.views.PhotoListView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),PhotoListView {
    override fun displayPhotoList(photoList: List<PhotosVO>) {
        mAdapter.setNewData(photoList.toMutableList())
    }

    override fun displayError(errorMessage: String) {
        Snackbar.make(rootview, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun navigateToDetail(eventId: String) {
        startActivity(DetailActivity.newIntent(applicationContext, eventId))
    }

    private lateinit var mPresenter: PhotoListPresenter
    private lateinit var mAdapter: PhotoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = ViewModelProviders.of(this).get(PhotoListPresenter::class.java)
        mPresenter.initPresenter(this)


        mAdapter = PhotoListAdapter(mPresenter)

        with(rcPhoto) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        mPresenter.onUiReady(this)
    }

    }

