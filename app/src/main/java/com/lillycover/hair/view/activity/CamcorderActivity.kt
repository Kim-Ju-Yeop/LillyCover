package com.lillycover.hair.view.activity

import android.graphics.SurfaceTexture
import android.net.Uri
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
                textureview.keepScreenOn = true
                textureview.setSurfaceTexture(SurfaceTexture(false))

                iVLCVout.setVideoView(textureview)
                iVLCVout.attachViews()

                val media = Media(libVLC, Uri.parse(Constants.TEST_HOST))
                mediaPlayer.media = media
                mediaPlayer.play()
            })
            onTakeEvent.observe(this@CamcorderActivity, Observer {
                imageview1.setImageBitmap(textureview.bitmap)
            })
        }
    }

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }
}