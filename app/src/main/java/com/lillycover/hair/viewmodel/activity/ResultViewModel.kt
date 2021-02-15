package com.lillycover.hair.viewmodel.activity

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.lillycover.hair.R
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.network.model.request.ResultRequest
import com.lillycover.hair.network.model.response.ResultResponse
import com.lillycover.hair.network.repositoryImpl.ResultRepositoryImpl
import com.lillycover.hair.widget.util.DiagnoseUtil
import com.lillycover.hair.widget.util.DiagnoseUtil.faceBitmap
import dagger.hilt.android.qualifiers.ActivityContext

class ResultViewModel @ViewModelInject constructor(
    @ActivityContext private val context: Context,
    val resultRepositoryImpl: ResultRepositoryImpl
) : BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val chartData = MutableLiveData<RadarData>()

    init {
        getResult()
        isLoading.value = true
    }

    fun setChartData(resultResponse: ResultResponse) {
        with(resultResponse) {
            val avgEntryList = ArrayList<RadarEntry>()
            avgEntryList.add(RadarEntry(50.toFloat()))
            avgEntryList.add(RadarEntry(50.toFloat()))
            avgEntryList.add(RadarEntry(50.toFloat()))
            avgEntryList.add(RadarEntry(50.toFloat()))
            avgEntryList.add(RadarEntry(50.toFloat()))
            avgEntryList.add(RadarEntry(50.toFloat()))

            val myEntryList = ArrayList<RadarEntry>()
            myEntryList.add(RadarEntry(vis_dry_scalp.toFloat()))
            myEntryList.add(RadarEntry(vis_oily_scalp.toFloat()))
            myEntryList.add(RadarEntry(vis_sensitive_scalp.toFloat()))
            myEntryList.add(RadarEntry(vis_forehead_ratio.toFloat()))
            myEntryList.add(RadarEntry(vis_thickness.toFloat()))
            myEntryList.add(RadarEntry(vis_hairloss.toFloat()))

            val avgDataSet = RadarDataSet(avgEntryList, context.resources.getString(R.string.text_avg_result))
            avgDataSet.color = context.resources.getColor(R.color.gray)
            avgDataSet.fillColor = context.resources.getColor(R.color.gray)
            avgDataSet.fillAlpha = 180
            avgDataSet.lineWidth = 2F
            avgDataSet.isHighlightEnabled = false
            avgDataSet.setDrawFilled(true)

            val userDataSet = RadarDataSet(myEntryList, context.resources.getString(R.string.text_my_result))
            userDataSet.color = context.resources.getColor(R.color.design_default_color_primary)
            userDataSet.fillColor = context.resources.getColor(R.color.design_default_color_primary)
            userDataSet.fillAlpha = 180
            userDataSet.lineWidth = 2F
            userDataSet.isHighlightEnabled = false
            userDataSet.setDrawFilled(true)

            val dataSetList = ArrayList<IRadarDataSet>()
            dataSetList.add(avgDataSet)
            dataSetList.add(userDataSet)

            val data = RadarData(dataSetList)
            data.setDrawValues(false)

            isLoading.value = false
            chartData.value = data
        }
    }

    private fun getResult() {
        with(DiagnoseUtil.hairBitmapList) {
            val resultRequest = ResultRequest("김주엽", 1376, get(0), get(1), get(2), get(3), get(4), faceBitmap.value!!)
            resultRepositoryImpl.getResult(resultRequest)
        }
    }
}