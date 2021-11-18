package com.mohamed.halim.essa.cryptoexchange.ui.screens.settings

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamed.halim.essa.cryptoexchange.R

@Composable
fun Settings(navController: NavController, viewModel: SettingsViewModel = hiltViewModel()) {
    val userPreferences = viewModel.userPreferences.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 12.dp
            )
        }
    ) {
        Card(modifier = Modifier
            .clickable {
                navController.navigate("SETTINGS/REAL_CURRENCY")
            }
            .fillMaxWidth()
            .padding(16.dp)) {
            Column(Modifier.padding(8.dp)) {
                Text("Exchange Currency")
                Text(userPreferences.value?.RealCurrency ?: "USD", fontSize = 16.sp)
            }
        }
    }
}