package com.example.instagram.post.presenter

import android.net.Uri
import android.util.Patterns
import com.example.instagram.R
import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Database
import com.example.instagram.common.model.UserAuth
import com.example.instagram.post.Post
import com.example.instagram.post.data.PostRepository
import com.example.instagram.profile.Profile
import com.example.instagram.profile.data.ProfileRepository
import com.example.instagram.register.RegisterEmail
import com.example.instagram.register.data.RegisterCallback
import com.example.instagram.register.data.RegisterRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostPresenter(
    private var view: Post.View?,
    private val repository: PostRepository
) : Post.Presenter, CoroutineScope {

    private var uri: Uri? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun selectUri(uri: Uri) {
        this.uri = uri
    }

    override fun getSelectedUri(): Uri? {
        return uri
    }

    override fun fetchPictures() {
        view?.showProgress(true)

        launch {
            val pictures = repository.fetchPictures()

            withContext(Dispatchers.Main) {
                if (pictures.isEmpty()) {
                    view?.displayEmptyPictures()
                } else {
                    view?.displayFullPictures(pictures)
                }
                view?.showProgress(false)
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        view = null
    }
}