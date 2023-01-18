package com.example.instagram.register

import androidx.annotation.StringRes
import com.example.instagram.common.base.BasePresenter
import com.example.instagram.common.base.BaseView

interface RegisterEmail {

    interface Presenter: BasePresenter {

    }

    interface View: BaseView<Presenter> {
        fun displayEmailFailure(@StringRes emailError: Int)
    }

}