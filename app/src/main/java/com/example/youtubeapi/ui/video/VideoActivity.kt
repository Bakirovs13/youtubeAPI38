package com.example.youtubeapi.ui.video

import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.example.youtubeapi.core.extensions.gone
import com.example.youtubeapi.core.extensions.visible
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.uiBase.BaseActivity
import com.example.youtubeapi.databinding.ActivityVideoBinding
import com.example.youtubeapi.ui.InternetConnection
import com.example.youtubeapi.utils.Constant
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseActivity<VideoViewModel,ActivityVideoBinding>() {

    override val viewModel: VideoViewModel by viewModel()


    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this){
            binding.progressBar.isVisible =it
        }

        intent.getStringExtra(Constant.VIDEO_ID)?.let { id->
            viewModel.getVideos(id).observe(this){
                when(it.status){
                    Status.SUCCESS-> {
                        viewModel.loading.postValue(false)
                        binding.titleTv.text = it.data!!.items[0].snippet.title
                        binding.description.text = it.data.items[0].snippet.description
                    }
                    Status.ERROR->
                        viewModel.loading.postValue(false)

                    Status.LOADING->{
                        viewModel.loading.postValue(true)
                    }
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.backTv.setOnClickListener {
            finish()
        }
    }



    override fun checkInternet() {
        super.checkInternet()
        InternetConnection.init(applicationContext)
        InternetConnection.observe(this){isConnected->
            if(isConnected){
                binding.checkInet.root.gone()
                binding.scroll.visible()
                initViewModel()
            }else{
               binding.checkInet.root.visible()
               binding.scroll.gone()
            }
        }

    }


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(inflater)
    }
}