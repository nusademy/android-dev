package com.nusademy.nusademy.ui.invitelistTeacher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nusademy.nusademy.R

class InviteListActivity : AppCompatActivity() {

    companion object {
        const val extra_name = "extra_name"
        const val extra_status = "extra_status"
        const val extra_date = "extra_date"
        const val extra_logo = "extra_logo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_list)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
