package com.lillycover.hair.network.model.response

data class SurveyQuestionResponse(
    val idx: Int,
    val type: Int,
    val question: String,
    val item1: String,
    val item2: String,
    val item3: String,
    val item4: String,
    val item5: String
)