package com.example.instagram.register

import androidx.annotation.StringRes
import com.example.instagram.common.base.BasePresenter
import com.example.instagram.common.base.BaseView

interface RegisterNameAndPassword {
    interface Presenter: BasePresenter {
        fun create(email: String, name: String, password: String, confirm: String)
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayNameFailure(@StringRes nameError: Int?)
        fun displayPasswordFailure(@StringRes passError: Int?)
        fun onCreateFailure(message: String)
        fun onCreateSuccess(name: String)
    }
}