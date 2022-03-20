package com.devartlab.rubyjira.data.di

import com.devartlab.rubyjira.data.datasource.myProject.MyProjectDataSource
import com.devartlab.rubyjira.data.datasource.myProject.MyProjectDataSourceImpl
import com.devartlab.rubyjira.data.repositories.MyProjectRepositoryImpl
import com.devartlab.rubyjira.domain.repositories.MyProjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MyprojectModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher):MyProjectDataSource{
        return MyProjectDataSourceImpl(dispatcher)
    }

    @Singleton
    @Provides
    fun provideRepository(myProjectDataSource: MyProjectDataSource):MyProjectRepository{
        return MyProjectRepositoryImpl(myProjectDataSource)
    }
}