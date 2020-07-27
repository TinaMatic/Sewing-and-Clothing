package com.example.sewingandclothing.ui.main

import com.example.sewingandclothing.data.UserRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(private val userRepository: UserRepository): MainContract.Presenter {

    private lateinit var view: MainContract.View

    private var compositeDisposable = CompositeDisposable()

    override fun isUserOrSeamstress(): Observable<String> {
        return userRepository.isUserOrSeamstress()
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }
}