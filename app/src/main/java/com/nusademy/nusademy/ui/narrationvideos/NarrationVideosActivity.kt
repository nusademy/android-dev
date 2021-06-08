package com.nusademy.nusademy.ui.narrationvideos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nusademy.nusademy.databinding.ActivityNarrationVideosBinding
import com.nusademy.nusademy.databinding.ActivityUserHomeBinding

private lateinit var binding:ActivityNarrationVideosBinding
class NarrationVideosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityNarrationVideosBinding.inflate(this.layoutInflater)
        setContentView(binding.root)


    }
}