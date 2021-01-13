package com.lillycover.hair.view.activity

import androidx.activity.viewModels
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivitySurveyBinding
import com.lillycover.hair.viewmodel.activity.SurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurveyActivity : BaseActivity<ActivitySurveyBinding, SurveyViewModel>() {

    override val mViewModel: SurveyViewModel by viewModels()

    override fun observerViewModel() {}
}