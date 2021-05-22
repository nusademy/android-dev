package com.nusademy.ui.schoolrecommendationTeacher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nusademy.nusademy.databinding.ItemsSchoolRecommendationBinding

class SchoolRecommendationAdapter : RecyclerView.Adapter<SchoolRecommendationAdapter.ListUserViewHolder>() {

    val teacherAdapter = ArrayList<ItemSchoolRecommendation>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setDataTeacher(setTeacherData: ArrayList<ItemSchoolRecommendation>) {
        teacherAdapter.clear()
        teacherAdapter.addAll(setTeacherData)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemSchoolRecommendation)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(teacherAdapter[position])
    }

    override fun getItemCount(): Int = teacherAdapter.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(ItemsSchoolRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ListUserViewHolder(val binding: ItemsSchoolRecommendationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataSchool: ItemSchoolRecommendation) {
            Glide.with(itemView.context)
                    .load(dataSchool.logoSchool)
                    .apply(RequestOptions().override(60, 60))
                    .into(binding.ivLogoSchool)

            binding.txtNameSchool.text = dataSchool.nameSchool
            binding.txtStatusSchool.text = dataSchool.statusSchool
            binding.txtDateSchool.text = dataSchool.dateSchool

            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(dataSchool)
            }
        }
    }
}