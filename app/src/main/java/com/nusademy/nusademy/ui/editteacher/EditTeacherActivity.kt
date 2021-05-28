package com.nusademy.nusademy.ui.editteacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityEditTeacherBinding
import com.nusademy.nusademy.databinding.ActivityLoginBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.home.UserHomeActivity
import com.nusademy.nusademy.ui.teacher_profil.TeacherProfilActivity
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditTeacherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTeacherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_teacher)

        binding = ActivityEditTeacherBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        binding.btnback.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, TeacherProfilActivity ::class.java)
            startActivity(intent)
        })

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        binding.btSave.setOnClickListener {
            val fullname = binding.editText0.text.toString().trim()
            val email = binding.editText.text.toString().trim()
            val phone = binding.editText4.text.toString().trim()
            val linkedin = binding.editText6.text.toString().trim()
            val personalbrand = binding.editText7.text.toString().trim()
            val password = binding.editText2.text.toString().trim()
        }
    }


}