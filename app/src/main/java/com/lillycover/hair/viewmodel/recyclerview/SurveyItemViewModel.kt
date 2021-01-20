package com.lillycover.hair.viewmodel.recyclerview

import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.network.model.response.SurveyQuestionResponse

class SurveyItemViewModel(
    val surveyQuestionResposne: SurveyQuestionResponse
) : BaseViewModel() {

    var questionText: String
    var item1Text: String
    var item2Text: String
    var item3Text: String
    var item4Text: String
    var item5Text: String

    init {
        with(surveyQuestionResposne) {
            questionText = question
            item1Text = if (item1 == "null") "" else item1
            item2Text = if (item2 == "null") "" else item2
            item3Text = if (item3 == "null") "" else item3
            item4Text = if (item4 == "null") "" else item4
            item5Text = if (item5 == "null") "" else item5
        }
    }
}