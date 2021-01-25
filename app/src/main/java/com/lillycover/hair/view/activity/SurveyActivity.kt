package com.lillycover.hair.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivitySurveyBinding
import com.lillycover.hair.viewmodel.activity.SurveyViewModel
import com.lillycover.hair.widget.extension.startActivityWithFinish
import com.lillycover.hair.widget.util.DiagnoseUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurveyActivity : BaseActivity<ActivitySurveyBinding, SurveyViewModel>() {

    override val mViewModel: SurveyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiagnoseUtil.surveySolutionArray = arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    }

    override fun observerViewModel() {
        with(mViewModel) {
            surveyRepositoryImpl.onSuccessEventGetQuestion.observe(this@SurveyActivity, Observer {
                setSurveyItemList(it)
            })
            surveyRepositoryImpl.onSuccessEventPostReply.observe(this@SurveyActivity, Observer {
                startActivityWithFinish(ResultActivity::class.java)
            })
            surveyRepositoryImpl.onErrorEvent.observe(this@SurveyActivity, Observer {
                onBackPressed()
            })
        }
    }

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }
}