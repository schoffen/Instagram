package com.example.instagram.common.base

import android.content.Context
import com.example.instagram.add.data.AddFakeRemoteDataSource
import com.example.instagram.add.data.AddLocalDataSource
import com.example.instagram.add.data.AddRepository
import com.example.instagram.home.data.FeedListMemoryCache
import com.example.instagram.home.data.HomeDataSourceFactory
import com.example.instagram.home.data.HomeRepository
import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.FireLoginDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.post.data.PostLocalDataSource
import com.example.instagram.post.data.PostRepository
import com.example.instagram.profile.data.*
import com.example.instagram.register.data.FakeRegisterDatasource
import com.example.instagram.register.data.FireRegisterDataSource
import com.example.instagram.register.data.RegisterRepository
import com.example.instagram.search.data.SearchFakeRemoteDataSource
import com.example.instagram.search.data.SearchRepository
import com.example.instagram.splash.data.FakeLocalDataSource
import com.example.instagram.splash.data.FireSplashDataSource
import com.example.instagram.splash.data.SplashRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FireLoginDataSource())
    }

    fun registerEmailRepository() : RegisterRepository{
        return RegisterRepository(FireRegisterDataSource())
    }

    fun splashRepository() : SplashRepository {
        return SplashRepository(FireSplashDataSource())
    }

    fun searchRepository() : SearchRepository {
        return SearchRepository(SearchFakeRemoteDataSource())
    }

    fun profileRepository() : ProfileRepository {
        return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache))
    }

    fun homeRepository() : HomeRepository {
        return HomeRepository(HomeDataSourceFactory(FeedListMemoryCache))
    }

    fun addRepository() : AddRepository {
        return AddRepository(AddFakeRemoteDataSource(), AddLocalDataSource())
    }

    fun postRepository(context: Context) : PostRepository {
        return PostRepository(PostLocalDataSource(context))
    }
}