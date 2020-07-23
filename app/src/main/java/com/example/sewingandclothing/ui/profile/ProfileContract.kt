package com.example.sewingandclothing.ui.profile

import com.example.sewingandclothing.ui.base.BaseContract

class ProfileContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun logout()
    }

    interface View{

    }
}