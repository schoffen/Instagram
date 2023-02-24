package com.example.instagram.search.data

import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.User
import com.example.instagram.common.model.UserAuth

interface SearchDataSource {
    fun fetchUsers(name: String, callback: RequestCallback<List<User>>)
}