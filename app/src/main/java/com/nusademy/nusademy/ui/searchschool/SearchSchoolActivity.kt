package com.nusademy.nusademy.ui.searchschool

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.dataapi.DataTeacher
import com.nusademy.nusademy.dataapi.ListDataSchool
import com.nusademy.nusademy.dataapi.ListDataSchool.ListDataSchoolItem
import com.nusademy.nusademy.databinding.ActivitySearchSchoolBinding
import com.nusademy.nusademy.databinding.ActivitySearchTeacherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchSchoolActivity : AppCompatActivity(), SearchSchoolAdapter.ItemClickListener {

    private val list = MutableLiveData<ArrayList<ListDataSchool.ListDataSchoolItem>>()
    private lateinit var dataBinding: ActivitySearchSchoolBinding
    private lateinit var dataAdapter: SearchSchoolAdapter
    val token= SharedPrefManager.getInstance(this).Getuser.token

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivitySearchSchoolBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        actionBar?.setTitle("Cari School")
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        searchTeacherItem()
        dataBinding.btSearch.setOnClickListener {
            searchTeacherItem()
        }
        getUsers().observe(this, {
            if (it != null) {
                dataAdapter.setDataUser(it)
            }
        })


        dataAdapter = SearchSchoolAdapter(this)
        dataAdapter.notifyDataSetChanged()

        dataBinding.rvUsers.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL, false
                )
            setHasFixedSize(true)
            adapter = dataAdapter
        }

    }

    override fun onInviteClick(id: String) {
        val intent = Intent(applicationContext, DetailSchoolActivity::class.java)
        intent.putExtra("idschool", id)
        startActivity(intent)
    }


    private fun searchTeacherItem() {
        val query = dataBinding.editSearch.text.toString()
//        if (query.isEmpty()) return
        setUsers(query)
    }

    fun setUsers(query: String) {
        RetrofitClient.instanceUserApi.getSearchSchool(query, "Bearer " + token)
            .enqueue(object : Callback<ListDataSchool> {
                override fun onResponse(call: Call<ListDataSchool>, response: Response<ListDataSchool>) {
                    Log.d("JSON", response.toString())
                    if (response.isSuccessful) {
                        list.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ListDataSchool>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }
            })
    }

    fun getUsers(): LiveData<ArrayList<ListDataSchoolItem>> {
        return list
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}