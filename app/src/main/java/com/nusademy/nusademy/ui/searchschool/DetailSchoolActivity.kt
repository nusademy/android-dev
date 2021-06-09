package com.nusademy.nusademy.ui.searchschool

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.developer.kalert.KAlertDialog
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.DataProfileSchool
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.databinding.ActivityDetailSchoolBinding
import com.nusademy.nusademy.ui.requestschool.RequestSchoolActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailSchoolActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailSchoolBinding
    val token= SharedPrefManager.getInstance(this).Getuser.token
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailSchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        actionBar?.setTitle("Informasi Guru")
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val idschool = intent.getStringExtra("idschool").toString()
        getprofile(idschool)
        Glide.with(this)
            .load(R.drawable.profile_null)
            .into(binding.ivAvatarTeacher)

        binding.btCancel.setOnClickListener {
            finish()
        }
        binding.btInvite.setOnClickListener {
            val intent = Intent(applicationContext, RequestSchoolActivity::class.java)
            intent.putExtra("idschool", idschool)
            startActivity(intent)
        }
    }
    
    
    fun getprofile(idschool:String){
        val pDialog = KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
        pDialog.show()
        RetrofitClient.instanceUserApi.getProfileSchool(idschool, "Bearer " + token)
            .enqueue(object : Callback<DataProfileSchool> {
                override fun onResponse(
                    call: Call<DataProfileSchool>,
                    response: Response<DataProfileSchool>
                ) {
                    pDialog.dismissWithAnimation()
                    Log.d("Teacher", response.body().toString())
                    // Cek Koneksi API Behasil
                    if (response.code().toString() == "200") {
                        //Cek Output Json
                        //Set data JSON ke tampilan
                        val data = response.body()
                        binding.tvNama.text = data?.name
                        binding.tvHeadmaster.text = data?.headmaster
                        binding.tvTelp.text = data?.phoneNumber
                        binding.tvAddress.text = data?.address
                        binding.tvWeb.text = data?.website

                        // Cek Koneksi API Gagal
                    } else {
                        Toast.makeText(applicationContext, "Failed To Get Data", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<DataProfileSchool>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())
                }
            })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}