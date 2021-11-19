package com.mohamed.halim.essa.cryptoexchange.ui.components

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import android.widget.TextView
import com.github.mikephil.charting.data.Entry
import com.mohamed.halim.essa.cryptoexchange.R

private const val TAG = "TextMarkerView"

class TextMarkerView(context: Context, layoutResource: Int,private val darkTheme: Boolean = false) :
    MarkerView(context, layoutResource) {

    private val tvContent: TextView = findViewById(R.id.text_marker)
    override fun refreshContent(e: Entry, highlight: Highlight?) {
        tvContent.text = e.y.toString()
        tvContent.setTextColor(if (!darkTheme) Color.BLACK else Color.WHITE)
        tvContent.setBackgroundColor(if(darkTheme) Color.BLACK else Color.WHITE)
        super.refreshContent(e, highlight)
    }

    private var mOffset: MPPointF? = null

    override fun getOffset(): MPPointF? {
        if (mOffset == null) {
            // center the marker horizontally and vertically
            mOffset =
                MPPointF((-(width / 2)).toFloat(), (-height * 2).toFloat())
        }
        return mOffset
    }
}