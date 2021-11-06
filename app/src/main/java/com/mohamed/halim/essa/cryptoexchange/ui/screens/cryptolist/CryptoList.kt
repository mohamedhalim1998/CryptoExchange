package com.mohamed.halim.essa.cryptoexchange.ui.screens.cryptolist

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamed.halim.essa.cryptoexchange.ui.components.CryptoCard

private const val TAG = "CryptoList"

@Composable
fun CryptoList(navController: NavController, viewModel: CryptoListViewModel = hiltViewModel()) {
    val rates = viewModel.cryptoRates.observeAsState(listOf())
    Scaffold {
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