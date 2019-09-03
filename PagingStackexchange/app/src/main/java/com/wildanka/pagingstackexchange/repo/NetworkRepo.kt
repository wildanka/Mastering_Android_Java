package com.wildanka.pagingstackexchange.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wildanka.pagingstackexchange.model.ApiClient
import com.wildanka.pagingstackexchange.model.Items
import com.wildanka.pagingstackexchange.model.StackApiResponse
import com.wildanka.pagingstackexchange.model.StackExchangeApi
import retrofit2.Call
import retrofit2.Response

class NetworkRepo {
    val tag = "NetworkRepo"

    companion object {
        val instance = NetworkRepo
    }

    private val stackExchangeApi: StackExchangeApi

    init {
        stackExchangeApi = ApiClient.createService(StackExchangeApi::class.java)
    }

    fun fetchStackAnswer(page: Int?, pageSize: Int?, site: String = "stackoverflow"): MutableLiveData<List<Items?>> {
        val stackExchangeResponse: MutableLiveData<StackApiResponse> = MutableLiveData()
        val stackExchangeResponseList: MutableLiveData<List<Items?>> = MutableLiveData()

        ApiClient
            .createServices(StackExchangeApi::class.java)
            .getAnswers(page, pageSize, site)
            .enqueue(object : retrofit2.Callback<StackApiResponse?> {
                override fun onFailure(call: Call<StackApiResponse?>, t: Throwable) {
                    println("Failed to POST Message: ${ t.message }")
                    stackExchangeResponse.value = null
                }

                override fun onResponse(call: Call<StackApiResponse?>, response: Response<StackApiResponse?>) {
                    stackExchangeResponse.value = response.body()
                    stackExchangeResponseList.value = response.body()?.items
                    Log.d(TAG, stackExchangeResponse.value?.items?.get(0)?.owner?.userId?.toString())
                    Log.d(TAG, stackExchangeResponseList.value?.get(0)?.owner?.userId?.toString())
                    println("HASIL REPO "+stackExchangeResponse.value?.items?.get(0)?.owner?.userId?.toString())
                    println("HASIL REPO LIST "+stackExchangeResponseList.value?.get(0)?.owner?.userId?.toString())
                }
            })
        return stackExchangeResponseList
    }
}