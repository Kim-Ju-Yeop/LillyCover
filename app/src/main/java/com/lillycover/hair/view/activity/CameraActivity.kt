package com.lillycover.hair.view.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityCameraBinding
import com.lillycover.hair.viewmodel.activity.CameraViewModel
import com.lillycover.hair.widget.extension.startActivityWithFinish
import com.lillycover.hair.widget.util.DiagnoseUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_camera.*

@AndroidEntryPoint
class CameraActivity : BaseActivity<ActivityCameraBinding, CameraViewModel>() {

    override val mViewModel: CameraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiagnoseUtil.faceBitmap.value = null
    }

    override fun observerViewModel() {
        with(mViewModel) {
            onTakeEvent.observe(this@CameraActivity, Observer {
                camerakitview.captureImage { cameraKitView, byteArray ->
                    val matrix = Matrix()
                    matrix.setScale(-1F, 1F)

                    var bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                    imageview.setImageBitmap(bitmap)
                }
            })
            onRetakeEvent.observe(this@CameraActivity, Observer {
                imageview.setImageBitmap(null)
            })
            onCheckEvent.observe(this@CameraActivity, Observer {
                val bitmapDrawable = imageview.drawable as BitmapDrawable
                DiagnoseUtil.faceBitmap.value = bitmapDrawable.bitmap
                startActivityWithFinish(SurveyActivity::class.java)
            })
        }
    }

    override fun onStart() {
        super.onStart()
        camerakitview.onStart()
    }
    override fun onResume() {
        super.onResume()
        camerakitview.onResume()
    }
    override fun onPause() {
        super.onPause()
        camerakitview.onPause()
    }
    override fun onStop() {
        super.onStop()
        camerakitview.onStop()
    }

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }
}