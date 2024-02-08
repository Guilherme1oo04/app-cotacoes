package com.example.appcotaes.ui.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appcotaes.utils.StateCoinViewModel
import kotlin.math.round

@Composable
fun CurrencyInformation(currencyMoedas: StateCoinViewModel, nomeMoeda1: String, nomeMoeda2: String, moedaNaoExiste: () -> Unit) {
    
    if(!currencyMoedas.combinacaoExiste) {
        AlertDialog(
            onDismissRequest = { moedaNaoExiste() }, 
            confirmButton = { 
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { moedaNaoExiste() }) {
                        Text(text = "OK")
                    }
                }
            },
            title = {
                Text(text = "Erro!", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            },
            text = {
                Text(
                    text = "Não conseguimos encontrar as informações referentes à essa combinação de moedas",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            
        )
    }

    if (currencyMoedas.currencyInfoCoin == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.size(100.dp))
        }
    } else {
        
        val precoArredondado = round(currencyMoedas.currencyInfoCoin!!.bid.toFloat())

        Column {
            
        }
    }

}