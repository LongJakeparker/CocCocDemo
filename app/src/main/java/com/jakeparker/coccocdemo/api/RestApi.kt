package com.jakeparker.coccocdemo.api

import com.jakeparker.coccocdemo.entity.FeedEntity
import retrofit2.Call
import retrofit2.http.GET




/**
 * @author Long Tran
 * @since 02/01/2021
 */
interface RestApi {
    @GET("rss/tin-moi-nhat.rss")
    fun getListNews(): Call<FeedEntity>
}