package com.example.sewingandclothing.di.module

import android.app.Activity
import com.example.sewingandclothing.data.LoginRegisterRepository
import com.example.sewingandclothing.data.UserRepository
import com.example.sewingandclothing.ui.login.LoginContract
import com.example.sewingandclothing.ui.login.LoginPresenter
import com.example.sewingandclothing.ui.main.MainContract
import com.example.sewingandclothing.ui.main.MainPresenter
import com.example.sewingandclothing.ui.register.RegisterContract
import com.example.sewingandclothing.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
class ActivityModule {


    @Provides
    fun provideLoginPresenter(loginRepository: LoginRegisterRepository): LoginContract.Presenter{
        return LoginPresenter(loginRepository)
    }

    @Provides
    fun provideRegisterPresenter(registerRepository: LoginRegisterRepository): RegisterContract.Presenter{
        return RegisterPresenter(registerRepository)
    }

    @Provides
    fun provideMainPresenter(userRepository: UserRepository): MainContract.Presenter{
        return MainPresenter(userRepository)
    }
}