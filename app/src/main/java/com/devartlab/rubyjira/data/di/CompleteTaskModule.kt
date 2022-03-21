package com.devartlab.rubyjira.data.di

import com.devartlab.rubyjira.data.datasource.completeTask.CompleteTaskDataSource
import com.devartlab.rubyjira.data.datasource.completeTask.CompleteTaskDataSourceImpl
import com.devartlab.rubyjira.data.repositories.CompleteTaskRepositoryImpl
import com.devartlab.rubyjira.domain.repositories.CompleteTaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CompleteTaskModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher): CompleteTaskDataSource{
        return CompleteTaskDataSourceImpl(dispatcher)
    }
    @Singleton
    @Provides
    fun provideRepository(completeTaskDataSource: CompleteTaskDataSource):CompleteTaskRepository{
        return CompleteTaskRepositoryImpl(completeTaskDataSource)
    }
}