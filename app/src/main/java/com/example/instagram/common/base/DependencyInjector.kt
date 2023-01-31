package com.example.instagram.common.base

import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.profile.data.FakeProfileRemoteDataSource
import com.example.instagram.profile.data.ProfileRepository
import com.example.instagram.register.data.FakeRegisterDatasource
import com.example.instagram.register.data.RegisterRepository
import com.example.instagram.splash.data.FakeLocalDataSource
import com.example.instagram.splash.data.SplashRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterRepository{
        return RegisterRepository(FakeRegisterDatasource())
    }

    fun splashRepository() : SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }

    fun profileRepository() : ProfileRepository {
        return ProfileRepository(FakeProfileRemoteDataSource())
    }
}