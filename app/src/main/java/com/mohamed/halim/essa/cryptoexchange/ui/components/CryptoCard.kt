package com.mohamed.halim.essa.cryptoexchange.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mohamed.halim.essa.cryptoexchange.data.domain.CryptoCurrency

@Composable
fun CryptoCard(cryptoCurrency: CryptoCurrency, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(cryptoCurrency.icon),
                contentDescription = cryptoCurrency.name,
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp),
                contentScale = ContentScale.FillWidth
            )
            Column (Modifier.weight(1.0f, true).padding(PaddingValues(start = 16.dp))){
                Text(text = cryptoCurrency.name)
                Text(text = cryptoCurrency.cryptoId)
            }
            Text(text = "%.2f $".format(cryptoCurrency.rate))
        }

    }
}