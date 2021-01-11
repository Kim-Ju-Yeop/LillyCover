package com.lillycover.hair.view.activity

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityCamcorderBinding
import com.lillycover.hair.viewmodel.activity.CamcorderViewModel
import com.lillycover.hair.widget.extension.startActivityWithFinish
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CamcorderActivity : BaseActivity<ActivityCamcorderBinding, CamcorderViewModel>() {

    override val mViewModel: CamcorderViewModel by viewModels()

    override fun observerViewModel() {
        with(mViewModel) {
            onLostEvent.observe(this@CamcorderActivity, Observer {
                onBackPressed()
            })
        }
    }

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }
}