package com.lillycover.hair.network.repositoryImpl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.lillycover.hair.network.model.response.SurveyQuestionResponse
import com.lillycover.hair.network.repository.SurveyRepository
import com.lillycover.hair.widget.SingleLiveEvent
import com.lillycover.hair.widget.util.AddressUtil
import dagger.hilt.android.qualifiers.ActivityContext
import org.json.JSONObject
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor(
    @ActivityContext private val context: Context
): SurveyRepository {

    val onSuccessEvent = MutableLiveData<List<SurveyQuestionResponse>>()
    val onErrorEvent = SingleLiveEvent<Throwable>()

    override fun getQuestion() {
        val host = AddressUtil.LILLYCOVER_HOST + "/SurveyQuestion.php"
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, host, null,
            object: Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject) {
                    val jsonArray = response.getJSONArray("data")
                    val surveyQuestionResponseList = ArrayList<SurveyQuestionResponse>()

                    for (i in 0 until jsonArray.length()) {
                        val data = jsonArray.getJSONObject(i)
                        with(data) {
                            val surveyQuestionResponse = SurveyQuestionResponse(
                                Integer.parseInt(getString("idx")),
                                Integer.parseInt(getString("type")),
                                getString("question"),
                                getString("item1"),
                                getString("item2"),
                                getString("item3"),
                                getString("item4"),
                                getString("item5")
                            )
                            surveyQuestionResponseList.add(surveyQuestionResponse)
                        }
                    }
                    onSuccessEvent.value = surveyQuestionResponseList
                }
            },
            object: Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    onErrorEvent.value = error
                }
            })
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        requestQueue.add(jsonObjectRequest)
    }
}