package com.lillycover.hair.view.activity

import androidx.activity.viewModels
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityCamcorderBinding
import com.lillycover.hair.viewmodel.activity.CamcorderViewModel
import com.lillycover.hair.widget.extension.startActivityWithFinish

class CamcorderActivity : BaseActivity<ActivityCamcorderBinding, CamcorderViewModel>() {

    override val mViewModel: CamcorderViewModel by viewModels()

    override fun observerViewModel() {}

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }
}