package com.nusademy.nusademy.ui.tempteacherrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.developer.kalert.KAlertDialog
import com.nusademy.nusademy.dataapi.ListdataTemporaryRequest.ListdataTemporaryRequestItem
import com.nusademy.nusademy.databinding.ItemApplyrequestBinding
import com.nusademy.nusademy.ui.applytempteacher.TempTeacherRequestActivity

class TempTeacherRequestAdapter(val itemClickListener: TempTeacherRequestActivity, val enable:Boolean) : RecyclerView.Adapter<TempTeacherRequestAdapter.ListDataViewHolder>() {

    val dataAdapter = ArrayList<ListdataTemporaryRequestItem>()

    //    private lateinit var itemClickListener: ItemClickListener
    private lateinit var pDialog: KAlertDialog
    fun setDataItem(setItemData: ArrayList<ListdataTemporaryRequestItem>) {
        dataAdapter.clear()
        dataAdapter.addAll(setItemData)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onApproveClick(idrequest: String)
        fun onRejectClick(idrequest: String)
    }

    override fun onBindViewHolder(holder: ListDataViewHolder, position: Int) {
        holder.bind(dataAdapter[position])
    }

    override fun getItemCount(): Int = dataAdapter.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataViewHolder {
        return ListDataViewHolder(ItemApplyrequestBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ListDataViewHolder(val binding: ItemApplyrequestBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ListdataTemporaryRequestItem) {
//            Glide.with(itemView.context)
//                .load(dataUser.avatar)
//                .apply(RequestOptions().override(60, 60))
//                .into(binding.ivAvatarUrl)

            binding.tvRequestName.text = data.name.toString()
            binding.tvTeacherName.text = data.school.name.toString()
//            binding.root.setOnClickListener {
//
////                val bundle = Bundle()
////                bundle.putString("nameclass",data.name.toString())
////                bundle.putString("idclass",  data.id.toString())
//
//
//                val intent = Intent(itemView.context, SubjectActivity::class.java)
//                intent.putExtra("nameclass", data.name.toString())
//                intent.putExtra("idclass", data.id.toString())
////                intent.putExtras(bundle)
//                itemView.context.startActivity(intent)
//
//            }

            if (enable){
                binding.btApprove.isVisible=true
                binding.btReject.isVisible=true
            }else{
                binding.btApprove.isVisible=false
                binding.btReject.isVisible=false
            }

            binding.btApprove.setOnClickListener {
               itemClickListener.onApproveClick(data.id.toString())}

            binding.btReject.setOnClickListener {
                itemClickListener.onRejectClick(data.id.toString())}


            }
        }
    }
