package com.mohamed.halim.essa.cryptoexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mohamed.halim.essa.cryptoexchange.ui.screens.cryptolist.CryptoList
import com.mohamed.halim.essa.cryptoexchange.ui.theme.CryptoExchangeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                CryptoList()
            }
        }
    }
}


@Composable
fun App(content: @Composable () -> Unit) {
    CryptoExchangeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App {
    }
}