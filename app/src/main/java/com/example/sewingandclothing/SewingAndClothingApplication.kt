package com.example.sewingandclothing

import android.app.Activity
import android.app.Application
import com.example.sewingandclothing.di.component.ApplicationComponent
import com.example.sewingandclothing.di.component.DaggerApplicationComponent
import com.example.sewingandclothing.di.module.ActivityModule
import com.example.sewingandclothing.di.module.ApplicationModule
import com.example.sewingandclothing.di.module.FragmentModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SewingAndClothingApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .applicationModule(ApplicationModule(applicationContext))
            .fragmentModule(FragmentModule())
            .activityModule(ActivityModule())
            .build()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    fun getSickLeaveComponent(): ApplicationComponent{
        return appComponent
    }
}