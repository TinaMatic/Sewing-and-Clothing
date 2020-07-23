package com.example.sewingandclothing.di.module

import android.content.Context
import com.example.sewingandclothing.data.LoginRegisterRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context{
        return context
    }

//    @Provides
//    @Singleton
//    fun provideLoginRegisterRepository(): LoginRegisterRepository{
//        return LoginRegisterRepository()
//    }
}