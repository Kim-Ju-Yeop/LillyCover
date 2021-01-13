package com.lillycover.hair.network.model

data class DiagnoseResponseModel(
    val cnt: Int,
    val hairCntAvg: Int,
    val hairLoss: Int,
    val keratin: Int,
    val oil: Int,
    val poreAreaRatio: Int,
    val redness: Int
)