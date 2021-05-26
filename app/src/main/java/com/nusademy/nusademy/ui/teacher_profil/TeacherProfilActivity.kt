package com.nusademy.nusademy.ui.teacher_profil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityMainMenuTeacherBinding
import com.nusademy.nusademy.databinding.ActivityTeacherProfilBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.home.UserHomeActivity
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherProfilBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_profil)

        binding = ActivityTeacherProfilBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()


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
                        Toast.makeText(applicationContext, "Gagal Mendapatkan Data", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(calls: Call<DataTeacher>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())

                }
            })
    }

}