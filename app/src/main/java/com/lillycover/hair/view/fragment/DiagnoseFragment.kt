package com.lillycover.hair.view.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lillycover.hair.base.view.BaseFragment
import com.lillycover.hair.databinding.FragmentDiagnoseBinding
import com.lillycover.hair.view.activity.CamcorderActivity
import com.lillycover.hair.viewmodel.fragment.DiagnoseViewModel
import com.lillycover.hair.widget.etc.isAllPermisionGranted
import com.lillycover.hair.widget.etc.isLillyCoverSSID
import com.lillycover.hair.widget.extension.startActivity
import com.lillycover.hair.widget.extension.startWifiSetting
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiagnoseFragment : BaseFragment<FragmentDiagnoseBinding, DiagnoseViewModel>() {

    override val mViewModel: DiagnoseViewModel by viewModels()

    override fun observerViewModel() {
        with(mViewModel) {
            onWifiSettingEvent.observe(this@DiagnoseFragment, Observer {
                startWifiSetting()
            })
        }
    }

    override fun onResume() {
        super.onResume()

        if (isAllPermisionGranted(requireContext(), requireActivity()) && isLillyCoverSSID(requireContext())) {
            startActivity(CamcorderActivity::class.java)
        }
    }
}