package com.mohamed.halim.essa.cryptoexchange.ui.screens.crptodetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

private const val TAG = "CryptoDetails"

@Composable
fun CryptoDetails(cryptoId: String) {
    Text(text = cryptoId)
}