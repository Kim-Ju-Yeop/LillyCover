package com.lillycover.hair.view.fragment

import androidx.fragment.app.viewModels
import com.lillycover.hair.base.view.BaseFragment
import com.lillycover.hair.databinding.FragmentHomeBinding
import com.lillycover.hair.viewmodel.fragment.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val mViewModel: HomeViewModel by viewModels()

    override fun observerViewModel() {}
}