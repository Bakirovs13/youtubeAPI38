package com.example.youtubeapi.ui.Playlist

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.adapters.PlaylistAdapter
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.databinding.ActivityMainBinding
import com.example.youtubeapi.ui.SecondActivity

class PlaylistActivity : BaseActivity<PlaylistViewModel,ActivityMainBinding>() {


    override val viewModel: PlaylistViewModel by lazy{
        ViewModelProvider(this).get(PlaylistViewModel::class.java)
    }

    override fun initView() {
        super.initView()
        viewModel.playlists().observe(this){
           // Toast.makeText(this, it.kind, Toast.LENGTH_SHORT).show()
            initRv(it)
        }
    }

    private fun initRv(playlist: Playlist?) {
        binding.recyclerMain.adapter =
            PlaylistAdapter(playlist!!.items, object : PlaylistAdapter.OnItemClickListener {
                override fun onItemClick(id: String) {
                    Intent(this@PlaylistActivity, SecondActivity::class.java).apply {
                        putExtra("channelId",id)
                        startActivity(this)
                    }
                }

            })
    }


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }


}