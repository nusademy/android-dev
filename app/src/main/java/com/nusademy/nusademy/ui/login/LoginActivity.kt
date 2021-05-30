package com.nusademy.nusademy.ui.login

import DataLogin
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityLoginBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.about.AboutActivity
import com.nusademy.nusademy.ui.home.UserHomeActivity
import com.nusademy.nusademy.ui.signup.SignUpActivity
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        binding.textView9.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignUpActivity ::class.java)
            startActivity(intent)
        })

        binding.btLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
        loginAuth(email,password)
        }
    }

    fun loginAuth(email: String,password:String) {

        RetrofitClient.instanceUserApi.login(email,password)
            .enqueue(object : Callback<DataLogin> {
                override fun onResponse(
                    call: Call<DataLogin>,
                    response: Response<DataLogin>
                ) {

                    if (response.code().toString()=="200"){
                        Log.d("Login", response.body().toString())
                        SharedPrefManager.getInstance(applicationContext).setLogin(true)

                        SharedPrefManager.getInstance(applicationContext).setUser(
                            response.body()?.user?.id.toString(),
                            response.body()?.user?.teacher?.id.toString(),
                            response.body()?.jwt.toString(),
                            response.body()?.user?.fullName.toString(),
                            response.body()?.user?.role?.name.toString()
                        )


                        if (response.body()?.user?.role?.name.toString()=="Basic User"){
                            val intent = Intent(this@LoginActivity, UserHomeActivity ::class.java)
                            startActivity(intent)
                        } else if(response.body()?.user?.role?.name.toString()=="Teacher") {
                            val intent = Intent(this@LoginActivity, MainMenuTeacherActivity ::class.java)
                            startActivity(intent)
                        }
                        Toast.makeText(applicationContext, response.body()?.user?.role?.name.toString(), Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext, "Username/Email/Password Salah", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(calls: Call<DataLogin>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())

                }
            })
    }

    override fun onStart() {
        super.onStart()
        if(SharedPrefManager.getInstance(this).IsLogin){
            if(SharedPrefManager.getInstance(this).Getuser.role=="Teacher"){
                val intent = Intent(applicationContext, MainMenuTeacherActivity::class.java)
                startActivity(intent)
            }else if(SharedPrefManager.getInstance(this).Getuser.role=="Basic User"){
                val intent = Intent(applicationContext, UserHomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

}