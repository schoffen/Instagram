package com.example.instagram.search

import com.example.instagram.common.base.BasePresenter
import com.example.instagram.common.base.BaseView

interface Search {
    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

    }
}