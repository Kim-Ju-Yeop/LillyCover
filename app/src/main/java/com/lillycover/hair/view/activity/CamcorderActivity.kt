package com.lillycover.hair.view.activity

import android.graphics.SurfaceTexture
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityCamcorderBinding
import com.lillycover.hair.viewmodel.activity.CamcorderViewModel
import com.lillycover.hair.widget.etc.isLillyCoverSSID
import com.lillycover.hair.widget.extension.startActivityWithFinish
import com.lillycover.hair.widget.extension.startWifiSetting
import com.lillycover.hair.widget.util.AddressUtil
import com.lillycover.hair.widget.util.DiagnoseUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_camcorder.*
import org.videolan.libvlc.Media

@AndroidEntryPoint
class CamcorderActivity : BaseActivity<ActivityCamcorderBinding, CamcorderViewModel>() {

    override val mViewModel: CamcorderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setImageViewData()
        DiagnoseUtil.hairBitmapList.clear()
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
                if (!isLillyCoverSSID(applicationContext)) onBackPressed()
            })
            onCreateMediaPlayerEvent.observe(this@CamcorderActivity, Observer {
                textureview.keepScreenOn = true
                textureview.setSurfaceTexture(SurfaceTexture(false))

                iVLCVout.setVideoView(textureview)
                iVLCVout.attachViews()

                val media = Media(libVLC, Uri.parse(AddressUtil.RTSP_HOST))
                mediaPlayer.media = media
                mediaPlayer.play()
            })
            onTakeEvent.observe(this@CamcorderActivity, Observer {
                imageViewList[imageViewIdx].setImageBitmap(textureview.bitmap)
            })
            onRetakeEvent.observe(this@CamcorderActivity, Observer {
                imageViewList[imageViewIdx].setImageBitmap(null)
            })
            onCheckEvent.observe(this@CamcorderActivity, Observer {
                if (imageViewIdx != imageViewList.size-1) {
                    val bitmapDrawable = imageViewList[imageViewIdx].drawable as BitmapDrawable
                    DiagnoseUtil.hairBitmapList.add(bitmapDrawable.bitmap)
                    imageViewIdx++
                } else startWifiSetting()
            })
            onNextEvent.observe(this@CamcorderActivity, Observer {
                if (!isLillyCoverSSID(applicationContext)) {
                    val bitmapDrawable = imageViewList[imageViewIdx].drawable as BitmapDrawable
                    DiagnoseUtil.hairBitmapList.add(bitmapDrawable.bitmap)
                    startActivityWithFinish(CameraActivity::class.java)
                }
            })
        }
    }

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }

    private fun setImageViewData() {
        mViewModel.imageViewList.add(imageview1)
        mViewModel.imageViewList.add(imageview2)
        mViewModel.imageViewList.add(imageview3)
        mViewModel.imageViewList.add(imageview4)
        mViewModel.imageViewList.add(imageview5)
    }
}