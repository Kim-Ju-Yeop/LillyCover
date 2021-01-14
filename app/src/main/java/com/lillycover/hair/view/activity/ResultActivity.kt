package com.lillycover.hair.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.lillycover.hair.R
import com.lillycover.hair.base.view.BaseActivity
import com.lillycover.hair.databinding.ActivityResultBinding
import com.lillycover.hair.viewmodel.activity.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_result.*

@AndroidEntryPoint
class ResultActivity : BaseActivity<ActivityResultBinding, ResultViewModel>() {

    override val mViewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initChart()
    }

    override fun observerViewModel() {
        with(mViewModel) {
            diagnoseRepositoryImpl.onSuccessEvent.observe(this@ResultActivity, Observer {
                mViewModel.setChartData(it)
            })
            diagnoseRepositoryImpl.onErrorEvent.observe(this@ResultActivity, Observer {
                Toast.makeText(this@ResultActivity, "서버 통신 실패", Toast.LENGTH_SHORT).show()
            })
            onCompleteEvent.observe(this@ResultActivity, Observer {
                chart.data = it
                chart.invalidate()
            })
        }
    }

    private fun initChart() {

        // Chart 전체적인 설정
        chart.webAlpha = 100
        chart.webLineWidth = 1F
        chart.webLineWidth = 1F
        chart.webColor = resources.getColor(R.color.black)
        chart.webColorInner = resources.getColor(R.color.black)
        chart.isEnabled = false
        chart.description.isEnabled = false
        chart.setBackgroundColor(resources.getColor(R.color.white))
        chart.animateXY(1400, 1400, Easing.EaseInOutQuad)

        // Chart 바깥 항목 설정
        val xAxis = chart.xAxis
        xAxis.textSize = 15F
        xAxis.textColor = resources.getColor(R.color.black)
        xAxis.valueFormatter = object: IndexAxisValueFormatter() {
            val valueArr = arrayOf("각질", "홍조", "유분", "두께", "모공", "이마비율")
            override fun getFormattedValue(value: Float): String {
                return valueArr[value.toInt() % valueArr.size]
            }
        }

        // Chart 내부 항목 설정
        val yAxis = chart.yAxis
        yAxis.axisMinimum = 0F
        yAxis.axisMaximum = 80F
        yAxis.setDrawLabels(false)
        yAxis.setLabelCount(6, false)

        // Chart 데이터 설명 설정
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