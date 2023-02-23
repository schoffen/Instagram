package com.example.instagram.profile.data

import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Post
import com.example.instagram.common.model.User

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {
    fun clearCache() {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }
    fun fetchUserProfile(uuid: String?, callback: RequestCallback<Pair<User, Boolean?>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userID = uuid ?: localDataSource.fetchSession()

        val dataSource = dataSourceFactory.createFromUser(uuid)

        dataSource.fetchUserProfile(userID, object :  RequestCallback<Pair<User, Boolean?>>{
            override fun onSuccess(data: Pair<User, Boolean?>) {
                if (uuid == null) {
                    localDataSource.putUser(data)
                }
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
            }

            override fun onComplete() {
            }
        })

        callback.onComplete()
    }

    fun fetchUserPosts(uuid: String?, callback: RequestCallback<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userID = uuid ?: localDataSource.fetchSession()

        val dataSource = dataSourceFactory.createFromPosts(uuid)

        dataSource.fetchUserPosts(userID, object :  RequestCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                if (uuid == null) {
                    localDataSource.putPosts(data)
                }
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
            }

            override fun onComplete() {
            }
        })

        callback.onComplete()
    }

    fun followUser(uuid: String?, follow: Boolean, callback: RequestCallback<Boolean>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userID = uuid ?: localDataSource.fetchSession()
        val dataSource = dataSourceFactory.createRemoteDataSource()

        dataSource.followUser(userID, follow, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }
}