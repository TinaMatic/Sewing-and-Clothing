package com.example.sewingandclothing.ui.register

import com.example.sewingandclothing.ui.base.BaseContract

class RegisterContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun createAccount(email: String, password: String, username: String, userVsSeamstress: String)
    }

    interface View{
        fun loginIntoApp()
        fun showErrorMessage()
    }
}