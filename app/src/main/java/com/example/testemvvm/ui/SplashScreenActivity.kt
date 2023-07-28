package com.example.testemvvm.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.testemvvm.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#F8F8FF")

        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this, LoginActivity :: class.java)
            startActivity(i)
            finish()
        },3000)
    }
}