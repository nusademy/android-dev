package com.nusademy.nusademy.ui.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.developer.kalert.KAlertDialog
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.DataBasicUser
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityTeacherProfilBinding
import com.nusademy.nusademy.databinding.ActivityUserProfileBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.editteacher.EditTeacherActivity
import com.nusademy.nusademy.ui.edituser.EditUserActivity
import com.nusademy.nusademy.ui.home.UserHomeActivity
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private val token= SharedPrefManager.getInstance(this).Getuser.token
    private val id= SharedPrefManager.getInstance(this).Getuser.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        binding = ActivityUserProfileBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        Glide.with(this)
            .load(R.drawable.profile_null)
            .into(findViewById(R.id.iv_avatar_user))

        //Memanggil Fungsi Get User
        GetUserProfile(id,token)

        binding.btnback.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UserHomeActivity ::class.java)
            startActivity(intent)
        })


        binding.btnChangeprofil.setOnClickListener(
                View.OnClickListener {
                    GetUserProfile(id,token)
                    val bundle = Bundle()
                    bundle.putString("full_name", binding.nameUserProfil.text.toString())
                    bundle.putString("email", binding.emailUserProfil.text.toString())

                    val intent = Intent(this, EditUserActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
        )
    }

    override fun onResume() {
        super.onResume()
        GetUserProfile(id,token)
    }

    // Fungsi Get User Profile
    fun GetUserProfile(id: String,token:String) {
        val pDialog = KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
        pDialog.show()

        RetrofitClient.instanceUserApi.getProfileBasicUser(id,"Bearer "+token)
                .enqueue(object : Callback<DataBasicUser> {
                    override fun onResponse(
                            call: Call<DataBasicUser>,
                            response: Response<DataBasicUser>
                    ) {
                        pDialog.hide()
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