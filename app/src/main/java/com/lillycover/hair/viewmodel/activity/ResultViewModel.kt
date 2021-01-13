package com.lillycover.hair.viewmodel.activity

import androidx.hilt.lifecycle.ViewModelInject
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.network.model.DiagnoseRequestModel
import com.lillycover.hair.network.repositoryImpl.DiagnoseRepositoryImpl
import com.lillycover.hair.widget.util.DiagnoseUtil
import com.lillycover.hair.widget.util.DiagnoseUtil.faceBitmap

class ResultViewModel @ViewModelInject constructor(
    val diagnoseRepositoryImpl: DiagnoseRepositoryImpl
) : BaseViewModel() {

    init {
        getResult()
    }

    private fun getResult() {
        with(DiagnoseUtil.hairBitmapList) {
            val diagnoseRequestModel = DiagnoseRequestModel("김주엽", get(0), get(1), get(2), get(3), get(4), faceBitmap.value!!)
            diagnoseRepositoryImpl.getResult(diagnoseRequestModel)
        }
    }
}