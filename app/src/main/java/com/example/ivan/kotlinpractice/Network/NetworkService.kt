package com.example.ivan.kotlinpractice.Network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkService private constructor() {
    private val networkService: NetworkApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
                .readTimeout(60, TimeUnit.SECONDS)
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val retrofit = Retrofit.Builder().baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        networkService = retrofit.create<NetworkApi>(NetworkApi::class.java!!)
    }

    companion object {
        private val API_BASE_URL = "https://en.wikipedia.org/w/"
        private var networkServiceClient: NetworkService? = null
        val serviceInstance: NetworkApi
            get() {
                if (networkServiceClient == null) {
                    networkServiceClient = NetworkService()
                }
                return networkServiceClient!!.networkService
            }
    }
}