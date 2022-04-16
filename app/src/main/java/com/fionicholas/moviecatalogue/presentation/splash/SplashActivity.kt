package com.fionicholas.moviecatalogue.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fionicholas.moviecatalogue.R
import com.fionicholas.moviecatalogue.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var fade: Animation

    private lateinit var imgLogo: ImageView
    private lateinit var tvAppName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        imgLogo = findViewById(R.id.logo)
        tvAppName = findViewById(R.id.tvAppName)

        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        fade = AnimationUtils.loadAnimation(this, R.anim.fade)

        imgLogo.startAnimation(fade)
        tvAppName.startAnimation(fade)

        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.start(this)
        }, 2000)
    }
}