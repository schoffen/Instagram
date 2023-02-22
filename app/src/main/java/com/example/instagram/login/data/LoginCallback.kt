package com.example.instagram.login.data

import com.example.instagram.common.model.UserAuth

interface LoginCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}