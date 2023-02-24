package com.example.instagram.search

import com.example.instagram.common.base.BasePresenter
import com.example.instagram.common.base.BaseView
import com.example.instagram.common.model.User
import com.example.instagram.common.model.UserAuth

interface Search {
    interface Presenter : BasePresenter {
        fun fetchUsers(name: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enable: Boolean)
        fun displayFullUsers(users: List<User>)
        fun displayEmptyUsers()
    }
}