package com.example.sewingandclothing.di.component

import android.app.Application
import com.example.sewingandclothing.SewingAndClothingApplication
import com.example.sewingandclothing.di.module.ActivityModule
import com.example.sewingandclothing.di.module.ApplicationModule
import com.example.sewingandclothing.di.module.FragmentModule
import com.example.sewingandclothing.ui.collection.CollectionFragment
import com.example.sewingandclothing.ui.collectionDetails.CollectionDetailsFragment
import com.example.sewingandclothing.ui.login.LoginActivity
import com.example.sewingandclothing.ui.main.MainActivity
import com.example.sewingandclothing.ui.order.OrderFragment
import com.example.sewingandclothing.ui.profile.ProfileFragment
import com.example.sewingandclothing.ui.register.RegisterActivity
import com.example.sewingandclothing.ui.register.RegisterPresenter
import com.example.sewingandclothing.ui.status.StatusFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, FragmentModule::class, ActivityModule::class])
interface ApplicationComponent: AndroidInjector<SewingAndClothingApplication> {

    fun inject(frag: OrderFragment)
    fun inject(frag: StatusFragment)
    fun inject(frag: CollectionFragment)
    fun inject(frag: ProfileFragment)
    fun inject(frag: CollectionDetailsFragment)
    fun inject(act: LoginActivity)
    fun inject(act: RegisterActivity)
    fun inject(act: MainActivity)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder
        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun fragmentModule(fragmentModule: FragmentModule): Builder
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ApplicationComponent
    }
}