package com.lillycover.hair.viewmodel.fragment

import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.widget.SingleLiveEvent

class DiagnoseViewModel : BaseViewModel() {

    val onWifiSettingEvent = SingleLiveEvent<Unit>()

    fun wifiSettingEvent() {
        onWifiSettingEvent.call()
    }
}