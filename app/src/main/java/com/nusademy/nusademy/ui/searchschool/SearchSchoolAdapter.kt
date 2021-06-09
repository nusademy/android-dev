package com.nusademy.nusademy.ui.searchschool

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nusademy.nusademy.R
import com.nusademy.nusademy.dataapi.ListDataSchool
import com.nusademy.nusademy.databinding.ItemListSchoolBinding
import com.nusademy.nusademy.databinding.ItemListTeacherBinding


class SearchSchoolAdapter(val itemClickListener: SearchSchoolActivity) : RecyclerView.Adapter<SearchSchoolAdapter.ListUserViewHolder>() {

    val userAdapter = ArrayList<ListDataSchool.ListDataSchoolItem>()

    fun setDataUser(setUserData: ArrayList<ListDataSchool.ListDataSchoolItem>) {
        userAdapter.clear()
        userAdapter.addAll(setUserData)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onInviteClick(idteacher: String)
    }



    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(userAdapter[position])
    }

    override fun getItemCount(): Int = userAdapter.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(ItemListSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ListUserViewHolder(val binding: ItemListSchoolBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListDataSchool.ListDataSchoolItem) {
            Glide.with(itemView.context)
                .load(R.drawable.profile_null)
                .into(binding.ivAvatarSchool)

            binding.txtNameSchool.text = data.name
            binding.txtAddress.text=data.address.toString()
            binding.txtWeb.text=data.website

            binding.root.setOnClickListener {
                itemClickListener.onInviteClick(data.id.toString())}
            }





        }
    }