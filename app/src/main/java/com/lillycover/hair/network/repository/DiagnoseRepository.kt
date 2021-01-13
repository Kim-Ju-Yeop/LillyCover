package com.lillycover.hair.network.repository

import com.lillycover.hair.network.model.DiagnoseRequestModel

interface DiagnoseRepository {
    fun getResult(diagnoseRequestModel: DiagnoseRequestModel)
}