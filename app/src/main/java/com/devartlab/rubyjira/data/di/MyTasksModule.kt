package com.devartlab.rubyjira.data.di

import com.devartlab.rubyjira.data.datasource.myTasks.MyTasksDataSource
import com.devartlab.rubyjira.data.datasource.myTasks.MyTasksDataSourceImpl
import com.devartlab.rubyjira.data.repositories.MyTasksRepositoryImpl
import com.devartlab.rubyjira.domain.repositories.MyTasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
class MyTasksModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher):MyTasksDataSource{
        return MyTasksDataSourceImpl(dispatcher)
    }
    @Singleton
    @Provides
    fun provideRepository(myTasksDataSource: MyTasksDataSource):MyTasksRepository{
        return MyTasksRepositoryImpl(myTasksDataSource)
    }
}