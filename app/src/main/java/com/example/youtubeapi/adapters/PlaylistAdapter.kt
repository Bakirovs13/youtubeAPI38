package com.example.youtubeapi.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.data.models.Items
import com.example.youtubeapi.databinding.ItemPlaylistRvBinding

class PlaylistAdapter(private val list:List<Items>,private val listener:OnItemClickListener): RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    lateinit var binding:ItemPlaylistRvBinding

    inner class ViewHolder(itemView: ItemPlaylistRvBinding)
        :RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(items: Items) {
            Glide.with(binding.root)
                .load(items.snippet.thumbnails.default.url)
                .into(binding.imageEv)
            binding.playlistNameTv.text = items.snippet.title
            binding.playlistCountTv.text = items.contentDetails.itemCount.toString() + "video series"
            itemView.setOnClickListener{
                listener.onItemClick(items.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPlaylistRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int  = list.size

    interface OnItemClickListener{
        fun onItemClick(id : String)
    }
}