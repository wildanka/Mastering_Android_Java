package com.wildanka.pagingstackexchange.model.network

import com.wildanka.pagingstackexchange.model.entity.StackApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeApi {
    companion object {
        fun create(){
            val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.stackexchange.com/2.2/")
                .build()

        }
    }

    @GET("answers")
    fun getAnswers(
        @Query("page") page: Int?,
        @Query("pagesize") pageSize: Int?,
        @Query("site") site: String?
    ): Call<StackApiResponse>

    @GET("answers")
    fun getAnswersLiveData(
        @Query("page") page: Int?,
        @Query("pagesize") pageSize: Int?,
        @Query("site") site: String?
    ): Call<StackApiResponse>
}