package com.example.appcotaes.ui.interfaces

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcotaes.data.CoinModel
import com.example.appcotaes.utils.StateCoinViewModel

@Composable
fun App(
    coinsList: List<CoinModel>,
    exitApp: () -> Unit,
) {
    var moeda1Selecionada by remember {
        mutableStateOf(CoinModel(sigla = "USD", nome = "Dólar Americano"))
    }
    var moeda2Selecionada by remember {
        mutableStateOf(CoinModel(sigla = "BRL", nome = "Real Brasileiro"))
    }
    var expandedPopupMoeda1 by remember {
        mutableStateOf(false)
    }
    var expandedPopupMoeda2 by remember {
        mutableStateOf(false)
    }
    var pesquisaQuery by remember {
        mutableStateOf("")
    }

    val currencyMoedas by remember {
        mutableStateOf(StateCoinViewModel())
    }
    LaunchedEffect(moeda1Selecionada){
        currencyMoedas.updateInfo(moeda1Selecionada, moeda2Selecionada)
    }
    LaunchedEffect(moeda2Selecionada){
        currencyMoedas.updateInfo(moeda1Selecionada, moeda2Selecionada)
    }
    LaunchedEffect(moeda1Selecionada, moeda2Selecionada){
        currencyMoedas.updateInfo(moeda1Selecionada, moeda2Selecionada)
    }

    BackHandler {
        if(expandedPopupMoeda1 || expandedPopupMoeda2) {
            expandedPopupMoeda1 = false
            expandedPopupMoeda2 = false
            pesquisaQuery = ""
        } else {
            exitApp()
        }
    }

    Box(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier
            .fillMaxHeight()) {

            Column(
                modifier = Modifier
                    .height(170.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp)
                    )
            ) {
                Text(
                    text = "Espia Câmbio",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(1.5f, 1.5f),
                            blurRadius = 0f
                        )
                    )
                )

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 13.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .clickable {
                                expandedPopupMoeda1 = !expandedPopupMoeda1
                            }
                            .width(100.dp)
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(top = 8.dp, bottom = 8.dp, start = 5.dp, end = 5.dp),
                        text = moeda1Selecionada.sigla,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )

                    Box(
                        modifier = Modifier
                            .width(55.dp)
                            .clickable {
                                val moeda = moeda1Selecionada
                                moeda1Selecionada = moeda2Selecionada
                                moeda2Selecionada = moeda
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 17.dp)
                                .size(25.dp),
                            tint = MaterialTheme.colorScheme.secondary,

                        )

                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(top = 10.dp, end = 17.dp)
                                .size(25.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Text(
                        modifier = Modifier
                            .clickable {
                                expandedPopupMoeda2 = !expandedPopupMoeda2
                            }
                            .width(100.dp)
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(top = 8.dp, bottom = 8.dp, start = 5.dp, end = 5.dp),
                        text = moeda2Selecionada.sigla,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            CurrencyInformation(currencyMoedas)
        }

        /* Moeda 1 */
        CoinSelectionList(
            coinsList = coinsList,
            showDialog = expandedPopupMoeda1,
            moedaSelecionada = moeda1Selecionada,
            labelFunction = {moeda ->

                if(moeda.sigla == moeda2Selecionada.sigla && moeda.nome == moeda2Selecionada.nome) {
                    moeda2Selecionada = moeda1Selecionada
                }
                moeda1Selecionada = moeda
            },
            closeDialog = {
                expandedPopupMoeda1 = false
            },
            pesquisaQuery = pesquisaQuery,
            pesquisaQueryFunction = {
                pesquisaQuery = it
            }
        )

        /* Moeda 2 */
        CoinSelectionList(
            coinsList = coinsList,
            showDialog = expandedPopupMoeda2,
            moedaSelecionada = moeda2Selecionada,
            labelFunction = {moeda ->

                if(moeda.sigla == moeda1Selecionada.sigla && moeda.nome == moeda1Selecionada.nome) {
                    moeda1Selecionada = moeda2Selecionada
                }
                moeda2Selecionada = moeda
            },
            closeDialog = {
                expandedPopupMoeda2 = false
            },
            pesquisaQuery = pesquisaQuery,
            pesquisaQueryFunction = {
                pesquisaQuery = it
            }
        )
    }
}