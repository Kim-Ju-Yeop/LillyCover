package com.lillycover.hair.view.activity

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityCamcorderBinding
import com.lillycover.hair.viewmodel.activity.CamcorderViewModel
import com.lillycover.hair.widget.extension.startActivityWithFinish
import com.lillycover.hair.widget.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_camcorder.*
import org.videolan.libvlc.Media

@AndroidEntryPoint
class CamcorderActivity : BaseActivity<ActivityCamcorderBinding, CamcorderViewModel>() {

    override val mViewModel: CamcorderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.surfaceHolder = surfaceview.holder
        mViewModel.surfaceHolder.setKeepScreenOn(true)
    }
    override fun onResume() {
        super.onResume()
        mViewModel.createMediaPlayer()
    }
    override fun onDestroy() {
        super.onDestroy()
        mViewModel.releaseMediaPlayer()
    }

    override fun observerViewModel() {
        with(mViewModel) {
            onLostEvent.observe(this@CamcorderActivity, Observer {
                onBackPressed()
            })
            onCreateMediaPlayerEvent.observe(this@CamcorderActivity, Observer {
                iVLCVout.setVideoView(surfaceview)
                iVLCVout.attachViews()

                val media = Media(libVLC, Uri.parse(Constants.RTSP_HOST))
                mediaPlayer.media = media
                mediaPlayer.play()
            })
        }
    }

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }
}