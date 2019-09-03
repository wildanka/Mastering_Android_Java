package com.wildanka.pagingstackexchange.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://api.stackexchange.com/2.2/"
object ApiClient {
//    private val BASE_URL = "http://api.stackexchange.com/2.2/answers?page=2&pagesize=50&site=stackoverflow"
    private val httpClient = OkHttpClient.Builder()
    private lateinit var retrofit: Retrofit

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun <T> createServices(service: Class<T>): T {
        return initRetrofit().create(service)
    }
    private fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return createService(serviceClass, null)
    }
    fun <S> createService(serviceClass: Class<S>, authToken: String?): S {
        //since we don't use authToken for TMDB API
//        if (authToken != null) {
//            addRequestHeaders(authToken)
//        }

        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }

}