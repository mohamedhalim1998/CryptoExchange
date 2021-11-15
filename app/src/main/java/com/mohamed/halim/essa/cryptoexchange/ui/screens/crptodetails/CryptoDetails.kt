package com.mohamed.halim.essa.cryptoexchange.ui.screens.crptodetails


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.mohamed.halim.essa.cryptoexchange.ui.components.Chip
import com.mohamed.halim.essa.cryptoexchange.ui.components.Graph
import com.mohamed.halim.essa.cryptoexchange.utils.IconsData


private const val TAG = "CryptoDetails"

@Composable
fun CryptoDetails(cryptoId: String, viewModel: CryptoDetailsViewModel = hiltViewModel()) {
    val rateHistory = viewModel.rateHistory.observeAsState(listOf())
    val currentRate = viewModel.currentRate.observeAsState(null)
    viewModel.getCryptoHistoryHour(cryptoId)
    viewModel.getCurrentRate(cryptoId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentRate.value?.name ?: cryptoId)
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                },
                backgroundColor = Color.Blue,
                contentColor = Color.White,
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
                    modifier = Modifier.weight(1F, true),
                    contentScale = ContentScale.Fit
                )
                Row {
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
                    Graph(rateHistory.value)
                } else {
                    CircularProgressIndicator()
                }
            }


        }

    }
}