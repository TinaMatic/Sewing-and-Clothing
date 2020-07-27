package com.example.sewingandclothing.ui.main

import android.view.View
import com.example.sewingandclothing.ui.base.BaseContract
import io.reactivex.Observable

class MainContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun isUserOrSeamstress(): Observable<String>
    }

    interface View{

    }
}