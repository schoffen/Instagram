package com.example.instagram.post

import android.net.Uri
import com.example.instagram.common.base.BasePresenter
import com.example.instagram.common.base.BaseView
import com.example.instagram.common.model.PhotoPair

interface Post {
    interface Presenter: BasePresenter {
        fun selectUri(uri: Uri)
        fun getSelectedUri(): Uri?
        fun fetchPictures()
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPictures()
        fun displayFullPictures(pictures: List<PhotoPair>)
    }
}