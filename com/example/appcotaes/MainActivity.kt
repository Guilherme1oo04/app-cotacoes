package com.example.appcotaes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.appcotaes.ui.interfaces.Screen
import com.example.appcotaes.ui.theme.AppCotacoesTheme
import com.example.appcotaes.utils.InternetDevice

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCotacoesTheme {
                Screen(internetDevice = InternetDevice(LocalContext.current))
            }
        }

    }
}

