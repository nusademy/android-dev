package com.nusademy.nusademy.ui.signup

import DataLogin
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nusademy.nusademy.dataapi.DataUser
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivitySignUpBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var id:String
    private lateinit var idteacher:String
    private lateinit var jwt:String
    private lateinit var name:String
    private lateinit var role:String
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        binding.btRegister.setOnClickListener {
            register(
                binding.editUsername.text.toString(),
                binding.editEmail.text.toString(),
                binding.editFullname.text.toString(),
                binding.editPassword.text.toString(),
                "true",
                "5"
            )
        }
    }

    fun register(
        username: String,
        email: String,
        fullname: String,
        password: String,
        confirm: String,
        assignrole: String
    ) {

        RetrofitClient.instanceUserApi.register(
            username,
            email,
            fullname,
            password,
            confirm,
            assignrole,
        )
            .enqueue(object : Callback<DataLogin> {
                override fun onResponse(
                    call: Call<DataLogin>,
                    response: Response<DataLogin>
                ) {
                    Log.d("Login", response.toString())
                    Log.d("Login", response.message().toString())
                    Log.d("Login", response.errorBody().toString())
                    Log.d("Login", response.body().toString())
                    if (response.code().toString()=="200"){
                        Log.d("Login", response.body().toString())

                        id=response.body()?.user?.id.toString()
                        idteacher=response.body()?.user?.teacher?.id.toString()
                        jwt=response.body()?.jwt.toString()
                        name= response.body()?.user?.fullName.toString()
                        role=response.body()?.user?.role?.name.toString()

                        SharedPrefManager.getInstance(applicationContext).setLogin(true)
                        SharedPrefManager.getInstance(applicationContext).setUser(
                            id,idteacher,jwt,name,role
                        )
                        AssignRole(
                            response.body()?.jwt.toString(),
                            assignrole,
                            response.body()?.user?.id.toString()
                        )


                    } else {
                        Toast.makeText(applicationContext, "Username/Email sudah digunakan", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(calls: Call<DataLogin>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())
                }
            })
    }

    fun AssignRole(token:String,role: String,iduser:String) {
        RetrofitClient.instanceUserApi.editregister(
            "Bearer " + token,role,iduser
        )
            .enqueue(object : Callback<DataUser> {
                override fun onResponse(
                    call: Call<DataUser>,
                    response: Response<DataUser>
                ) {
                    if (response.isSuccessful) {
                        // Saat response sukses finnish activity (menutup/mengakhiri activity editprofile)
                        val intent = Intent(this@SignUpActivity, MainMenuTeacherActivity ::class.java)
                        startActivity(intent)

                        SharedPrefManager.getInstance(applicationContext).setUser(
                            id,idteacher,jwt,name,response.body()?.role?.name.toString()
                        )

                    } else {
                    }
                }

                override fun onFailure(calls: Call<DataUser>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())
                }
            })
    }

}
//    fun registered() {
//        if (edit_text0.text!!.isEmpty()) {
//            edit_text0.error = "Full name cannot be empty"
//            edit_text0.requestFocus()
//            return
//        } else if (edit_text.text!!.isEmpty()) {
//            edit_text.error = "Email cannot be empty"
//            edit_text.requestFocus()
//            return
//        } else if (edit_text4.text!!.isEmpty()) {
//            edit_text4.error = "Username cannot be empty"
//            edit_text4.requestFocus()
//            return
//        } else if (edit_text2.text!!.isEmpty()) {
//            edit_text2.error = "Password cannot be empty"
//            edit_text2.requestFocus()
//            return
//        }

