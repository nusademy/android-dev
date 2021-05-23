package com.nusademy.nusademy.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nusademy.nusademy.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()
    }
}