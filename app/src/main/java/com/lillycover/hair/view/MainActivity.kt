package com.lillycover.hair.view

import androidx.activity.viewModels
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityMainBinding
import com.lillycover.hair.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun observerViewModel() {}
}