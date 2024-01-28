package com.example.appcotaes.ui.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcotaes.data.CoinModel

@Composable
fun CoinSelectionList(
    coinsList: List<CoinModel>,
    showDialog: Boolean,
    labelFunction: (CoinModel) -> Unit,
    closeDialog: () -> Unit
) {
    if (showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background,
                )
                .padding(top = 10.dp, bottom = 10.dp),
        ) {
            LazyColumn {
                items(coinsList) {moeda ->
                    Box(
                        modifier = Modifier.height(65.dp)
                    ) {
                        Text(
                            text = moeda.nome,
                            modifier = Modifier
                                .clickable {
                                    labelFunction(moeda)
                                    closeDialog()
                                }
                                .padding(horizontal = 10.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}