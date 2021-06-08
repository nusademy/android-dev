package com.nusademy.nusademy.ui.teacher_profil

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.developer.kalert.KAlertDialog
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.DataBasicUser
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityTeacherProfilBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.about.AboutActivity
import com.nusademy.nusademy.ui.editteacher.EditTeacher2Activity
import com.nusademy.nusademy.ui.editteacher.EditTeacherActivity
import com.nusademy.nusademy.ui.home.UserHomeActivity
import com.nusademy.nusademy.ui.login.LoginActivity
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherProfilBinding
    private val token = SharedPrefManager.getInstance(this).Getuser.token
    private val id = SharedPrefManager.getInstance(this).Getuser.idteacher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_profil)
        binding = ActivityTeacherProfilBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()
        //Memanggil Fungsi Get Teacher
        GetTeacherProfile(id, token)

        Glide.with(this)
            .load(R.drawable.profile_null)
            .into(findViewById(R.id.iv_teacher))

        binding.btnChangeprofil.setOnClickListener(
            View.OnClickListener {
                GetTeacherProfile(id, token)

                val bundle = Bundle()
                //get id2
                bundle.putString("fullName", binding.nameTeacherProfil.text.toString())
                bundle.putString("email", binding.emailTeacherProfil.text.toString())

                val intent = Intent(this, EditTeacher2Activity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        )

        binding.btnChangeprofil2.setOnClickListener(
            View.OnClickListener {
                GetTeacherProfile(id, token)

                val bundle = Bundle()
                //get id
                bundle.putString("last_education", binding.lasteducationTeacherProfil.text.toString())
                bundle.putString("campus", binding.campusTeacherProfil.text.toString())
                bundle.putString("major", binding.majorTeacherProfil.text.toString())
                bundle.putString("ipk", binding.ipkTeacherProfil.text.toString())
                bundle.putString("short_brief", binding.shortbriefTeacherProfil.text.toString())
                bundle.putString("linkedin", binding.linkedinTeacherProfil.text.toString())
                bundle.putString("video_branding", binding.videobrandingTeacherProfil.text.toString())


                val intent = Intent(this, EditTeacherActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        )

        binding.btnback.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainMenuTeacherActivity::class.java)
            startActivity(intent)
        })

        binding.btnLogout.setOnClickListener {
            SharedPrefManager.getInstance(applicationContext).setLogin(false)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        GetTeacherProfile(id, token)
    }

    // Fungsi Get Teacher Profile
    fun GetTeacherProfile(id: String, token: String) {
        val pDialog = KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
        pDialog.show()
        Log.d("Teacher", "CEK TEACHER" + id + token)
        RetrofitClient.instanceUserApi.getProfileTeacher(id, "Bearer " + token)
            .enqueue(object : Callback<DataTeacher> {
                override fun onResponse(
                    call: Call<DataTeacher>,
                    response: Response<DataTeacher>
                ) {
                    pDialog.hide()
                    Log.d("Teacher", response.body().toString())
                    // Cek Koneksi API Behasil
                    if (response.code().toString() == "200") {

                        //Cek Output Json


                        //Set data JSON ke tampilan
                        val data = response.body()
                        binding.nameTeacherProfil.text = data?.user?.fullName
                        binding.emailTeacherProfil.text = data?.user?.email
                        //
                        binding.lasteducationTeacherProfil.text = data?.lastEducation
                        binding.campusTeacherProfil.text = data?.campus
                        binding.majorTeacherProfil.text = data?.major
                        binding.ipkTeacherProfil.text = data?.ipk.toString()
                        binding.shortbriefTeacherProfil.text = data?.shortBrief
                        binding.linkedinTeacherProfil.text = data?.linkedin
                        binding.videobrandingTeacherProfil.text = data?.videoBranding.toString()


                        // Cek Koneksi API Gagal
                    } else {
                        Toast.makeText(applicationContext, "Failed To Get Data", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<DataTeacher>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())
                }
            })
    }

}