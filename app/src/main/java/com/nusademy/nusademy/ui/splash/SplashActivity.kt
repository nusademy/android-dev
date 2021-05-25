package com.nusademy.nusademy.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.nusademy.nusademy.databinding.ActivitySplashBinding
import com.nusademy.nusademy.ui.home.UserHomeActivity
import com.nusademy.nusademy.ui.login.LoginActivity
import com.nusademy.nusademy.ui.signup.SignUpActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity ::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}