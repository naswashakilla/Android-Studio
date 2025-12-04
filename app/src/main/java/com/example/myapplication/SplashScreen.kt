package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logo = findViewById<ImageView>(R.id.logoapk)
        val brandName = findViewById<TextView>(R.id.brand_name)

        logo.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(1000)
            .start()

        brandName.animate()
            .alpha(1f)
            .setStartDelay(800)
            .setDuration(1000)
            .start()

        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },
            3000
        )
    }
}
