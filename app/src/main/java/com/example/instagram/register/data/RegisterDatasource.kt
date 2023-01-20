package com.example.instagram.register.data

interface RegisterDatasource {
    fun create(email: String, callback: RegisterCallback)
    fun create(name: String, email: String, password: String, callback: RegisterCallback)
}