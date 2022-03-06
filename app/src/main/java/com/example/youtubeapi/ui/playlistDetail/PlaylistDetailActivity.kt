package com.example.youtubeapi.ui.playlistDetail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.core.uiBase.BaseActivity
import com.example.youtubeapi.core.uiBase.BaseViewModel
import com.example.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapi.ui.playlist.MyPlaylistActivity.Companion.INTENT_KEY

class PlaylistDetailActivity : BaseActivity<BaseViewModel,ActivityPlaylistDetailBinding>() {

    override val viewModel: BaseViewModel by lazy{
        ViewModelProvider(this).get(BaseViewModel::class.java)
    }


    override fun initViewModel() {
        super.initViewModel()
        if (intent != null) {
           Toast.makeText(this, intent.getStringExtra(INTENT_KEY), Toast.LENGTH_SHORT).show()
        }
    }


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(inflater)
    }

}