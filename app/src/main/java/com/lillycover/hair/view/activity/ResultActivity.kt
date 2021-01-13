package com.lillycover.hair.view.activity

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityResultBinding
import com.lillycover.hair.viewmodel.activity.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : BaseActivity<ActivityResultBinding, ResultViewModel>() {

    override val mViewModel: ResultViewModel by viewModels()

    override fun observerViewModel() {
        with(mViewModel) {
            diagnoseRepositoryImpl.onSuccessEvent.observe(this@ResultActivity, Observer {

            })
            diagnoseRepositoryImpl.onErrorEvent.observe(this@ResultActivity, Observer {

            })
        }
    }
}