package com.example.sewingandclothing.ui.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sewingandclothing.R
import com.example.sewingandclothing.SewingAndClothingApplication
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SewingAndClothingApplication).getSickLeaveComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attach(this)



        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_order,
            R.id.navigation_status,
            R.id.navigation_collection,
            R.id.navigation_profile,
            R.id.navigation_order_list
        ))

        val navGraph: NavGraph = navController.navInflater.inflate(R.navigation.mobile_navigation)

        compositeDisposable.add(presenter.isUserOrSeamstress().subscribe {
            if(it.equals("Seamstress")){
                navGraph.startDestination = R.id.navigation_order_list
            }
        })

        navController.graph = navGraph
        navController.popBackStack(R.id.navigation_order, false)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
        compositeDisposable.clear()
    }
}
