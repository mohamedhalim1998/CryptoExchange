package com.mohamed.halim.essa.cryptoexchange.ui.components

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mohamed.halim.essa.cryptoexchange.R

import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistory

private const val TAG = "RateGraph"

@Composable
fun Graph(rates: List<RateHistory>, darkTheme: Boolean) {
    val context = LocalContext.current
    val chart = setupChart(rates, context, darkTheme)
    AndroidView({ chart },
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        update = {
            it.data = LineData(setupDataset(rates, context))
            it.notifyDataSetChanged()
            it.invalidate()

        })
}

fun setupChart(rates: List<RateHistory>, context: Context, darkTheme: Boolean): LineChart {
    val chart = LineChart(context);
    chart.data = LineData(setupDataset(rates, context))
    chart.setBackgroundColor(if(darkTheme) Color.BLACK else Color.WHITE);
    chart.description.isEnabled = false
    chart.notifyDataSetChanged()
    chart.invalidate()
    chart.axisLeft.textColor = if(!darkTheme) Color.BLACK else Color.WHITE
    chart.axisLeft.setDrawGridLines(false);
    chart.axisRight.setDrawGridLines(false)
    chart.axisRight.setDrawAxisLine(false)
    chart.axisRight.setDrawLabels(false)
    chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    chart.xAxis.setDrawGridLines(false)
    chart.setPinchZoom(false)
    chart.setScaleEnabled(false)
    chart.setTouchEnabled(true)
    chart.xAxis.setDrawLabels(false)
    chart.legend.isEnabled = false
    chart.marker = TextMarkerView(context, R.layout.text_marker_view, darkTheme);
    return chart
}

fun setupDataset(rates: List<RateHistory>, context: Context): LineDataSet {
    val dataSet = LineDataSet(mapRateToEntries(rates), "Rate")
    dataSet.setDrawCircleHole(false)
    dataSet.setDrawValues(false)
    dataSet.setDrawFilled(false)
    dataSet.setDrawCircles(false)
    dataSet.setDrawHighlightIndicators(false)
    dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
    dataSet.lineWidth = 3F
    dataSet.color = ContextCompat.getColor(context, R.color.teal_alpha)
    dataSet.setDrawFilled(true)
    dataSet.fillDrawable = ContextCompat.getDrawable(context, R.drawable.graph_fill)
    return dataSet
}

fun mapRateToEntries(rates: List<RateHistory>): List<Entry> {
    return rates.mapIndexed { index, rateHistory ->
        Entry(
            index.toFloat(),
            rateHistory.rateClose.toFloat()
        )
    }
}
