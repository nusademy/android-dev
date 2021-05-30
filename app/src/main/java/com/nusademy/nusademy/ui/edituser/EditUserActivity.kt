package com.nusademy.nusademy.ui.edituser

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import com.developer.kalert.KAlertDialog
import com.developer.kalert.KAlertDialog.KAlertClickListener
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.DataBasicUser
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityEditUserBinding
import com.nusademy.nusademy.databinding.ActivityUserProfileBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.profile.UserProfileActivity
import com.nusademy.nusademy.ui.teacher_profil.TeacherProfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditUserBinding

    //Ambil id dan token dari SharedPreference
    private val token= SharedPrefManager.getInstance(this).Getuser.token
    private val id= SharedPrefManager.getInstance(this).Getuser.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        val bundle = intent.extras
        if (bundle != null) {
            binding.tvName.setText(bundle.getString("full_name",""))
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
            val intent = Intent(this, UserProfileActivity::class.java)
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
        RetrofitClient.instanceUserApi.editProfileUsers(id,"Bearer "+token,fullName,email)
            .enqueue(object : Callback<DataBasicUser> {
                override fun onResponse(
                    call: Call<DataBasicUser>,
                    response: Response<DataBasicUser>
                ) {
                    pDialog.hide()
                    if (response.isSuccessful) {
                        // Saat response sukses finnish activity (menutup/mengakhiri activity editprofile)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Gagal Cek kembali Isian", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(calls: Call<DataBasicUser>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())

                }
            })
    }

}