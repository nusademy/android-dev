package com.nusademy.nusademy.ui.login_option

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nusademy.nusademy.R
import com.nusademy.nusademy.databinding.ActivityLoginOptionBinding
import com.nusademy.nusademy.databinding.ActivityUserHomeBinding
import com.nusademy.nusademy.ui.about.AboutActivity
import com.nusademy.nusademy.ui.signup.SignUpActivity
import com.nusademy.nusademy.ui.signup.SignUpUserActivity

class LoginOptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginOptionBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        binding.btnRegisteruser.setOnClickListener {
            val intent = Intent(this, SignUpUserActivity ::class.java)
            startActivity(intent)
        }

        binding.btnRegisterteacher.setOnClickListener {
            val intent = Intent(this, SignUpActivity ::class.java)
            startActivity(intent)
        }
    }
}