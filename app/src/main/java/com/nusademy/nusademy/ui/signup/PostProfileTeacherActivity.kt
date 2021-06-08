package com.nusademy.nusademy.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.developer.kalert.KAlertDialog
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityEditTeacherBinding
import com.nusademy.nusademy.databinding.ActivityPostProfileTeacherBinding
import com.nusademy.nusademy.databinding.ActivitySignUpBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.teacher_profil.TeacherProfilActivity
import com.nusademy.ui.mainmenuTeacher.MainMenuTeacherActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostProfileTeacherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostProfileTeacherBinding
    private val token= SharedPrefManager.getInstance(this).Getuser.token
    private val iduser= SharedPrefManager.getInstance(this).Getuser.id
    private val fullname= SharedPrefManager.getInstance(this).Getuser.name
    private val rolename= SharedPrefManager.getInstance(this).Getuser.role

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostProfileTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()


//         Action Saat Button Save Di Klik
        binding.btSave.setOnClickListener(View.OnClickListener {

            // Memanggil Function UpdateSchool
            UpdateTeacher(
                binding.tvLasteducation.text.toString(),
                binding.tvCampus.text.toString(),
                binding.tvMajor.text.toString(),
                binding.tvIpk.text.toString(),
                binding.tvBrief.text.toString(),
                binding.tvVideobranding.text.toString(),
                binding.tvLinkedin.text.toString()
            )
        })

        binding.btnback.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, TeacherProfilActivity::class.java)
            startActivity(intent)
        })

    }

    //Inisiasi Fuction UpdateSchool
    fun UpdateTeacher(
        lastEducation:String,
        campus:String,
        major:String,
        ipk:String,
        brief:String,
        videoBranding:String,
        linkedin:String

    ) {
        val pDialog = KAlertDialog(this, KAlertDialog.SUCCESS_TYPE)
        pDialog.contentText = "Updated data has been saved"
        pDialog.setCancelable(false)
        pDialog.show()
        RetrofitClient.instanceUserApi.addProfileTeachers(
            "Bearer "+token,
            lastEducation,
            campus,
            major,
            ipk,
            brief,
            videoBranding,
            linkedin,"1",iduser)
            .enqueue(object : Callback<DataTeacher> {
                override fun onResponse(
                    call: Call<DataTeacher>,
                    response: Response<DataTeacher>
                ) {
                    pDialog.hide()
                    Log.d("Post",response.toString())
                    if (response.isSuccessful) {
                        SharedPrefManager.getInstance(applicationContext).setUser(
                            iduser,
                            response.body()?.id.toString(),
                            token,
                            fullname,
                            rolename
                        )
                        pDialog.changeAlertType(KAlertDialog.SUCCESS_TYPE)
                        pDialog.setTitleText("Berhasil")
                        pDialog.setContentText("Data Profil Teacher Berhasil Dibuat")
                        pDialog.setConfirmText("OK")
                        pDialog.setConfirmClickListener {
                                sDialog -> sDialog.dismissWithAnimation()
                            val intent = Intent(this@PostProfileTeacherActivity, MainMenuTeacherActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(applicationContext, "Gagal Cek kembali Isian", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(calls: Call<DataTeacher>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())

                }
            })
    }
}