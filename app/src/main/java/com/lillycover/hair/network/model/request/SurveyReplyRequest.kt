package com.lillycover.hair.network.model.request

import java.sql.Timestamp

data class SurveyReplyRequest(
    val user_id: Int,
    val submit_time: Timestamp,
    val question1_solution: Int,
    val question2_solution: Int,
    val question3_solution: Int,
    val question4_solution: Int,
    val question5_solution: Int,
    val question6_solution: Int,
    val question7_solution: Int,
    val question8_solution: Int,
    val question9_solution: Int,
    val question10_solution: Int,
    val question11_solution: Int,
    val question12_solution: Int,
    val question13_solution: Int,
    val question14_solution: Int,
    val question15_solution: Int,
    val question16_solution: Int,
    val question17_solution: Int,
    val question18_solution: Int,
    val question19_solution: Int,
    val question20_solution: Int,
    val question21_solution: Int,
    val question22_solution: Int,
)