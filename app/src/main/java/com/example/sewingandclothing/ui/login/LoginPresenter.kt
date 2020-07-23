package com.example.sewingandclothing.ui.login

import com.example.sewingandclothing.data.LoginRegisterRepository
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val loginRegisterRepository: LoginRegisterRepository) : LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    var compositeDisposable = CompositeDisposable()

    override fun loginUser(email: String, password: String) {
        compositeDisposable.add(loginRegisterRepository.loginUser(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if (it) {
                    view.loginIntoApp()
                    view.showProgressBar(false)
                } else {
                    view.showErrorMessage()
                }
            }, {})
        )
    }

    override fun isUSerLoggedIn(): Observable<Boolean> {
        return loginRegisterRepository.isUSerLoggedIn()
    }

    override fun getFirebaseAuth(): FirebaseAuth {
        return loginRegisterRepository.mAuth
    }

    override fun getAuthListener(): FirebaseAuth.AuthStateListener? {
        return loginRegisterRepository.mAuthListener
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun attach(view: LoginContract.View) {
        this.view = view
    }
}