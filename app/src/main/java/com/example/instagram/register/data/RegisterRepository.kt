package com.example.instagram.register.data

class RegisterRepository(private val dataSource: RegisterDatasource) {
    fun create(email: String, callback: RegisterCallback) {
        dataSource.create(email, callback)
    }

    fun create(name: String, password: String, email: String, callback: RegisterCallback) {
        dataSource.create(name, email, password, callback)
    }
}