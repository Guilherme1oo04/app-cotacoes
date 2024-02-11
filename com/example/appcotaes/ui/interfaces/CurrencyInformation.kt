package com.example.appcotaes.ui.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcotaes.utils.StateCoinViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(inputFormat: String, outputFormat: String, dateToFormat: String): String {
    val inputFormatInstance = SimpleDateFormat(inputFormat, Locale.getDefault())
    val outputFormatInstance = SimpleDateFormat(outputFormat, Locale.getDefault())
    val date: Date? = inputFormatInstance.parse(dateToFormat)
    return date?.let { outputFormatInstance.format(it) } ?: ""
}

@OptIn(ExperimentalMaterial3Api::class)
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
                    Button(onClick = { moedaNaoExiste() }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)) {
                        Text(text = "OK", fontSize = 19.sp, color = MaterialTheme.colorScheme.tertiary)
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

    if (currencyMoedas.currencyInfoCoin != null) {

        val currencyInfoCoin = currencyMoedas.currencyInfoCoin!!

        val precoArredondado = when {
            currencyInfoCoin.bid.toDouble() < 0.01 -> BigDecimal(currencyInfoCoin.bid).setScale(4, RoundingMode.DOWN)
            else -> BigDecimal(currencyInfoCoin.bid).setScale(2, RoundingMode.DOWN)
        }

        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                text = "1 $nomeMoeda1 equivale a",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    shadow = Shadow(
                        color = MaterialTheme.colorScheme.primary,
                        offset = Offset(2f, 2f),
                        blurRadius = 0f
                    )
                )
            )
            Text(
                text = "$precoArredondado $nomeMoeda2",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp),
                style = TextStyle(
                    shadow = Shadow(
                        color = MaterialTheme.colorScheme.primary,
                        offset = Offset(2f, 2f),
                        blurRadius = 0f
                    )
                )
            )

            Divider(modifier = Modifier.padding(top = 10.dp))

            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.secondary) {
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Conversão de valores",
                        fontSize = 25.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.primary,
                                offset = Offset(2f, 2f),
                                blurRadius = 0f
                            )
                        ),
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        var valorMoeda1 by remember {
                            mutableStateOf("1")
                        }
                        var valorMoeda2 by remember {
                            mutableStateOf(precoArredondado.toString())
                        }
                        Column {
                            val focusManager = LocalFocusManager.current
                            Text(
                                text = nomeMoeda1
                            )
                            OutlinedTextField(
                                value = TextFieldValue(
                                    text = valorMoeda1,
                                    selection = TextRange(valorMoeda1.length),
                                ),
                                onValueChange = { newValue ->
                                    if(newValue.text.isEmpty()) {
                                        valorMoeda1 = "0"
                                    } else {
                                        if(newValue.text.matches(Regex("^[+]?(\\d+\\.?\\d*|\\.\\d+)\$"))) {
                                            if(newValue.text.matches(Regex("^0[1-9]\\d*\$"))){
                                                valorMoeda1 = newValue.text.trimStart { it == '0' }
                                            } else {
                                                valorMoeda1 = newValue.text
                                            }
                                        }
                                    }
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Decimal,
                                    imeAction = ImeAction.Done
                                ),
                                singleLine = true,
                                keyboardActions = KeyboardActions(
                                    onAny = {focusManager.clearFocus()}
                                ),
                                modifier = Modifier
                                    .width(120.dp)
                            )

                        }
                        Column {
                            Text(
                                text = nomeMoeda2
                            )
                            OutlinedTextField(
                                value = TextFieldValue(
                                    text = valorMoeda2,
                                    selection = TextRange(valorMoeda2.length),
                                ),
                                onValueChange = { newValue ->
                                    if(newValue.text.isEmpty()) {
                                        valorMoeda2 = "0"
                                    } else {
                                        if(newValue.text.matches(Regex("^[+]?(\\d+\\.?\\d*|\\.\\d+)\$"))) {
                                            if(newValue.text.matches(Regex("^0[1-9]\\d*\$"))){
                                                valorMoeda2 = newValue.text.trimStart { it == '0' }
                                            } else {
                                                valorMoeda2 = newValue.text
                                            }
                                        }
                                    }
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Decimal,
                                    imeAction = ImeAction.Done
                                ),
                                singleLine = true,
                                modifier = Modifier.width(120.dp)
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val date = formatDate("yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy - HH:mm:ss", currencyInfoCoin.create_date)
                Text(
                    text = "Dados do câmbio",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 25.sp
                )
                Text(
                    text = "Última atualização $date",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 12.sp
                )
            }
        }

    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.size(100.dp))
        }
    }

}