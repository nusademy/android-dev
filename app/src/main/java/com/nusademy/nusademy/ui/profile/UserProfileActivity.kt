package com.nusademy.nusademy.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nusademy.nusademy.R

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setSupportActionBar(findViewById(R.id.toolbar))

    }
}