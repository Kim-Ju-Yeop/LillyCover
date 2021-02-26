package com.lillycover.hair.network.repository

import com.lillycover.hair.network.model.request.SurveyReplyRequest

interface SurveyRepository {

    fun getQuestion()

    fun postReply(surveyReplyRequest: SurveyReplyRequest)
}