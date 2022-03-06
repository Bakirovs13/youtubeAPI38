package com.example.youtubeapi.ui.playlist

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.uiBase.BaseActivity
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.databinding.ActivityPlaylistBinding
import com.example.youtubeapi.ui.playlistDetail.PlaylistDetailActivity

class MyPlaylistActivity : BaseActivity<MyPlaylistViewModel,ActivityPlaylistBinding>() {


    override val viewModel: MyPlaylistViewModel by lazy{
        ViewModelProvider(this).get(MyPlaylistViewModel::class.java)
    }


    companion object{
        const val INTENT_KEY ="channelId"
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this){
            binding.progressBar.isVisible =it
        }
    }

    override fun initView() {
        super.initView()
        viewModel.getPlaylists().observe(this){
          when(it.status){
              Status.SUCCESS->{
                  initRv(it.data)
                  viewModel.loading.postValue(false)
              }
              Status.ERROR-> {
                  Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                  viewModel.loading.postValue(false)

              }
              Status.LOADING->{
                  viewModel.loading.postValue(true)

              }
          }
        }
    }

    private fun initRv(playlist: Playlist?) {
       binding.recyclerMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerMain.adapter =
            playlist?.items?.let {
                PlaylistAdapter(it,this::clickListener) }
    }

    private  fun clickListener(id: String){
        Intent(this@MyPlaylistActivity, PlaylistDetailActivity::class.java).apply {
            putExtra(INTENT_KEY,id)
            startActivity(this)
        }
    }


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(inflater)
    }


}