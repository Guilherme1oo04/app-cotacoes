package com.example.appcotaes.ui.interfaces

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.appcotaes.utils.StateCoinViewModel

@Composable
fun CurrencyInformation(currencyMoedas: StateCoinViewModel) {

    if (currencyMoedas.loading) {
        CircularProgressIndicator()
    } else {
        Text(text = currencyMoedas.currencyInfoCoin.toString(), color = MaterialTheme.colorScheme.secondary)
    }

}