package com.lillycover.hair.viewmodel.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.lillycover.hair.R
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.network.model.request.SurveyReplyRequest
import com.lillycover.hair.network.model.response.SurveyQuestionResponse
import com.lillycover.hair.network.repositoryImpl.SurveyRepositoryImpl
import com.lillycover.hair.viewmodel.recyclerview.SurveyItemViewModel
import com.lillycover.hair.widget.navigator.SurveyItemNavigator
import com.lillycover.hair.widget.recyclerview.RecyclerViewItem
import com.lillycover.hair.widget.util.DiagnoseUtil
import java.sql.Timestamp

class SurveyViewModel @ViewModelInject constructor(
    val surveyRepositoryImpl: SurveyRepositoryImpl
) : BaseViewModel(), SurveyItemNavigator {

    val surveyItemList = MutableLiveData<ArrayList<RecyclerViewItem>>()

    init {
        surveyRepositoryImpl.getQuestion()
    }

    fun setSurveyItemList(surveyQuestionResponse: List<SurveyQuestionResponse>) {
        surveyItemList.value = surveyQuestionResponse.toRecyclerViewItemList() as ArrayList<RecyclerViewItem>
    }

    fun checkEvent() {
        with(DiagnoseUtil.surveySolutionArray) {
            val surveyReplyRequest = SurveyReplyRequest(1376, Timestamp(System.currentTimeMillis()),
                get(0), get(1), get(2), get(3), get(4), get(5), get(6),
                get(7), get(8), get(9), get(10), get(11), get(12), get(13),
                get(14), get(15), get(16), get(17), get(18), get(19), get(20), get(21))
            surveyRepositoryImpl.postReply(surveyReplyRequest)
        }
    }

    private fun List<SurveyQuestionResponse>.toRecyclerViewItemList() = map { it.toViewModel() }
    private fun SurveyQuestionResponse.toViewModel() = SurveyItemViewModel(this).toRecyclerViewItem()
    private fun SurveyItemViewModel.toRecyclerViewItem() = RecyclerViewItem(this, this@SurveyViewModel, R.layout.item_survey)

    override fun radioButtonEvent(buttonIdx: Int, surveyQuestionResponse: SurveyQuestionResponse) {
        DiagnoseUtil.surveySolutionArray[surveyQuestionResponse.idx-1] = buttonIdx
    }
}