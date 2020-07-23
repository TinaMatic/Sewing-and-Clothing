package com.example.sewingandclothing.ui.profile

import com.example.sewingandclothing.data.LoginRegisterRepository
import javax.inject.Inject

class ProfilePresenter @Inject constructor(private val loginRegisterRepository: LoginRegisterRepository) : ProfileContract.Presenter {

    private lateinit var view: ProfileContract.View

    override fun logout() {
        loginRegisterRepository.logout()
    }


    override fun destroy() {
        TODO("Not yet implemented")
    }

    override fun attach(view: ProfileContract.View) {
        this.view = view
    }
}