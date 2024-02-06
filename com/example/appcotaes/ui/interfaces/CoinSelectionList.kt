package com.example.appcotaes.ui.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcotaes.data.CoinModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinSelectionList(
    coinsList: List<CoinModel>,
    showDialog: Boolean,
    moedaSelecionada: CoinModel,
    labelFunction: (CoinModel) -> Unit,
    closeDialog: () -> Unit,
    pesquisaQuery: String,
    pesquisaQueryFunction: (String) -> Unit
) {

    val itemSelecionado = coinsList.find { it.nome == moedaSelecionada.nome && it.sigla == moedaSelecionada.sigla }
    var selectedIndexItem = coinsList.indexOf(itemSelecionado)
    val listState = rememberLazyListState()


    val filteredCoinsList = coinsList.filter {
        it.nome.contains(pesquisaQuery, ignoreCase = true)
    }

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
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, start = 10.dp, bottom = 5.dp, end = 15.dp)
                        .height(60.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                closeDialog()
                                pesquisaQueryFunction("")
                            }
                            .size(40.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    OutlinedTextField(
                        value = pesquisaQuery,
                        onValueChange = {
                            pesquisaQueryFunction(it)
                        },
                        modifier = Modifier
                            .width(250.dp),
                        placeholder = {
                            Row {
                                Icon(Icons.Rounded.Search, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    )
                }
                LazyColumn(state = listState) {
                    items(filteredCoinsList) {moeda ->
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
                                    pesquisaQueryFunction("")
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