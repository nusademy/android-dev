package com.nusademy.nusademy.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.nusademy.nusademy.R
import com.nusademy.nusademy.R.layout
import com.nusademy.nusademy.databinding.ActivityUserHomeBinding
import com.nusademy.nusademy.ui.about.AboutActivity

class UserHomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUserHomeBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_user_home)
        binding = ActivityUserHomeBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        binding.btAbout.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AboutActivity ::class.java)
            startActivity(intent)
        })

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
    }



}