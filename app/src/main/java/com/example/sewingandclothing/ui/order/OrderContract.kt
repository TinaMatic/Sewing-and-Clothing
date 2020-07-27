package com.example.sewingandclothing.ui.order

import android.view.View
import com.example.sewingandclothing.ui.base.BaseContract
import io.reactivex.Observable

class OrderContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun isUserOrSeamstress()
    }

    interface View{
        fun popFragmentFromStack()
        fun showProgressBar(show: Boolean)
    }
}