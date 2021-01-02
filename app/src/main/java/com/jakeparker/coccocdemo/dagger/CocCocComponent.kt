package com.jakeparker.coccocdemo.dagger

import com.jakeparker.coccocdemo.MainViewModelFactory
import com.jakeparker.coccocdemo.repository.NewsRepository
import dagger.Component
import javax.inject.Singleton

/**
 * @author Long Tran
 * @since 02/01/2021
 */
@Component(
    modules = [
        CocCocModule::class
    ]
)
@Singleton
interface CocCocComponent {
    fun getNewsRepository(): NewsRepository
    fun getMainViewModelFactory(): MainViewModelFactory
}