package com.example.youtubeapi.ui

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.databinding.ActivitySecondBinding

class SecondActivity : BaseActivity<BaseViewModel,ActivitySecondBinding>() {

    override val viewModel: BaseViewModel by lazy{
        ViewModelProvider(this).get(BaseViewModel::class.java)
    }


    override fun initViewModel() {
        super.initViewModel()
        if (intent != null) {
           Toast.makeText(this, intent.getStringExtra("channelId"), Toast.LENGTH_SHORT).show()
        }
    }


    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySecondBinding {
        return ActivitySecondBinding.inflate(inflater)
    }

}