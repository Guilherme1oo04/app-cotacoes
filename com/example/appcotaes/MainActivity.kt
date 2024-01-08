package com.example.appcotaes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.appcotaes.ui.interfaces.Screen
import com.example.appcotaes.ui.theme.AppCotaçõesTheme
import com.example.appcotaes.utils.InternetDevice

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCotaçõesTheme {
                Screen(internetDevice = InternetDevice(LocalContext.current))
            }
        }
    }
}

