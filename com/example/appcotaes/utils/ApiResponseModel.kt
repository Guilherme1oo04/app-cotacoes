package com.example.appcotaes.utils

import com.google.gson.annotations.SerializedName

const val COTACOES_URL = "https://economia.awesomeapi.com.br/daily/"

data class ApiResponseModel(
    @SerializedName("code")
    var code: String,
    @SerializedName("codein")
    var codein: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("high")
    var high: String,
    @SerializedName("low")
    var low: String,
    @SerializedName("varBid")
    var varBid: String,
    @SerializedName("pctChange")
    var pctChange: String,
    @SerializedName("bid")
    var bid: String,
    @SerializedName("ask")
    var ask: String,
    @SerializedName("timestamp")
    var timestamp: String,
    @SerializedName("create_data")
    var create_data: String
)
