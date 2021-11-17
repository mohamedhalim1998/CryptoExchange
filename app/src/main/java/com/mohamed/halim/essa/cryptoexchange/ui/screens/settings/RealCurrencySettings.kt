package com.mohamed.halim.essa.cryptoexchange.ui.screens.settings

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mohamed.halim.essa.cryptoexchange.utils.CurrencyDataUtils

@Composable
 fun RealCurrencySettings(navController: NavController) {
    LazyColumn {
        items(CurrencyDataUtils.cryptoCurrencyMap.values.filter { it.isCrypto == 0 }) { currency ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("REAL_CURRENCY", currency.currencyId)
                    navController.popBackStack()
                }
                .padding(8.dp)) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = currency.name
                )
            }
        }
    }
}