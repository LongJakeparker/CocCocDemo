package com.jakeparker.coccocdemo.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * @author Long Tran
 * @since 02/01/2021
 */
@Module
object CocCocModule {
    @Provides
    @JvmStatic
    fun provideContext(): Context {
        return Application().applicationContext
    }
}