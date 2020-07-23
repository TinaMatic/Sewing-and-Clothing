package com.example.sewingandclothing.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.sewingandclothing.R
import com.example.sewingandclothing.SewingAndClothingApplication
import com.example.sewingandclothing.data.LoginRegisterRepository
import com.example.sewingandclothing.ui.main.MainActivity
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.afterTextChangeEvents
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private var compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var presenter: RegisterContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SewingAndClothingApplication).getSickLeaveComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.title = getString(R.string.register)

        presenter.attach(this)

        addTextChangeListener()

        val listUserSeamstress = listOf("User", "Seamstress")
        val spinnerAdapter = ArrayAdapter(this, R.layout.list_user_seamstress, listUserSeamstress)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerUserSeamstress.adapter = spinnerAdapter

        compositeDisposable.add(btnRegister.clicks().subscribe ({
            if(isEverythingFilled()){
                createAccount()
            }
        },{}))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
        compositeDisposable.clear()
    }

    private fun createAccount(){
        val email = etEmailRegister.text.toString()
        val password = etPasswordRegister.text.toString()
        val username = etUsernameRegister.text.toString()
        val userVsSeamstress = spinnerUserSeamstress.selectedItem.toString()

        if(isPasswordLongEnough(password)){
            presenter.createAccount(email, password, username, userVsSeamstress)
        }
    }

    private fun isPasswordLongEnough(password: String): Boolean{
        return if(password.length >= 6){
            textInputPasswordRegister.isErrorEnabled = false
            true
        } else {
            textInputPasswordRegister.error = getString(R.string.password_not_long_enough)
            false
        }
    }

    private fun isEverythingFilled(): Boolean{
        var isValid = true

        if(etUsernameRegister.text.toString().isEmpty()){
            textInputUsernameRegister.error = getString(R.string.username_mandatory)
            isValid = false
        } else {
            textInputUsernameRegister.isErrorEnabled = false
        }

        if(etEmailRegister.text.toString().isEmpty()){
            textInputEmailRegister.error = getString(R.string.email_mandatory)
            isValid = false
        } else {
            textInputEmailRegister.isErrorEnabled = false
        }

        if(etPasswordRegister.text.toString().isEmpty()){
            textInputPasswordRegister.error = getString(R.string.password_mandatory)
            isValid = false
        } else {
            textInputPasswordRegister.isErrorEnabled = false
        }

        return isValid
    }

    private fun addTextChangeListener(){
        etUsernameRegister.afterTextChangeEvents().map {
            it.toString()
        }.doOnNext {
            if(it.isNotEmpty()){
                Log.i("TextChangeListener", it.length.toString())
                textInputUsernameRegister.isErrorEnabled = false
            } else {
                textInputUsernameRegister.error = getString(R.string.username_mandatory)
            }
        }.subscribe()


        etEmailRegister.afterTextChangeEvents().map {
            it.toString()
        }.doOnNext {
            if(it.isNotEmpty()){
                textInputEmailRegister.isErrorEnabled = false
            }
        }.subscribe()

        etPasswordRegister.afterTextChangeEvents().map {
            it.toString()
        }.doOnNext {
            if(it.isNotEmpty()){
                textInputPasswordRegister.isErrorEnabled = false
            }
        }.subscribe()
    }

    override fun loginIntoApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }

}
