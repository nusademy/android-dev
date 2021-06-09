package com.nusademy.nusademy.ui.requestschool

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developer.kalert.KAlertDialog
import com.nusademy.nusademy.dataapi.RetrofitClient
import com.nusademy.nusademy.databinding.ActivityRequestSchoolBinding
import com.nusademy.nusademy.storage.SharedPrefManager
import com.nusademy.nusademy.R
import com.nusademy.nusademy.R.layout
import com.nusademy.nusademy.dataapi.ListDataClasses
import com.nusademy.nusademy.dataapi.ListDataClasses.ListDataClassesItem
import com.nusademy.nusademy.dataapi.ListDataGuestRequest.ListDataGuestRequestItem
import com.nusademy.nusademy.dataapi.ListdataTemporaryRequest.ListdataTemporaryRequestItem
import com.nusademy.nusademy.databinding.ActivitySearchTeacherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Calendar

class RequestSchoolActivity : AppCompatActivity() {
    var listclasses= mutableListOf("")
    var idschool =""
    var dateteaching=""
    var dateteaching2=""
    var idclass=""
    var idclass2=""
    private lateinit var binding: ActivityRequestSchoolBinding
    val token= SharedPrefManager.getInstance(this).Getuser.token
    val idteacher= SharedPrefManager.getInstance(this).Getuser.idteacher
    val iduser= SharedPrefManager.getInstance(this).Getuser.id
    var generic=true
    private val list = MutableLiveData<ArrayList<ListDataClassesItem>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestSchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        actionBar?.setTitle("Permohonan Mengajar")
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
//        idschool = intent.getStringExtra("idschool").toString()
        idschool = intent.getStringExtra("idschool").toString()
        GetListClass()
        binding.btSendGuest.setOnClickListener {
            getItems().observe(this, {
                if (it != null) {
                    it.forEachIndexed { index, listDataClassesItem ->
                        if (binding.menuAutocomplete.text.toString()==listDataClassesItem.name){
                            idclass=listDataClassesItem.id.toString()
                        }
                    }
                }
            })

            if(generic==false){
                ADDGuestRequest(
                    binding.editGsName.text.toString(),
                    binding.editGsDesc.text.toString(),
                    dateteaching,
                    binding.editGsTimeStart.text.toString(),
                    binding.editGsTimeEnd.text.toString(),
                    binding.editGsNote.text.toString(),
                    iduser,
                    idschool,
                    idclass,
                    "Specific",
                    "Requested",
                    "Teacher"
                )
            } else {
                ADDGuestRequest(
                    binding.editGsName.text.toString(),
                    binding.editGsDesc.text.toString(),
                    dateteaching,
                    binding.editGsTimeStart.text.toString(),
                    binding.editGsTimeEnd.text.toString(),
                    binding.editGsNote.text.toString(),
                    iduser,
                    idschool,
                    null,
                    "General",
                    "Requested",
                    "Teacher"
                )
                Log.d("subject","CEK -$idclass")
            }



       }
        binding.btSendTemp.setOnClickListener {
            getItems().observe(this, {
                if (it != null) {
                    it.forEachIndexed { index, listDataClassesItem ->
                        if (binding.menuAutocomplete2.text.toString()==listDataClassesItem.name){
                            idclass2=listDataClassesItem.id.toString()
                        }

                    }
                }
            })

            ADDTempRequest(
                binding.editTmName.text.toString(),
                binding.editTmDuration.text.toString(),
                dateteaching2,
                idclass2,
                iduser,
                idschool,
                "Requested",
                "Teacher"
            )
       }
        binding.btGsDate.setOnClickListener { clickDataPicker() }
        binding.btTmDate.setOnClickListener { clickDataPicker2() }
        binding.btGsTimeStart.setOnClickListener {
            clickTimePickerstart()
        }
        binding.btGsTimeEnd.setOnClickListener {
            clickTimePickerend()
        }
        binding.toggleButtonGroup2.check(R.id.bt_gs_generic)
        binding.toggleButtonGroup2.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.bt_gs_generic ->{
                        binding.dropdownkelas.isVisible=false
                        generic=true
                    }
                    R.id.bt_gs_specific ->{
                        binding.dropdownkelas.isVisible=true
                        generic=false
                    }
                }
            } else {

            }
        }
        binding.toggleButtonGroup.check(R.id.bt_guest)
        binding.toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.bt_guest -> {
                        binding.lyTemp.isVisible = false
                        binding.lyGuest.isVisible = true
                    }
                    R.id.bt_temporary -> {
                        binding.lyTemp.isVisible = true
                        binding.lyGuest.isVisible = false
                    }
                }
            } else {
            }
        }




        val adapter = ArrayAdapter(this, layout.list_item, listclasses)
        binding.menuAutocomplete.setAdapter(adapter)
        binding.menuAutocomplete2.setAdapter(adapter)

    }


    fun clickDataPicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            binding.editGsDate.setText("${twodigit((dayOfMonth).toString())}-${twodigit((monthOfYear + 1).toString())}-$year")
            dateteaching="$year-${twodigit((monthOfYear + 1).toString())}-${twodigit((dayOfMonth).toString())}"
        }, year, month, day)
        dpd.show()
    }
    fun clickDataPicker2() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            binding.editTmDate.setText("${twodigit((dayOfMonth).toString())}-${twodigit((monthOfYear + 1).toString())}-$year")
            dateteaching2="$year-${twodigit((monthOfYear + 1).toString())}-${twodigit((dayOfMonth).toString())}"
        }, year, month, day)
        dpd.show()
    }

    fun clickTimePickerstart() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        val df = DecimalFormat("##")
        df.roundingMode = RoundingMode.CEILING

        val tpd = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
            binding.editGsTimeStart.setText(twodigit(h.toString()) + ":" + twodigit(m.toString())  +":00")
        }),hour,minute,true)
        tpd.show()
    }

    fun clickTimePickerend() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)


        val tpd = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
            binding.editGsTimeEnd.setText(twodigit(h.toString()) + ":" + twodigit(m.toString())  +":00")
        }),hour,minute,true)
        tpd.show()
    }

     fun twodigit(num:String):String{
        var str=""
        if(num.length==1){
            str="0$num"
        }else{
            str=num
        }
        return str;
    }

    // Get List Class From API ----------------------------------------------------------------------------------------
    fun GetListClass(){
        setItems(idschool, token)
        getItems().observe(this, {
            if (it != null) {
                listclasses.clear()
                it.forEachIndexed { index, listDataClassesItem ->listclasses.add(listDataClassesItem.name)}
            }
        })
    }
    fun setItems(id: String, token: String) {
        val pDialog = KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
        pDialog.show()
        RetrofitClient.instanceUserApi.getClasses(id, "school.id", "Bearer " + token)
            .enqueue(object : Callback<ListDataClasses> {
                override fun onResponse(call: Call<ListDataClasses>, response: Response<ListDataClasses>) {
                    Log.d("JSON", response.toString())
                    if (response.isSuccessful) {
                        list.postValue(response.body())
                        pDialog.dismissWithAnimation()
                    }
                }

                override fun onFailure(call: Call<ListDataClasses>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                    pDialog.dismissWithAnimation()
                }
            })
    }
    fun getItems(): LiveData<ArrayList<ListDataClassesItem>> {
        return list
    }




    // Function ADDGuestRequest API -----------------------------------------------------------------------------------------
    fun ADDGuestRequest(
        name: String,
        description: String,
        dateteach: String,
        timestart: String,
        timeend: String,
        notes: String,
        idteacher: String,
        idschool: String,
        idclass: String?,
        target: String,
        status: String,
        createdby:String,) {
        val pDialog = KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
        pDialog.changeAlertType(KAlertDialog.PROGRESS_TYPE)
        pDialog.show()
        RetrofitClient.instanceUserApi.addGuestRequest(
            "Bearer " + token,
            name,
            description,
            dateteach,
            timestart,
            timeend,
            notes,
            idteacher,
            idschool,
            idclass,
            target,
            status,
            createdby
        )
            .enqueue(object : Callback<ListDataGuestRequestItem> {
                override fun onResponse(
                    call: Call<ListDataGuestRequestItem>,
                    response: Response<ListDataGuestRequestItem>
                ) {
                    Log.d("subject",response.toString())
                    Log.d("subject",response.errorBody().toString())
                    if (response.isSuccessful) {
                        // Saat response sukses finnish activity (menutup/mengakhiri activity editprofile)

                        pDialog.changeAlertType(KAlertDialog.SUCCESS_TYPE)
                        pDialog.setTitleText("Berhasil")
                        pDialog.setContentText("Permintaan  $name berhasil diajukan")
                        pDialog.setConfirmText("OK")
                        pDialog.setConfirmClickListener { sDialog ->
                            sDialog.dismissWithAnimation()
                            finish()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Gagal Cek kembali Isian", Toast.LENGTH_SHORT).show()
                        pDialog.dismissWithAnimation()
                    }
                }

                override fun onFailure(calls: Call<ListDataGuestRequestItem>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())
                    pDialog.dismissWithAnimation()
                }
            })
    }

    // Function ADDTempRequest API -----------------------------------------------------------------------------------------
    fun ADDTempRequest(
        name: String,
        durations: String?,
        dateteach: String?,
        idclass: String,
        idteacher: String,
        idschool: String,
        status: String,
        createdby: String) {
        val pDialog = KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
        pDialog.changeAlertType(KAlertDialog.PROGRESS_TYPE)
        pDialog.show()
        RetrofitClient.instanceUserApi.addTempRequest(
            "Bearer " + token,
            name,
            durations,
            dateteach,
            idclass,
            idteacher,
            idschool,
            status,
            createdby,
        )
            .enqueue(object : Callback<ListdataTemporaryRequestItem> {
                override fun onResponse(
                    call: Call<ListdataTemporaryRequestItem>,
                    response: Response<ListdataTemporaryRequestItem>
                ) {
                    Log.d("temp",response.toString())
                    Log.d("temp",response.errorBody().toString())
                    if (response.isSuccessful) {
                        // Saat response sukses finnish activity (menutup/mengakhiri activity editprofile)

                        pDialog.changeAlertType(KAlertDialog.SUCCESS_TYPE)
                        pDialog.setTitleText("Berhasil")
                        pDialog.setContentText("Permintaan  $name berhasil diajukan")
                        pDialog.setConfirmText("OK")
                        pDialog.setConfirmClickListener { sDialog ->
                            sDialog.dismissWithAnimation()
                            finish()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Gagal Cek kembali Isian", Toast.LENGTH_SHORT).show()
                        pDialog.dismissWithAnimation()
                    }
                }

                override fun onFailure(calls: Call<ListdataTemporaryRequestItem>, ts: Throwable) {
                    Log.d("Error", ts.message.toString())
                    pDialog.dismissWithAnimation()
                }
            })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
