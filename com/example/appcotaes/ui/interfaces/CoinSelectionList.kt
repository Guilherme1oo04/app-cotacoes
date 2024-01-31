package com.example.appcotaes.ui.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcotaes.data.CoinModel

@Composable
fun CoinSelectionList(
    coinsList: List<CoinModel>,
    showDialog: Boolean,
    moedaSelecionada: CoinModel,
    labelFunction: (CoinModel) -> Unit,
    closeDialog: () -> Unit
) {

    val itemSelecionado = coinsList.find { it.nome == moedaSelecionada.nome && it.sigla == moedaSelecionada.sigla }
    var selectedIndexItem = coinsList.indexOf(itemSelecionado)
    val listState = rememberLazyListState()

    if (showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background,
                )
                .padding(top = 10.dp, bottom = 10.dp),
        ) {
            Column {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            closeDialog()
                        }
                        .padding(top = 5.dp, start = 10.dp, bottom = 5.dp)
                        .size(40.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                LazyColumn(state = listState) {
                    items(coinsList) {moeda ->
                        val indexItem = coinsList.indexOf(moeda)
                        val color =
                            if(moeda.sigla == moedaSelecionada.sigla && moeda.nome == moedaSelecionada.nome) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.background

                        Box(
                            modifier = Modifier
                                .height(65.dp)
                                .fillMaxWidth()
                                .background(color)
                                .clickable {
                                    labelFunction(moeda)
                                    closeDialog()
                                }
                        ) {
                            Text(
                                text = moeda.nome,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .align(Alignment.CenterStart),
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }

                        if(moeda.sigla == moedaSelecionada.sigla && moeda.nome == moedaSelecionada.nome) {
                            selectedIndexItem = indexItem
                        }
                    }
                }
            }
        }
        LaunchedEffect(selectedIndexItem) {
            listState.scrollToItem(selectedIndexItem)
        }
    }
}