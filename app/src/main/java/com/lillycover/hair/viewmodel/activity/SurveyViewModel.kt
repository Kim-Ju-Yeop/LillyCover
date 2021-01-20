package com.lillycover.hair.viewmodel.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.lillycover.hair.R
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.network.model.response.SurveyQuestionResponse
import com.lillycover.hair.network.repositoryImpl.SurveyRepositoryImpl
import com.lillycover.hair.viewmodel.recyclerview.SurveyItemViewModel
import com.lillycover.hair.widget.navigator.SurveyItemNavigator
import com.lillycover.hair.widget.recyclerview.RecyclerViewItem
import com.lillycover.hair.widget.util.DiagnoseUtil

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
        // 설문내용 DB 연결
    }

    private fun List<SurveyQuestionResponse>.toRecyclerViewItemList() = map { it.toViewModel() }
    private fun SurveyQuestionResponse.toViewModel() = SurveyItemViewModel(this).toRecyclerViewItem()
    private fun SurveyItemViewModel.toRecyclerViewItem() = RecyclerViewItem(this, this@SurveyViewModel, R.layout.item_survey)

    override fun radioButtonEvent(buttonIdx: Int, surveyQuestionResponse: SurveyQuestionResponse) {
        with(surveyQuestionResponse) {
            DiagnoseUtil.surveySolutionArray[idx-1] = buttonIdx
        }
    }
}