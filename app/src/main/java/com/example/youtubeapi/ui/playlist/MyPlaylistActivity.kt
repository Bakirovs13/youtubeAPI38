package com.example.youtubeapi.ui.playlist

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.core.extensions.gone
import com.example.youtubeapi.core.extensions.visible
import com.example.youtubeapi.ui.InternetConnection
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.uiBase.BaseActivity
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.databinding.ActivityPlaylistBinding
import com.example.youtubeapi.ui.playlistDetail.PlaylistDetailActivity
import com.example.youtubeapi.utils.Constant
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPlaylistActivity : BaseActivity<MyPlaylistViewModel,ActivityPlaylistBinding>() {


    override val viewModel: MyPlaylistViewModel by viewModel()


    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this){
            binding.progressBar.isVisible =it
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        InternetConnection.init(applicationContext)
        InternetConnection.observe(this){ isConnected->
            if (isConnected){
                binding.networkLayout.root.gone()
                binding.recyclerMain.visible()
                initView()
            }else{
                binding.networkLayout.root.visible()
                binding.recyclerMain.gone()
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.networkLayout.tryAgain.setOnClickListener {
            checkInternet()
        }
    }

    override fun initView() {
        super.initView()
        viewModel.getPlaylists().observe(this){
          when(it.status){
              Status.SUCCESS->{
                  initRv(it.data )
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

    private  fun clickListener(id: String,title:String,desc:String){
        Intent(this@MyPlaylistActivity, PlaylistDetailActivity::class.java).apply {
            putExtra(Constant.KEY_PLAYLIST_ID,id)
            putExtra(Constant.KEY_PLAYLIST_TITLE,title)
            putExtra(Constant.KEY_PLAYLIST_DESC,desc)
            startActivity(this)
        }
    }


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(inflater)
    }


}