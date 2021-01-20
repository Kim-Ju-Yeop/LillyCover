package com.lillycover.hair.network.repositoryImpl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.lillycover.hair.network.model.request.ResultRequest
import com.lillycover.hair.network.model.response.ResultResponse
import com.lillycover.hair.network.repository.ResultRepository
import com.lillycover.hair.widget.SingleLiveEvent
import com.lillycover.hair.widget.etc.bitmapToBase64
import com.lillycover.hair.widget.util.AddressUtil
import dagger.hilt.android.qualifiers.ActivityContext
import org.json.JSONObject
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    @ActivityContext private val context: Context
) : ResultRepository {

    val onSuccessEvent = MutableLiveData<ResultResponse>()
    val onErrorEvent = SingleLiveEvent<Throwable>()

    override fun getResult(resultRequest: ResultRequest) {
        with(resultRequest) {
            val requestQueue = Volley.newRequestQueue(context)
            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("file0", bitmapToBase64(file0))
            jsonObject.put("file1", bitmapToBase64(file1))
            jsonObject.put("file2", bitmapToBase64(file2))
            jsonObject.put("file3", bitmapToBase64(file3))
            jsonObject.put("file4", bitmapToBase64(file4))
            jsonObject.put("file5", bitmapToBase64(file5))

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, AddressUtil.ALGORITH_HOST, jsonObject,
                object: Response.Listener<JSONObject> {
                    override fun onResponse(response: JSONObject) {
                        with(response) {
                            onSuccessEvent.value = ResultResponse(
                                Integer.parseInt(getString("cnt")),
                                Integer.parseInt(getString("hair_cnt_avg")),
                                Integer.parseInt(getString("hairloss")),
                                Integer.parseInt(getString("keratin")),
                                Integer.parseInt(getString("oil")),
                                Integer.parseInt(getString("pore_area_ratio")),
                                Integer.parseInt(getString("redness")),
                            )
                        }
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
}