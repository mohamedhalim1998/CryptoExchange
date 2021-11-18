package com.mohamed.halim.essa.cryptoexchange.ui.screens.crptodetails


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mohamed.halim.essa.cryptoexchange.ui.components.Chip
import com.mohamed.halim.essa.cryptoexchange.ui.components.Graph
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriod
import com.mohamed.halim.essa.cryptoexchange.utils.IconsData


private const val TAG = "CryptoDetails"

@Composable
fun CryptoDetails(
    navController: NavController,
    cryptoId: String,
    viewModel: CryptoDetailsViewModel = hiltViewModel()
) {
    val rateHistory = viewModel.rateHistory.observeAsState(listOf())
    val currentRate = viewModel.currentRate.observeAsState()
    val currentPeriod = viewModel.currentPeriod.observeAsState()
    if (currentPeriod.value == null) {
        viewModel.getCryptoHistoryHour(cryptoId)
        viewModel.getCurrentRate(cryptoId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentRate.value?.name ?: cryptoId)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 12.dp
            )
        }
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1F, true),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberImagePainter(
                        IconsData.icons[cryptoId]
                            ?: IconsData.icons["USD"]!!
                    ),
                    contentDescription = cryptoId,
                    modifier = Modifier
                        .weight(1F, true)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )
                Row(Modifier.padding(horizontal = 16.dp)) {
                    Column(
                        Modifier
                            .weight(1.0f, true)
                            .padding(PaddingValues(start = 16.dp))
                    ) {
                        Text(text = currentRate.value?.name ?: "")
                        Text(text = cryptoId)
                    }

                    Text(
                        text = "%.2f $".format(currentRate.value?.rate)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F, true)
            ) {
                if (rateHistory.value.isNotEmpty()) {
                    Log.d(TAG, "CryptoDetails: ${currentPeriod.value}")
                    Graph(rateHistory.value)
                } else {
                    CircularProgressIndicator()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Chip(
                    modifier = Modifier.weight(1F, true),
                    historyPeriod = HistoryPeriod.ONE_HOUR,
                    checked = currentPeriod.value == HistoryPeriod.ONE_HOUR,
                    onClick = {
                        viewModel.getCryptoHistoryHour(cryptoId)
                    })
                Chip(
                    modifier = Modifier.weight(1F, true),
                    historyPeriod = HistoryPeriod.TWELVE_HOUR,
                    checked = currentPeriod.value == HistoryPeriod.TWELVE_HOUR,
                    onClick = {
                        viewModel.getCryptoHistory12Hour(cryptoId)
                    })
                Chip(
                    modifier = Modifier.weight(1F, true),
                    historyPeriod = HistoryPeriod.ONE_DAY,
                    checked = currentPeriod.value == HistoryPeriod.ONE_DAY,
                    onClick = {
                        viewModel.getCryptoHistoryDay(cryptoId)
                    })

            }


        }

    }
}