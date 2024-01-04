package com.example.appcotaes.ui.interfaces

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.appcotaes.utils.InternetDevice


@Composable
fun Screen(internetDevice: InternetDevice) {

    var result by remember {
        mutableStateOf(internetDevice.internetAvailability())
    }

    if (result) {
        Text(text = "Deu certo!")
    } else {
        InternetErrorMessage() {
            result = internetDevice.internetAvailability()
        }
    }
}