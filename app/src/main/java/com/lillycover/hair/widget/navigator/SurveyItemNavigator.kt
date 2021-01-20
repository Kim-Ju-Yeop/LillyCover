package com.lillycover.hair.widget.navigator

import com.lillycover.hair.network.model.response.SurveyQuestionResponse

interface SurveyItemNavigator {
    fun radioButtonEvent(buttonIdx: Int, surveyQuestionResponse: SurveyQuestionResponse)
}