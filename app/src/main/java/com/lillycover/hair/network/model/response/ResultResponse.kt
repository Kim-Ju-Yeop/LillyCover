package com.lillycover.hair.network.model.response

data class ResultResponse(
    val cnt: Int,
    val vis_dry_scalp: Double,
    val vis_oily_scalp: Double,
    val vis_sensitive_scalp: Double,
    val vis_forehead_ratio: Double,
    val vis_scalp_con: Double,
    val vis_hair_con: Double,
    val vis_hairloss: Double
)