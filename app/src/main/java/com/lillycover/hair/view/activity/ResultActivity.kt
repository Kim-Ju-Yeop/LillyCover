package com.lillycover.hair.view.activity

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.lillycover.hair.R
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityResultBinding
import com.lillycover.hair.viewmodel.activity.ResultViewModel
import com.lillycover.hair.widget.extension.startActivityWithFinish
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_result.*

@AndroidEntryPoint
class ResultActivity : BaseActivity<ActivityResultBinding, ResultViewModel>() {

    override val mViewModel: ResultViewModel by viewModels()

    override fun observerViewModel() {
        with(mViewModel) {
            resultRepositoryImpl.onSuccessEvent.observe(this@ResultActivity, Observer {
                initChart()
                mViewModel.setChartData(it)
            })
            resultRepositoryImpl.onErrorEvent.observe(this@ResultActivity, Observer {
                onBackPressed()
            })
            chartData.observe(this@ResultActivity, Observer {
                chart.data = it
                chart.invalidate()
            })
        }
    }

    override fun onBackPressed() {
        startActivityWithFinish(MainActivity::class.java)
    }

    private fun initChart() {
        chart.webAlpha = 100
        chart.webLineWidth = 1F
        chart.webLineWidth = 1F
        chart.webColor = resources.getColor(R.color.black)
        chart.webColorInner = resources.getColor(R.color.black)
        chart.isEnabled = false
        chart.description.isEnabled = false
        chart.setBackgroundColor(resources.getColor(R.color.white))
        chart.animateXY(1400, 1400, Easing.EaseInOutQuad)

        val xAxis = chart.xAxis
        xAxis.textSize = 15F
        xAxis.textColor = resources.getColor(R.color.black)
        xAxis.valueFormatter = object: IndexAxisValueFormatter() {
            val valueArr = arrayOf("건성", "지성", "민감성", "이마비율", "모발건강", "탈모")
            override fun getFormattedValue(value: Float): String {
                return valueArr[value.toInt() % valueArr.size]
            }
        }

        val yAxis = chart.yAxis
        yAxis.axisMinimum = 0F
        yAxis.axisMaximum = 80F
        yAxis.setDrawLabels(false)
        yAxis.setLabelCount(6, false)

        val legend = chart.legend
        legend.xEntrySpace = 10F
        legend.yEntrySpace = 0F
        legend.textColor = resources.getColor(R.color.black)
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.setDrawInside(false)
    }
}