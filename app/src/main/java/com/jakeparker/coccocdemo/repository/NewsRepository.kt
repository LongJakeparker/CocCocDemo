package com.jakeparker.coccocdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakeparker.coccocdemo.api.ApiClient
import com.jakeparker.coccocdemo.entity.FeedEntity
import com.jakeparker.coccocdemo.mapper.FeedMapper
import com.jakeparker.coccocdemo.model.Item
import com.jakeparker.coccocdemo.model.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Long Tran
 * @since 02/01/2021
 */
@Singleton
class NewsRepository @Inject constructor(private val apiClient: ApiClient) {

    fun getListNews(): LiveData<Resource<ArrayList<Item>>> {
        val data = MutableLiveData<Resource<ArrayList<Item>>>()
        data.value = Resource.loading(0)
        apiClient.getInterface().getListNews().enqueue(
            object: Callback<FeedEntity> {
                override fun onFailure(call: Call<FeedEntity>, t: Throwable) {
                    data.value = Resource.error(t)
                }

                override fun onResponse(call: Call<FeedEntity>, response: Response<FeedEntity>) {
                    val feed = FeedMapper.transform(response.body()!!)
                    data.value = Resource.success(feed.channel?.items)
                }
            }
        )
        return data
    }

}