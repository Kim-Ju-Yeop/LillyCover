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
import com.lillycover.hair.network.model.DiagnoseRequestModel
import com.lillycover.hair.network.model.DiagnoseResponseModel
import com.lillycover.hair.network.repositoryImpl.DiagnoseRepositoryImpl
import com.lillycover.hair.widget.util.DiagnoseUtil
import com.lillycover.hair.widget.util.DiagnoseUtil.faceBitmap
import dagger.hilt.android.qualifiers.ActivityContext

class ResultViewModel @ViewModelInject constructor(
    @ActivityContext private val context: Context,
    val diagnoseRepositoryImpl: DiagnoseRepositoryImpl
) : BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val chartData = MutableLiveData<RadarData>()

    init {
        getResult()
        isLoading.value = true
    }

    private fun getResult() {
        with(DiagnoseUtil.hairBitmapList) {
            val diagnoseRequestModel = DiagnoseRequestModel("김주엽", get(0), get(1), get(2), get(3), get(4), faceBitmap.value!!)
            diagnoseRepositoryImpl.getResult(diagnoseRequestModel)
        }
    }

    fun setChartData(diagnoseResponseModel: DiagnoseResponseModel) {
        with(diagnoseResponseModel) {
            val avgEntryList = ArrayList<RadarEntry>()
            avgEntryList.add(RadarEntry(60.toFloat()))
            avgEntryList.add(RadarEntry(60.toFloat()))
            avgEntryList.add(RadarEntry(60.toFloat()))
            avgEntryList.add(RadarEntry(60.toFloat()))
            avgEntryList.add(RadarEntry(60.toFloat()))
            avgEntryList.add(RadarEntry(60.toFloat()))

            val myEntryList = ArrayList<RadarEntry>()
            myEntryList.add(RadarEntry(keratin.toFloat()))
            myEntryList.add(RadarEntry(redness.toFloat()))
            myEntryList.add(RadarEntry(oil.toFloat()))
            myEntryList.add(RadarEntry(hairCntAvg.toFloat()))
            myEntryList.add(RadarEntry(poreAreaRatio.toFloat()))
            myEntryList.add(RadarEntry(hairLoss.toFloat()))

            val avgDataSet = RadarDataSet(avgEntryList, context.resources.getString(R.string.text_avg_result))
            avgDataSet.color = context.resources.getColor(R.color.gray)
            avgDataSet.fillColor = context.resources.getColor(R.color.gray)
            avgDataSet.fillAlpha = 100
            avgDataSet.lineWidth = 2F
            avgDataSet.setDrawFilled(true)

            val userDataSet = RadarDataSet(myEntryList, context.resources.getString(R.string.text_my_result))
            userDataSet.color = context.resources.getColor(R.color.design_default_color_primary)
            userDataSet.fillColor = context.resources.getColor(R.color.design_default_color_primary)
            userDataSet.fillAlpha = 100
            userDataSet.lineWidth = 2F
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
}