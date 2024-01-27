package com.example.appcotaes.ui.interfaces

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun App(coinsList: List<CoinModel>) {
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

    Column(modifier = Modifier
        .clickable(interactionSource = MutableInteractionSource(), indication = null) {
            if (expandedPopupMoeda1) {
                expandedPopupMoeda1 = false
            }
            if (expandedPopupMoeda2) {
                expandedPopupMoeda2 = false
            }
        }
        .fillMaxHeight()) {

        Text(
            text = "Espia Câmbio",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
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
                .padding(13.dp)
        ) {
            Text(
                modifier = Modifier
                    .clickable(onClick = {
                        expandedPopupMoeda1 = !expandedPopupMoeda1
                    }, onClickLabel = "")
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(5.dp),
                text = moeda1Selecionada.sigla,
                fontSize = 22.sp,
            )
        }

        CoinSelectionList(
            coinsList = coinsList,
            showDialog = expandedPopupMoeda1,
            labelFunction = {moeda ->
                moeda1Selecionada = moeda
            },
            closeDialog = {
                expandedPopupMoeda1 = false
            }
        )
    }
}