package com.nusademy.ui.applyschoolTeacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nusademy.nusademy.R
import com.nusademy.nusademy.databinding.FragmentAcceptedOrRejectedBinding

class AcceptedFragment : Fragment(R.layout.item_apply_school) {

    private lateinit var dataAdapter: ApplySchoolAdapter
    private lateinit var accepted : String
    private var dataBindingS: FragmentAcceptedOrRejectedBinding? = null
    private val dataBinding get() = dataBindingS!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        accepted = args?.getString(ApplySchoolActivity.extra_name).toString()
        dataBindingS = FragmentAcceptedOrRejectedBinding.bind(view)

        dataAdapter = ApplySchoolAdapter()
        dataAdapter.notifyDataSetChanged()

        dataBinding.rvAcceptedOrRejected.apply {
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