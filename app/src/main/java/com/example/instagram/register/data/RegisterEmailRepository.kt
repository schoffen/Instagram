package com.example.instagram.register.data

class RegisterEmailRepository(private val dataSource: RegisterEmailDatasource) {
    fun create(email: String, callback: RegisterEmailCallback) {
        dataSource.create(email, callback)
    }
}