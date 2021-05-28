package com.nusademy.nusademy.ui.teacher_profil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityTeacherProfilBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.about.AboutActivity
import com.nusademy.nusademy.ui.editteacher.EditTeacherActivity
import com.nusademy.nusademy.ui.home.UserHomeActivity
import com.nusademy.nusademy.ui.login.LoginActivity
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_profil)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        binding = ActivityTeacherProfilBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        binding.btnChangeprofil.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, EditTeacherActivity ::class.java)
            startActivity(intent)
        })

        binding.btnback.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainMenuTeacherActivity ::class.java)
            startActivity(intent)
        })

        // Get data id dan token login dari sharedpreference
        val id=SharedPrefManager.getInstance(this).Getuser.id
        val token = SharedPrefManager.getInstance(this).Getuser.token

        //Memanggil Fungsi Get Teacher
        GetTeacherProfile(id,token)

    }


    // Fungsi Get Teacher Profile
    fun GetTeacherProfile(id: String,token:String) {
        RetrofitClient.instanceUserApi.getProfileTeacher(id,"Bearer "+token)
            .enqueue(object : Callback<DataTeacher> {
                override fun onResponse(
                    call: Call<DataTeacher>,
                    response: Response<DataTeacher>
                ) {
                    // Cek Koneksi API Behasil
                    if (response.code().toString()=="200"){

                        //Cek Output Json
                        Log.d("Teacher", response.body().toString())

                        //Set data JSON ke tampilan
                        val data = response.body()
                        binding.nameTeacherProfil.text = data?.fullName
                        binding.emailTeacherProfil.text = data?.email


                    // Cek Koneksi API Gagal
                    } else {
                        Toast.makeText(applicationContext, "Failed To Get Data", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(calls: Call<DataTeacher>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())

                }
            })
    }
}