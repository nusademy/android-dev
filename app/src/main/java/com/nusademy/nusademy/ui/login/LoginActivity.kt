package com.nusademy.nusademy.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nusademy.nusademy.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()
    }
}