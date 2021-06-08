package com.nusademy.nusademy.ui.narrationvideos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.ListDataVideos
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityNarrationVideosBinding
import com.nusademy.nusademy.databinding.ActivityUserHomeBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NarrationVideosActivity : AppCompatActivity() {
    private val token= SharedPrefManager.getInstance(this).Getuser.token
    private lateinit var binding:ActivityNarrationVideosBinding
    private lateinit var dataAdapter: NarrationVideosAdapter
    private val list = MutableLiveData<ArrayList<ListDataVideos.ListDataVideosItem>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityNarrationVideosBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        actionBar?.setTitle("Narration videos")
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)


        dataAdapter = NarrationVideosAdapter()
        dataAdapter.notifyDataSetChanged()
        searchTeacherItem()
        getUsers().observe(this, {
            if (it != null) {
                dataAdapter.setDataUser(it)
            }
        })
        binding.rvItems.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL, false
                )
            setHasFixedSize(true)
            adapter = dataAdapter
        }
    }



    private fun searchTeacherItem() {
//        val query = dataBinding.txtSearchuser.text.toString()
        val query=""
//        if (query.isEmpty()) return
//        showLoading(true)
        setUsers(query)
    }

    fun setUsers(query: String) {
        RetrofitClient.instanceUserApi.getVideoNarration("Bearer "+token)
            .enqueue(object : Callback<ListDataVideos> {
                override fun onResponse(call: Call<ListDataVideos>, response: Response<ListDataVideos>) {
                    Log.d("JSON",response.toString())
                    Log.d("JSON",response.body().toString())
                    if (response.isSuccessful) {
                        list.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ListDataVideos>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }

            })
    }

    fun getUsers(): LiveData<ArrayList<ListDataVideos.ListDataVideosItem>> {
        return list
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
