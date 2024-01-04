package com.example.appcotaes.ui.interfaces

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InternetErrorMessage(internetVerification: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sem conexão com a internet!",
            modifier = Modifier.padding(16.dp),
            fontSize = 28.sp
        )

        Button(onClick = {
            internetVerification()
        }) {
            Text(
                text = "Tentar novamente",
                color = Color.White,
                fontSize = 25.sp
            )
        }
    }
}