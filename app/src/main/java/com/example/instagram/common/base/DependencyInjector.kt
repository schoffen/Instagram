package com.example.instagram.common.base

import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.register.data.FakeRegisterDatasource
import com.example.instagram.register.data.RegisterRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterRepository{
        return RegisterRepository(FakeRegisterDatasource())
    }
}