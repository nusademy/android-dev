package com.nusademy.nusademy.ui.editteacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.developer.kalert.KAlertDialog
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.DataBasicUser
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityEditTeacher2Binding
import com.nusademy.nusademy.databinding.ActivityEditUserBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.teacher_profil.TeacherProfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditTeacher2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTeacher2Binding

    //Ambil id dan token dari SharedPreference
    private val token= SharedPrefManager.getInstance(this).Getuser.token
    private val id= SharedPrefManager.getInstance(this).Getuser.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTeacher2Binding.inflate(this.layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        val bundle = intent.extras
        if (bundle != null) {
            binding.tvName.setText(bundle.getString("fullName",""))
            binding.tvEmail.setText( bundle.getString("email",""))
        }

        // Action Saat Button Save Di Klik
        binding.btSave.setOnClickListener(View.OnClickListener {

            // Memanggil Function UpdateSchool
            UpdateUsers(
                binding.tvName.text.toString(),
                binding.tvEmail.text.toString())
        })

        binding.btnback.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, TeacherProfilActivity::class.java)
            startActivity(intent)
        })

    }

    //Inisiasi Fuction UpdateSchool
    fun UpdateUsers(
        fullName:String,
        email:String) {
        val pDialog = KAlertDialog(this, KAlertDialog.SUCCESS_TYPE)
        pDialog.contentText = "Updated data has been saved"
        pDialog.show()
        RetrofitClient.instanceUserApi.editProfileTeachers2(id,"Bearer "+token,fullName,email)
            .enqueue(object : Callback<DataTeacher> {
                override fun onResponse(
                    call: Call<DataTeacher>,
                    response: Response<DataTeacher>
                ) {
                    pDialog.hide()
                    if (response.isSuccessful) {
                        // Saat response sukses finnish activity (menutup/mengakhiri activity editprofile)
                        finish()
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