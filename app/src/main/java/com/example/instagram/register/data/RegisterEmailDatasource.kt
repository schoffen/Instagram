package com.example.instagram.register.data

interface RegisterEmailDatasource {
    fun create(email: String, callback: RegisterEmailCallback)
}