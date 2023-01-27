package com.example.instagram.register.data

import android.net.Uri

class RegisterRepository(private val dataSource: RegisterDatasource) {
    fun create(email: String, callback: RegisterCallback) {
        dataSource.create(email, callback)
    }

    fun create(name: String, password: String, email: String, callback: RegisterCallback) {
        dataSource.create(name, email, password, callback)
    }

    fun updateUser(photoUri: Uri, callback: RegisterCallback) {
        dataSource.updateUser(photoUri, callback)
    }
}