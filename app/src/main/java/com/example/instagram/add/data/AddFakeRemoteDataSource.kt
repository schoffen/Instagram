package com.example.instagram.add.data

import android.net.Uri
import android.os.Looper
import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Database
import com.example.instagram.common.model.Post
import java.util.*

class AddFakeRemoteDataSource : AddDataSource {
    override fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            var posts = Database.posts[userUUID]

            if (posts == null) {
                posts = mutableSetOf()
                Database.posts[userUUID] = posts
            }

            val post = Post(UUID.randomUUID().toString(), null, caption, System.currentTimeMillis(), null)

            posts.add(post)

            var followers = Database.followers[userUUID]

            if (followers == null) {
                followers = mutableSetOf()
                Database.followers[userUUID] = followers
            } else {
                for (follower in followers) {
                    Database.feeds[follower]?.add(post)
                }

                Database.feeds[userUUID]?.add(post)
            }

            callback.onSuccess(true)
        }, 1000)
    }
}