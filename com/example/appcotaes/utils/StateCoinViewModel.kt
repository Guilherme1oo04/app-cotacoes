package com.example.appcotaes.utils

import androidx.lifecycle.ViewModel
import com.example.appcotaes.data.ApiResponseModel
import com.example.appcotaes.data.CoinModel
import com.example.appcotaes.utils.interfaces.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StateCoinViewModel: ViewModel() {
    var currencyInfoCoin: ApiResponseModel? = null
    var combinacaoExiste: Boolean = true

    private val retrofitService: ApiService = InstanceRetrofit().createService()

    init {
        loadInfo("USD", "BRL")
    }

    private fun loadInfo(sigla1: String, sigla2: String) {
        retrofitService.getCotacao("$sigla1-$sigla2").enqueue(object: Callback<List<ApiResponseModel>> {
            override fun onResponse(
                call: Call<List<ApiResponseModel>>,
                response: Response<List<ApiResponseModel>>
            ) {
                if (response.isSuccessful) {
                    currencyInfoCoin = response.body()?.get(0)
                    combinacaoExiste = true
                } else {
                    combinacaoExiste = false
                }
            }

            override fun onFailure(call: Call<List<ApiResponseModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun updateInfo(moeda1: CoinModel, moeda2: CoinModel) {
        currencyInfoCoin = null
        loadInfo(moeda1.sigla, moeda2.sigla)
    }
}