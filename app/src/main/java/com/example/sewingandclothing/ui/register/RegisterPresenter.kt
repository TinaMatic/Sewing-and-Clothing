package com.example.sewingandclothing.ui.register

import android.util.Log
import com.example.sewingandclothing.data.LoginRegisterRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterPresenter @Inject constructor(private val loginRegisterRepository: LoginRegisterRepository) : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View

    private var compositeDisposable = CompositeDisposable()

    override fun createAccount(email: String, password: String, username: String, userVsSeamstress: String) {
        compositeDisposable.add(loginRegisterRepository.createAccount(email, password, username, userVsSeamstress)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if (it) {
                    view.loginIntoApp()
                } else {
                    view.showErrorMessage()
                }
            },{
                Log.i("createAccount Register", it.message!!)
            }))
    }

    override fun destroy() {
        compositeDisposable.dispose()
    }

    override fun attach(view: RegisterContract.View) {
        this.view = view
    }
}