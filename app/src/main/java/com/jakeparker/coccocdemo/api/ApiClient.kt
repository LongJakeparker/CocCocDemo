package com.jakeparker.coccocdemo.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Long Tran
 * @since 02/01/2021
 */
class ApiClient @Inject constructor() {

    companion object {
        const val BASE_URL = "https://vnexpress.net/"
    }

    private var retrofit: Retrofit

    init {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)

        val client = httpClient.build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()
    }

    fun getInterface(): RestApi {
        return retrofit.create(RestApi::class.java)
    }
}