package com.lillycover.hair.viewmodel.recyclerview

import androidx.lifecycle.MutableLiveData
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.network.model.response.SurveyQuestionResponse
import com.lillycover.hair.widget.util.DiagnoseUtil

class SurveyItemViewModel(
    val surveyQuestionResposne: SurveyQuestionResponse
) : BaseViewModel() {

    var questionText: String
    var item1Text: String
    var item2Text: String
    var item3Text: String
    var item4Text: String
    var item5Text: String

    var item1Checked = MutableLiveData<Boolean>()
    var item2Checked = MutableLiveData<Boolean>()
    var item3Checked = MutableLiveData<Boolean>()
    var item4Checked = MutableLiveData<Boolean>()
    var item5Checked = MutableLiveData<Boolean>()

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

    fun checkedUpdate() {
        when(DiagnoseUtil.surveySolutionArray[surveyQuestionResposne.idx-1]) {
            1 -> item1Checked.value = true
            2 -> item2Checked.value = true
            3 -> item3Checked.value = true
            4 -> item4Checked.value = true
            5 -> item5Checked.value = true
        }
    }
}