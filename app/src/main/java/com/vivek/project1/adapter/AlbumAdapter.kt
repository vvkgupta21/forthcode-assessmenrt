package com.vivek.project1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vivek.project1.databinding.AlbumListItemLayoutBinding
import com.vivek.project1.loadImage
import com.vivek.project1.models.AlbumResponseModelItem

class AlbumAdapter(private var getList: ArrayList<AlbumResponseModelItem>) :
    RecyclerView.Adapter<AlbumAdapter.Holder>() {

    class Holder(val binding: AlbumListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(
            AlbumListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model = getList[position]
        holder.binding.albumIdText.text = model.id.toString()
        holder.binding.albumTitleText.text = model.title
        loadImage(holder.binding.albumImage, model.thumbnailUrl)
    }

    override fun getItemCount() = getList.size

    fun initList(initialList: ArrayList<AlbumResponseModelItem>) {
        getList = initialList
        notifyDataSetChanged()
    }

}