package com.example.appcotaes.data

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class CoinModel(
    val id: String = UUID.randomUUID().toString(),
    val sigla: String,
    val nome: String
)