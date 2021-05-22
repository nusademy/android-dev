package com.nusademy.ui.schoolrecommendationTeacher


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nusademy.nusademy.R

class SchoolRecommendationActivity: AppCompatActivity() {
    companion object {
        const val extra_name = "extra_name"
        const val extra_status = "extra_status"
        const val extra_date = "extra_date"
        const val extra_avatar = "extra_logo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_recommendation)
        supportActionBar?.title = "School Recommendation"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
