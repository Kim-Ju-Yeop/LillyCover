package com.lillycover.hair.viewmodel.activity

import androidx.hilt.lifecycle.ViewModelInject
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.network.repositoryImpl.SurveyRepositoryImpl

class SurveyViewModel @ViewModelInject constructor(
    val surveyRepositoryImpl: SurveyRepositoryImpl
) : BaseViewModel() {

    init {
        getQuestion()
    }

    fun getQuestion() {
        surveyRepositoryImpl.getQuestion()
    }
}