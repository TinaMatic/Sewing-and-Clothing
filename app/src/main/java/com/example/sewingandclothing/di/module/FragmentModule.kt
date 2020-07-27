package com.example.sewingandclothing.di.module

import com.example.sewingandclothing.data.LoginRegisterRepository
import com.example.sewingandclothing.data.UserRepository
import com.example.sewingandclothing.ui.collection.CollectionContract
import com.example.sewingandclothing.ui.collection.CollectionPresenter
import com.example.sewingandclothing.ui.collectionDetails.CollectionDetailsContract
import com.example.sewingandclothing.ui.collectionDetails.CollectionDetailsPresenter
import com.example.sewingandclothing.ui.login.LoginContract
import com.example.sewingandclothing.ui.login.LoginPresenter
import com.example.sewingandclothing.ui.order.OrderContract
import com.example.sewingandclothing.ui.order.OrderPresenter
import com.example.sewingandclothing.ui.profile.ProfileContract
import com.example.sewingandclothing.ui.profile.ProfilePresenter
import com.example.sewingandclothing.ui.register.RegisterContract
import com.example.sewingandclothing.ui.register.RegisterPresenter
import com.example.sewingandclothing.ui.status.StatusContract
import com.example.sewingandclothing.ui.status.StatusPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
class FragmentModule {

    @Provides
    fun provideOrderPresenter(userRepository: UserRepository): OrderContract.Presenter{
        return OrderPresenter(userRepository)
    }

    @Provides
    fun provideStatusPresenter(): StatusContract.Presenter{
        return StatusPresenter()
    }

    @Provides
    fun provideCollectionPresenter(): CollectionContract.Presenter{
        return CollectionPresenter()
    }

    @Provides
    fun provideProfilePresenter(loginRegisterRepository: LoginRegisterRepository): ProfileContract.Presenter{
        return ProfilePresenter(loginRegisterRepository)
    }

    @Provides
    fun provideCollectionDetailsPresenter(): CollectionDetailsContract.Presenter{
        return CollectionDetailsPresenter()
    }



}