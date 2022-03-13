package com.example.youtubeapi.ui.playlistDetail
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
import com.example.youtubeapi.core.uiBase.BaseViewModel
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapi.ui.video.VideoActivity
import com.example.youtubeapi.utils.Constant
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaylistDetailActivity : BaseActivity<BaseViewModel,ActivityPlaylistDetailBinding>() {

    override val viewModel: PlaylistDetailViewModel by viewModel()

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this){
            binding.progressBar.isVisible =it
        }
    }

    override fun initView() {
        super.initView()
        intent.getStringExtra(Constant.KEY_PLAYLIST_ID)?.let { getData(it) }
    }

    override fun checkInternet() {
        super.checkInternet()
        InternetConnection.init(applicationContext)
        InternetConnection.observe(this) { isConnected ->
            if (isConnected) {
                binding.apply {
                    titleTv.text = intent.getStringExtra(Constant.KEY_PLAYLIST_TITLE)
                    descTv.text = intent.getStringExtra(Constant.KEY_PLAYLIST_DESC)
                    checkInet.root.gone()
                    playlistRv.visible()
                    appBar.visible()
                }
            } else {
                binding.apply {
                    seriesTv.gone()
                    appBar.gone()
                    floatBtn.gone()
                    checkInet.root.visible()
                    playlistRv.gone()
                }
            }
        }
    }

    private fun getData(playlistId: String) {
        viewModel.getPlaylistItems(playlistId,null).observe(this){
            when(it.status){
                Status.SUCCESS->{
                    initRv( it.data)
                    val videoSeries = it.data?.pageInfo?.totalResults.toString() + " video series"
                     binding.seriesTv.text = videoSeries
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

    private fun initRv(data: Playlist?) {
        binding.playlistRv.layoutManager = LinearLayoutManager(this)
        binding.playlistRv.adapter =
            data?.items?.let {
                PlaylistDetailAdapter(it,this::clickListener) }
    }

    private  fun clickListener(videoId: String){
        Intent(this, VideoActivity::class.java).apply {
            putExtra(Constant.VIDEO_ID,videoId)
            startActivity(this)
        }
    }

    override fun initListener() {
        super.initListener()
        binding.checkInet.tryAgain.setOnClickListener {
            checkInternet()
        }
        binding.backTv.setOnClickListener {
            finish()
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(inflater)
    }

}