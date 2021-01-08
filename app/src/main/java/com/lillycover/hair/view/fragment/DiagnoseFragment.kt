package com.lillycover.hair.view.fragment

import androidx.fragment.app.viewModels
import com.lillycover.hair.base.view.BaseFragment
import com.lillycover.hair.databinding.FragmentDiagnoseBinding
import com.lillycover.hair.viewmodel.fragment.DiagnoseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiagnoseFragment : BaseFragment<FragmentDiagnoseBinding, DiagnoseViewModel>() {

    override val mViewModel: DiagnoseViewModel by viewModels()

    override fun observerViewModel() {}
}