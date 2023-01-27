package com.example.instagram.register.data

import android.net.Uri

interface RegisterDatasource {
    fun create(email: String, callback: RegisterCallback)
    fun create(name: String, email: String, password: String, callback: RegisterCallback)
    fun updateUser(photoUri: Uri, callback: RegisterCallback)
}