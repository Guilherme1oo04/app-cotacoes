package com.example.appcotaes.utils.interfaces

import com.example.appcotaes.utils.ApiResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{moedas}")
    fun getCotacao(@Path("moedas") moedas: String): Call<List<ApiResponseModel>>
}