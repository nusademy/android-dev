package com.nusademy.ui.applyschoolTeacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nusademy.nusademy.R
import com.nusademy.nusademy.databinding.FragmentWaitingBinding

class WaitingFragment : Fragment(R.layout.item_apply_school_waiting) {

    private lateinit var dataAdapter: ApplySchoolAdapter
    private lateinit var waiting: String
    private var dataBindingS: FragmentWaitingBinding? = null
    private val dataBinding get() = dataBindingS!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        waiting = args?.getString(ApplySchoolActivity.extra_name).toString()
        dataBindingS = FragmentWaitingBinding.bind(view)

        dataAdapter = ApplySchoolAdapter()
        dataAdapter.notifyDataSetChanged()

        dataBinding.rvWaiting.apply {
            layoutManager =
                    LinearLayoutManager(
                            context,
                            LinearLayoutManager.VERTICAL, false
                    )
            setHasFixedSize(true)
            adapter = dataAdapter
        }

    }
}