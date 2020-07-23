package com.example.sewingandclothing.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.example.sewingandclothing.R
import com.example.sewingandclothing.SewingAndClothingApplication
import com.example.sewingandclothing.ui.main.MainActivity
import com.example.sewingandclothing.ui.register.RegisterActivity
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.afterTextChangeEvents
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SewingAndClothingApplication).getSickLeaveComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = getString(R.string.login)

        presenter.attach(this)

        addTextChangeListeners()

        compositeDisposable.add(presenter.isUSerLoggedIn().subscribe {
            if(it){
                loginIntoApp()
            }
        })

        compositeDisposable.add(tvRegister.clicks().subscribe ({
            openRegisterScreen()
        },{}))

        compositeDisposable.add(btnLogin.clicks().subscribe ({
            if(isEverythingFilled()){
                login()
            }
        },{}))
    }

    override fun onStart() {
        super.onStart()
        presenter.getAuthListener()?.let { presenter.getFirebaseAuth().addAuthStateListener(it) }
    }

    override fun onStop() {
        super.onStop()
        presenter.getAuthListener()?.let { presenter.getFirebaseAuth().removeAuthStateListener(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        presenter.destroy()
    }

    private fun login(){
        val email = etEmailLogin.text.toString()
        val password = etPasswordLogin.text.toString()

        presenter.loginUser(email, password)
    }

    private fun openRegisterScreen(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun isEverythingFilled(): Boolean{
        var isValid = true

        if(etEmailLogin.text.toString().isEmpty()){
            textInputEmailLogin.error = getString(R.string.email_mandatory)
            isValid = false
        } else {
            textInputEmailLogin.isErrorEnabled = false
        }

        if(etPasswordLogin.text.toString().isEmpty()){
            textInputPasswordLogin.error = getString(R.string.password_mandatory)
            isValid = false
        } else {
            textInputPasswordLogin.isErrorEnabled = false
        }

        return isValid
    }

    private fun addTextChangeListeners(){
        etEmailLogin.afterTextChangeEvents().map {
            it.toString()
        }.doOnNext {
            if(it.isNotEmpty()){
                textInputEmailLogin.error = ""
            }
        }.subscribe()

        etPasswordLogin.afterTextChangeEvents().map {
            it.toString()
        }.doOnNext {
            if(it.isNotEmpty()){
                textInputPasswordLogin.error = ""
            }
        }.subscribe()
    }

    override fun showProgressBar(show: Boolean) {
        if(show){
            loginProgressBar.visibility = View.VISIBLE
        } else {
            loginProgressBar.visibility = View.INVISIBLE
        }
    }

    override fun loginIntoApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.user_does_not_exist), Toast.LENGTH_LONG).show()
    }
}
