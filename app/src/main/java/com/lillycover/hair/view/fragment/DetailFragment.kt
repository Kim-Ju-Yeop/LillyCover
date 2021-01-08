package com.lillycover.hair.view.fragment

import androidx.fragment.app.viewModels
import com.lillycover.hair.base.view.BaseFragment
import com.lillycover.hair.databinding.FragmentDetailBinding
import com.lillycover.hair.viewmodel.fragment.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val mViewModel: DetailViewModel by viewModels()

    override fun observerViewModel() {}
}