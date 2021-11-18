package com.mohamed.halim.essa.cryptoexchange.ui.screens.settings

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mohamed.halim.essa.cryptoexchange.R
import com.mohamed.halim.essa.cryptoexchange.ui.components.SearchBox
import com.mohamed.halim.essa.cryptoexchange.utils.CurrencyDataUtils

@ExperimentalComposeUiApi
@Composable
fun RealCurrencySettings(navController: NavController, viewModel: SettingsViewModel) {
    val currencies = CurrencyDataUtils.cryptoCurrencyMap.values.filter { it.isCrypto == 0 }
    val query = viewModel.query.observeAsState("")
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = 12.dp,
        )
    }) {

        Column {
            SearchBox(query = query, changeQuery = {
                viewModel.changeQuery(it)
            })

            LazyColumn {
                items(currencies.filter {
                    query.value == "" ||
                    it.currencyId.toLowerCase().contains(query.value.toLowerCase()) ||
                    it.name.toLowerCase().contains(query.value.toLowerCase())
                }) { currency ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.changeRealCurrency(currency.currencyId)
                            viewModel.changeQuery("")
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
    }
}