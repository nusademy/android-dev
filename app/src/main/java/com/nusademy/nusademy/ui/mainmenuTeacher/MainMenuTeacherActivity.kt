package com.nusademy.ui.mainmenuTeacher

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.nusademy.nusademy.R
import com.nusademy.nusademy.databinding.ActivityMainMenuTeacherBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.ui.about.AboutActivity
import com.nusademy.nusademy.ui.applyguestteacher.GuestTeacherRequestActivity
import com.nusademy.nusademy.ui.applytempteacher.TempTeacherRequestActivity
import com.nusademy.nusademy.ui.chatbot4java.MainChatbot4Activity
import com.nusademy.nusademy.ui.narrationvideos.NarrationVideosActivity
import com.nusademy.nusademy.ui.searchschool.SearchSchoolActivity
import com.nusademy.nusademy.ui.signup.PostProfileTeacherActivity
import com.nusademy.nusademy.ui.teacher_profil.TeacherProfilActivity

class MainMenuTeacherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuTeacherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_teacher)

        binding = ActivityMainMenuTeacherBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        binding.btAbout.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AboutActivity ::class.java)
            startActivity(intent)
        })

        binding.passionidentifierButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainChatbot4Activity ::class.java)
            startActivity(intent)
        })

        binding.btApplyGuest.setOnClickListener {
            val intent = Intent(this, GuestTeacherRequestActivity ::class.java)
            startActivity(intent)
        }

        binding.btApplyTemp.setOnClickListener {
            val intent = Intent(this, TempTeacherRequestActivity ::class.java)
            startActivity(intent)
        }
        binding.narrationbookButton.setOnClickListener {
            val intent = Intent(this, NarrationVideosActivity ::class.java)
            startActivity(intent)
        }
        binding.btSearchschool.setOnClickListener {
            val intent = Intent(this, SearchSchoolActivity ::class.java)
            startActivity(intent)
        }

        Glide.with(this)
                .load(R.drawable.profile_null)
                .into(findViewById(R.id.img_me))

        var imageList = ArrayList<SlideModel>()

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(SlideModel(R.drawable.carousel1))
        imageList.add(SlideModel(R.drawable.carousel2))
        imageList.add(SlideModel(R.drawable.carousel3))


        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)


        binding.tvFullname.text= SharedPrefManager.getInstance(this).Getuser.name
        binding.tvRole.text= SharedPrefManager.getInstance(this).Getuser.role

        binding.linearprofile.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, TeacherProfilActivity ::class.java)
            startActivity(intent)
        })
    }

    override fun onStart() {
        super.onStart()
        if(SharedPrefManager.getInstance(this).Getuser.idteacher=="null"){
            val intent = Intent(applicationContext, PostProfileTeacherActivity::class.java)
            startActivity(intent)
        }
        binding.tvFullname.text= SharedPrefManager.getInstance(this).Getuser.name
        binding.tvRole.text= SharedPrefManager.getInstance(this).Getuser.role
    }

}