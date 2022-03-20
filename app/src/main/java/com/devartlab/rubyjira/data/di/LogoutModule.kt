package com.devartlab.rubyjira.data.di

import com.devartlab.rubyjira.data.datasource.logout.LogoutDataSource
import com.devartlab.rubyjira.data.datasource.logout.LogoutDataSourceImpl
import com.devartlab.rubyjira.data.repositories.LogoutRepositoryImpl
import com.devartlab.rubyjira.domain.repositories.LogoutResponsitory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LogoutModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher): LogoutDataSource{
        return LogoutDataSourceImpl(dispatcher)
    }
    @Singleton
    @Provides
    fun provideRepository(logoutDataSource: LogoutDataSource):LogoutResponsitory{
        return LogoutRepositoryImpl(logoutDataSource)
    }
}