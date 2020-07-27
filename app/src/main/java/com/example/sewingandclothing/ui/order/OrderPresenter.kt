package com.example.sewingandclothing.ui.order

import com.example.sewingandclothing.data.UserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrderPresenter @Inject constructor(private val userRepository: UserRepository) : OrderContract.Presenter {

    lateinit var view: OrderContract.View

    private var compositeDisposable = CompositeDisposable()

    override fun isUserOrSeamstress(){
        compositeDisposable.add(userRepository.isUserOrSeamstress()
            .doOnNext { view.showProgressBar(true) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if(it.equals("Seamstress")){
                    view.popFragmentFromStack()
                    view.showProgressBar(false)
                }
            },{}))
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun attach(view: OrderContract.View) {
        this.view = view
    }
}