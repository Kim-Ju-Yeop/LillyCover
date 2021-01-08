package com.lillycover.hair.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lillycover.hair.R
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityMainBinding
import com.lillycover.hair.viewmodel.activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.fragment)
        bottom_nav.setupWithNavController(navController)
    }

    override fun observerViewModel() {}
}