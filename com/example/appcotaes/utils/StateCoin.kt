package com.example.appcotaes.utils

import com.example.appcotaes.utils.interfaces.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StateCoin {

    var currentInfoCoin: ApiResponseModel? = null
        private set

    private val retrofitService: ApiService = InstanceRetrofit().createService()

    fun updateInfo() {
        retrofitService.getCotacao("USD-BRL").enqueue(object: Callback<List<ApiResponseModel>> {
            override fun onResponse(
                call: Call<List<ApiResponseModel>>,
                response: Response<List<ApiResponseModel>>
            ) {
                if (response.isSuccessful) {
                    currentInfoCoin = response.body()?.get(0)
                }
            }

            override fun onFailure(call: Call<List<ApiResponseModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}