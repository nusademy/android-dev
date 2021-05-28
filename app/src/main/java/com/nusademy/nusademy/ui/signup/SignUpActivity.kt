package com.nusademy.nusademy.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.dataapi.register.RegisterPostResponse
import com.nusademy.nusademy.databinding.ActivityEditTeacherBinding
import com.nusademy.nusademy.databinding.ActivityMainMenuTeacherBinding
import com.nusademy.nusademy.databinding.ActivitySignUpBinding
import com.nusademy.nusademy.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_edit_teacher.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.edit_text
import kotlinx.android.synthetic.main.activity_sign_up.edit_text0
import kotlinx.android.synthetic.main.activity_sign_up.edit_text2
import kotlinx.android.synthetic.main.activity_sign_up.edit_text4
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        binding.textView9.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity ::class.java)
            startActivity(intent)
        })

        button.setOnClickListener {
            registered()
        }

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()
    }

    fun registered() {
        if (edit_text0.text!!.isEmpty()) {
            edit_text0.error = "Full name cannot be empty"
            edit_text0.requestFocus()
            return
        } else if (edit_text.text!!.isEmpty()) {
            edit_text.error = "Email cannot be empty"
            edit_text.requestFocus()
            return
        } else if (edit_text4.text!!.isEmpty()) {
            edit_text4.error = "Username cannot be empty"
            edit_text4.requestFocus()
            return
        } else if (edit_text2.text!!.isEmpty()) {
            edit_text2.error = "Password cannot be empty"
            edit_text2.requestFocus()
            return
        }

        RetrofitClient.instanceUserApi.postRegister(
            edit_text0.text.toString(),
            edit_text.text.toString(),
            edit_text4.text.toString(),
            edit_text2.text.toString()
        ).enqueue(object : Callback<RegisterPostResponse>{
            override fun onResponse(
                call: Call<RegisterPostResponse>,
                response: Response<RegisterPostResponse>
            ) {
                //Data berhasil masuk
            }

            override fun onFailure(call: Call<RegisterPostResponse>, t: Throwable) {
                //Data gagal masuk
            }
        })
    }
}