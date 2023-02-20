package com.example.instagram.search.data

import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Post
import com.example.instagram.common.model.UserAuth
import java.util.UUID

class SearchRepository(private val dataSource: SearchDataSource) {

    fun fetchUsers(name: String, callback: RequestCallback<List<UserAuth>>) {

        dataSource.fetchUsers(name, object :  RequestCallback<List<UserAuth>>{
            override fun onSuccess(data: List<UserAuth>) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
            }

            override fun onComplete() {
            }
        })

        callback.onComplete()
    }
}