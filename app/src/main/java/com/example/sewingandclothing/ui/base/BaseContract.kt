package com.example.sewingandclothing.ui.base

class BaseContract {

    interface BasePresenter<in T>{
        fun destroy()
        fun attach(view: T)
    }
}