package com.wildanka.pagingstackexchange.model.network

import androidx.paging.PageKeyedDataSource
import com.wildanka.pagingstackexchange.model.entity.Items
import com.wildanka.pagingstackexchange.model.entity.StackApiResponse
import retrofit2.Call
import retrofit2.Response

const val FIRST_PAGE = 1
const val PAGE_SIZE = 50
const val SITE_NAME = "stackoverflow"

class ItemDataSource() : PageKeyedDataSource<Int, Items>() {

    //called first time adapter launched
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Items>) {

        ApiClient
            .createServices(StackExchangeApi::class.java)
            .getAnswersLiveData(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
            .enqueue(object : retrofit2.Callback<StackApiResponse?> {
                override fun onFailure(call: Call<StackApiResponse?>, t: Throwable) {
                    println("Failed to POST Message: ${t.message}")
                }

                override fun onResponse(call: Call<StackApiResponse?>, response: Response<StackApiResponse?>) {
                    val items = response.body()!!.items
                    if (response.body() != null) {
                        callback.onResult(items, null, FIRST_PAGE + 1)
                    }
//                    stackExchangeResponse.value = response.body()
//                    stackExchangeResponseList.value = response.body()?.items
//                    Log.d(ContentValues.TAG, stackExchangeResponse.value?.items?.get(0)?.owner?.userId?.toString())
//                    Log.d(ContentValues.TAG, stackExchangeResponseList.value?.get(0)?.owner?.userId?.toString())
//                    println("HASIL REPO "+stackExchangeResponse.value?.items?.get(0)?.owner?.userId?.toString())
//                    println("HASIL REPO LIST "+stackExchangeResponseList.value?.get(0)?.owner?.userId?.toString())
                }
            })
    }

    //when we scroll bottom (swipe up)
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Items>) {
        ApiClient
            .createServices(StackExchangeApi::class.java)
            .getAnswersLiveData(params.key, PAGE_SIZE, SITE_NAME)
            .enqueue(object : retrofit2.Callback<StackApiResponse?> {
                override fun onFailure(call: Call<StackApiResponse?>, t: Throwable) {
                    println("Failed to POST Message: ${t.message}")
                }

                override fun onResponse(call: Call<StackApiResponse?>, response: Response<StackApiResponse?>) {
                    if (response.body() != null) {
                        val key: Int? = if (params.key > 1) params.key - 1 else null
                        callback.onResult(response.body()!!.items, key)
                    }
                }
            })
    }

    //when we scroll up (swipe down)
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Items>) {
        ApiClient
            .createServices(StackExchangeApi::class.java)
            .getAnswersLiveData(params.key, PAGE_SIZE, SITE_NAME)
            .enqueue(object : retrofit2.Callback<StackApiResponse?> {
                override fun onFailure(call: Call<StackApiResponse?>, t: Throwable) {
                    println("Failed to POST Message: ${t.message}")
                }

                override fun onResponse(call: Call<StackApiResponse?>, response: Response<StackApiResponse?>) {
                    if (response.body() != null) {
                        val key: Int? = if (response.body()?.has_more!!) params.key + 1 else null
                        callback.onResult(response.body()!!.items, key)
                    }
                }
            })
    }

}