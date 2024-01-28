package com.example.appcotaes.utils

import com.example.appcotaes.data.COTACOES_URL
import com.example.appcotaes.utils.interfaces.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstanceRetrofit {
    private val retrofit =  Retrofit.Builder()
        .baseUrl(COTACOES_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}