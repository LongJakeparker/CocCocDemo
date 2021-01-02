package com.jakeparker.coccocdemo

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jakeparker.coccocdemo.model.Item
import com.jakeparker.coccocdemo.model.Resource
import com.jakeparker.coccocdemo.repository.NewsRepository

/**
 * @author Long Tran
 * @since 02/01/2021
 */
class MainViewModel internal constructor(private val newsRepository: NewsRepository) : ViewModel() {
    var listData: ArrayList<Item>? = ArrayList()
    var isCreated = false

    private val _eventRefresh = EventLiveData<Int>()
    val eventRefresh: LiveData<Int> = _eventRefresh

    private val _evenDarkMode = EventLiveData<Int>()
    val evenDarkMode: LiveData<Int> = _evenDarkMode

    var isRefresh = false

    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        isRefresh = true
        _eventRefresh.setValue(1)
    }

    val onDarkModeListener = View.OnClickListener {
        _evenDarkMode.setValue(1)
    }

    fun getListNews(): LiveData<Resource<ArrayList<Item>>> {
        return newsRepository.getListNews()
    }

}