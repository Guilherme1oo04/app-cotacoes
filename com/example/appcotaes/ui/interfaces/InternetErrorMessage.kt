package com.example.appcotaes.ui.interfaces

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InternetErrorMessage(internetVerification: () -> Unit) {
    var isPressed by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isPressed) {
            CircularProgressIndicator(modifier = Modifier.width(70.dp).height(70.dp), color = MaterialTheme.colorScheme.secondary)
        } else {
            Text(
                text = "Sem conexão com a internet!",
                modifier = Modifier.padding(16.dp),
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )

            Button(onClick = {
                internetVerification()

                isPressed = true

                Handler(Looper.getMainLooper()).postDelayed({
                    isPressed = false
                }, 1000)
            }
            ) {
                Text(
                    text = "Tentar novamente",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 25.sp
                )
            }
        }
    }
}