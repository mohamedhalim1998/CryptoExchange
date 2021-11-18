package com.mohamed.halim.essa.cryptoexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.mohamed.halim.essa.cryptoexchange.ui.screens.crptodetails.CryptoDetails
import com.mohamed.halim.essa.cryptoexchange.ui.screens.cryptolist.CryptoList
import com.mohamed.halim.essa.cryptoexchange.ui.screens.settings.CryptoSettings
import com.mohamed.halim.essa.cryptoexchange.ui.screens.settings.RealCurrencySettings
import com.mohamed.halim.essa.cryptoexchange.ui.screens.settings.Settings
import com.mohamed.halim.essa.cryptoexchange.ui.screens.settings.SettingsViewModel
import com.mohamed.halim.essa.cryptoexchange.ui.theme.CryptoExchangeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "CRYPTO_LIST") {
                    composable("CRYPTO_LIST") { CryptoList(navController) }
                    composable("SETTINGS") { Settings(navController) }
                    composable("SETTINGS/REAL_CURRENCY") {
                        val parentEntry = remember {
                            navController.getBackStackEntry("SETTINGS")
                        }
                        val parentViewModel = hiltViewModel<SettingsViewModel>(parentEntry)
                        RealCurrencySettings(
                            navController,
                            parentViewModel
                        )
                    }
                    composable(
                        "CRYPTO_DETAILS/{currency_id}"
                    ) { backStackEntry ->
                        CryptoDetails(
                            navController,
                            backStackEntry.arguments?.getString("currency_id")!!
                        )
                    }
                }

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