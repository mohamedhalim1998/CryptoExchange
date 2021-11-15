package com.mohamed.halim.essa.cryptoexchange.ui.components

import android.content.res.Resources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriod

@Composable
fun Chip(modifier: Modifier = Modifier, historyPeriod: HistoryPeriod, checked: Boolean, onClick: () -> Unit) {
    Box(modifier = Modifier
        .padding(8.dp)
        .clickable {
            onClick.invoke()
        }) {
        Surface(
            elevation = 1.dp,
            shape = RoundedCornerShape(20.dp),
            color = if (checked) Color.LightGray else MaterialTheme.colors.secondary
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    historyPeriod.value,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.button.copy(color = Color.DarkGray)
                )
            }
        }
    }
}