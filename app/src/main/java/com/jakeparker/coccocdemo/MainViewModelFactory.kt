package com.jakeparker.coccocdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jakeparker.coccocdemo.repository.NewsRepository
import javax.inject.Inject

/**
 * @author Long Tran
 * @since 02/01/2021
 */
class MainViewModelFactory @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(newsRepository) as T
    }
}