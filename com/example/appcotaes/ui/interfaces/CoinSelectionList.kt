package com.example.appcotaes.ui.interfaces

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.appcotaes.data.CoinModel

@Composable
fun CoinSelectionList(
    coinsList: List<CoinModel>,
    showDialog: Boolean,
    labelFunction: (CoinModel) -> Unit,
    closeDialog: () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = { closeDialog() }) {
            LazyColumn {
                items(coinsList) {moeda ->
                    Text(text = moeda.nome, modifier = Modifier.clickable {
                        labelFunction(moeda)
                        closeDialog()
                    })
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}