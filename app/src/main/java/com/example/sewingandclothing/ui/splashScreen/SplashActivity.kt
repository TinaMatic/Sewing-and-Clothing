package com.example.sewingandclothing.ui.splashScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.sewingandclothing.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        makeFullScreen()

        Handler().postDelayed({
            //if logged in show main screen

            //if seamstress show order list

        },2000)
    }

    private fun makeFullScreen(){
        //remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        //make full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //hide the toolbar
        supportActionBar?.hide()
    }
}
