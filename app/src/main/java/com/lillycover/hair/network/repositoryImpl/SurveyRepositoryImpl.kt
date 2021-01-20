package com.lillycover.hair.network.repositoryImpl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.lillycover.hair.network.model.request.SurveyReplyRequest
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

    val onSuccessEventGetQuestion = MutableLiveData<List<SurveyQuestionResponse>>()
    val onSuccessEventPostReply = SingleLiveEvent<Unit>()
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
                    onSuccessEventGetQuestion.value = surveyQuestionResponseList
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

    override fun postReply(surveyReplyRequest: SurveyReplyRequest) {
        val host = AddressUtil.LILLYCOVER_HOST + "/SurveyReply.php"
        val requestQueue = Volley.newRequestQueue(context)

        val stringRequest = object: StringRequest(Method.POST, host, object: Response.Listener<String> {
            override fun onResponse(response: String?) {
                onSuccessEventPostReply.call()
            }
        }, object: Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                onErrorEvent.value = error
            }
        }) {
            override fun getParams(): MutableMap<String, String> {
                with(surveyReplyRequest) {
                    val params: MutableMap<String, String> = HashMap()
                    params["user_id"] = user_id.toString()
                    params["submit_time"] = submit_time.toString()
                    params["question1_solution"] = question1_solution.toString()
                    params["question2_solution"] = question2_solution.toString()
                    params["question3_solution"] = question3_solution.toString()
                    params["question4_solution"] = question4_solution.toString()
                    params["question5_solution"] = question5_solution.toString()
                    params["question6_solution"] = question6_solution.toString()
                    params["question7_solution"] = question7_solution.toString()
                    params["question8_solution"] = question8_solution.toString()
                    params["question9_solution"] = question9_solution.toString()
                    params["question10_solution"] = question10_solution.toString()
                    params["question11_solution"] = question11_solution.toString()
                    params["question12_solution"] = question12_solution.toString()
                    params["question13_solution"] = question13_solution.toString()
                    params["question14_solution"] = question14_solution.toString()
                    return params
                }
            }
        }
        requestQueue.add(stringRequest)
    }
}