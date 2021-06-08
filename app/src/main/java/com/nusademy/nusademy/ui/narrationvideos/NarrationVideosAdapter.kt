package com.nusademy.nusademy.ui.narrationvideos

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nusademy.nusademy.dataapi.ListDataVideos
import com.nusademy.nusademy.databinding.ItemVideoList2Binding


class NarrationVideosAdapter : RecyclerView.Adapter<NarrationVideosAdapter.ListUserViewHolder>() {

    val userAdapter = ArrayList<ListDataVideos.ListDataVideosItem>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setDataUser(setUserData: ArrayList<ListDataVideos.ListDataVideosItem>) {
        userAdapter.clear()
        userAdapter.addAll(setUserData)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListDataVideos.ListDataVideosItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(userAdapter[position])
    }

    override fun getItemCount(): Int = userAdapter.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(ItemVideoList2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ListUserViewHolder(val binding: ItemVideoList2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListDataVideos.ListDataVideosItem) {
//            Glide.with(itemView.context)
//                .load(dataUser.avatar)
//                .apply(RequestOptions().override(60, 60))
//                .into(binding.ivAvatarUrl)

            binding.tvVideoTitle2.text = data.title.toString()
            Glide.with(itemView.context)
                .load("https://i.ytimg.com/vi/${data.url.toString()}/maxresdefault.jpg")
                .into(binding.imgThumb)
            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, VideoPlayerActivity::class.java)
                intent.putExtra("idvideo", data.url.toString())
//                intent.putExtras(bundle)
                itemView.context.startActivity(intent)
            }
        }
    }
}