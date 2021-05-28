package com.nusademy.nusademy.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.DataBasicUser
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityTeacherProfilBinding
import com.nusademy.nusademy.databinding.ActivityUserProfileBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.editteacher.EditTeacherActivity
import com.nusademy.nusademy.ui.home.UserHomeActivity
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        binding = ActivityUserProfileBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        binding.btnback.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UserHomeActivity ::class.java)
            startActivity(intent)
        })

        // Get data id dan token login dari sharedpreference
        val id= SharedPrefManager.getInstance(this).Getuser.id
        val token = SharedPrefManager.getInstance(this).Getuser.token

        //Memanggil Fungsi Get User
        GetUserProfile(id,token)

    }

    // Fungsi Get User Profile
    fun GetUserProfile(id: String,token:String) {
        RetrofitClient.instanceUserApi.getProfileBasicUser(id,"Bearer "+token)
                .enqueue(object : Callback<DataBasicUser> {
                    override fun onResponse(
                            call: Call<DataBasicUser>,
                            response: Response<DataBasicUser>
                    ) {
                        // Cek Koneksi API Behasil
                        if (response.code().toString()=="200"){

                            //Cek Output Json
                            Log.d("User", response.body().toString())

                            //Set data JSON ke tampilan
                            val data = response.body()
                            binding.nameUserProfil.text = data?.fullName
                            binding.emailUserProfil.text = data?.email


                            // Cek Koneksi API Gagal
                        } else {
                            Toast.makeText(applicationContext, "Failed To Get Data", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(calls: Call<DataBasicUser>, ts: Throwable) {
                        Log.d("Error", ts.message.toString())

                    }
                })
    }

}