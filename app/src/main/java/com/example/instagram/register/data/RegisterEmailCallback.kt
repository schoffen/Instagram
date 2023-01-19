package com.example.instagram.register.data

import com.example.instagram.common.model.UserAuth

interface RegisterEmailCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}