package com.mohamed.halim.essa.cryptoexchange.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mohamed.halim.essa.cryptoexchange.R
import com.mohamed.halim.essa.cryptoexchange.ui.components.SearchBox
import com.mohamed.halim.essa.cryptoexchange.utils.CurrencyDataUtils

@ExperimentalComposeUiApi
@Composable
fun CryptoSettings(navController: NavController, viewModel: SettingsViewModel) {
    val query = viewModel.query.observeAsState("")
    val currencies = CurrencyDataUtils.cryptoCurrencyMap.values.filter { it.isCrypto == 1 }.filter {
        query.value == "" ||
                it.currencyId.toLowerCase().contains(query.value.toLowerCase()) ||
                it.name.toLowerCase().contains(query.value.toLowerCase())
    }
    val checked = currencies.map {
        mutableStateOf(viewModel.containCryptoCurrency(it.currencyId))
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 12.dp,
            actions = {
                IconButton(onClick = {
                    viewModel.changeCryptoCurrencies()
                    navController.popBackStack()
                }) {
                    Icon(Icons.Filled.Done, "")
                }
            }
        )
    }) {
        Column {
            SearchBox(query = query, changeQuery = {
                viewModel.changeQuery(it)
            })

            LazyColumn {
                itemsIndexed(currencies) { index, currency ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                checked[index].value = !checked[index].value
                                if (checked[index].value) {
                                    viewModel.addCryptoCurrency(currency.currencyId)
                                } else {
                                    viewModel.deleteCryptoCurrency(currency.currencyId)
                                }
                            }
                    ) {
                        Row(
                            Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1F, true),
                                text = currency.name
                            )
                            Checkbox(
                                checked = checked[index].value,
                                onCheckedChange = { check ->
                                    checked[index].value = check
                                    if (check) {
                                        viewModel.addCryptoCurrency(currency.currencyId)
                                    } else {
                                        viewModel.deleteCryptoCurrency(currency.currencyId)
                                    }
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}