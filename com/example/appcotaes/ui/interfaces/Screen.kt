package com.example.appcotaes.ui.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcotaes.data.CoinModel
import com.example.appcotaes.utils.CoinsList
import com.example.appcotaes.utils.InternetDevice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Screen(internetDevice: InternetDevice, exitApp: () -> Unit) {
    var result by remember {
        mutableStateOf(internetDevice.internetAvailability())
    }

    Box(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        if (result) {
            var carregandoMoedas by remember {
                mutableStateOf(true)
            }
            var coinsList by remember {
                mutableStateOf(listOf<CoinModel>())
            }
            CoroutineScope(Dispatchers.Default).launch {
                val coins = CoinsList()
                coinsList = coins.loadCoins()
                carregandoMoedas = false
            }

            if (carregandoMoedas) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(modifier = Modifier.width(70.dp).height(70.dp), color = MaterialTheme.colorScheme.secondary)
                }
            } else {
                App(coinsList, exitApp)
            }
        } else {
            InternetErrorMessage() {
                result = internetDevice.internetAvailability()
            }
        }
    }
}