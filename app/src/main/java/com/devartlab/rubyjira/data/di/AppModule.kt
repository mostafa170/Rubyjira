package com.devartlab.rubyjira.data.di

import android.content.Context
import android.content.res.Resources
import com.devartlab.rubyjira.data.utils.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideCoroutineDispatcher() = Dispatchers.IO


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) = app as MyApplication

    @Singleton
    @Provides
    fun provideApplicationResources(app: MyApplication): Resources = app.resources
}