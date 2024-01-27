package com.example.appcotaes.data

import com.google.gson.annotations.SerializedName

const val COTACOES_URL = "https://economia.awesomeapi.com.br/daily/"

data class ApiResponseModel(
    @SerializedName("code")
    val code: String,
    @SerializedName("codein")
    val codein: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("high")
    val high: String,
    @SerializedName("low")
    val low: String,
    @SerializedName("varBid")
    val varBid: String,
    @SerializedName("pctChange")
    val pctChange: String,
    @SerializedName("bid")
    val bid: String,
    @SerializedName("ask")
    val ask: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("create_data")
    val create_data: String
)
