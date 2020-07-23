package com.example.sewingandclothing.ui.login

import com.example.sewingandclothing.ui.base.BaseContract
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable

class LoginContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun loginUser(email: String, password: String)
        fun isUSerLoggedIn(): Observable<Boolean>
        fun getFirebaseAuth(): FirebaseAuth
        fun getAuthListener(): FirebaseAuth.AuthStateListener?
    }

    interface View{
        fun showProgressBar(show: Boolean)
        fun loginIntoApp()
        fun showErrorMessage()
    }
}