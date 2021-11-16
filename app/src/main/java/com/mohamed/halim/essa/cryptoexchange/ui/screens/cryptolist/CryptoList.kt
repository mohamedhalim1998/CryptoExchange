package com.mohamed.halim.essa.cryptoexchange.ui.screens.cryptolist

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamed.halim.essa.cryptoexchange.ui.components.CryptoCard
import com.mohamed.halim.essa.cryptoexchange.R

private const val TAG = "CryptoList"

@Composable
fun CryptoList(navController: NavController, viewModel: CryptoListViewModel = hiltViewModel()) {
    val rates = viewModel.cryptoRates.observeAsState(listOf())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 12.dp,
                actions = {
                    IconButton(onClick = {
                        navController.navigate("SETTINGS")
                    }) {
                        Icon(Icons.Filled.Settings, "")
                    }
                }
            )
        }
    ) {
        LazyColumn {
            items(rates.value) { crypto ->
                Log.d(TAG, "CryptoList: $crypto")
                CryptoCard(cryptoCurrency = crypto) {
                    navController.navigate("CRYPTO_DETAILS/${crypto.cryptoId}")
                }
            }
        }
    }
}