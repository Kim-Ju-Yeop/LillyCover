package com.lillycover.hair.view.activity

import androidx.activity.viewModels
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityCameraBinding
import com.lillycover.hair.viewmodel.activity.CameraViewModel
import com.lillycover.hair.widget.extension.startActivityWithFinish
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraActivity : BaseActivity<ActivityCameraBinding, CameraViewModel>() {

    override val mViewModel: CameraViewModel by viewModels()

    override fun observerViewModel() {}

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }
}