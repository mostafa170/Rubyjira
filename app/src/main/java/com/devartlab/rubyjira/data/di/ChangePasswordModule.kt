package com.devartlab.rubyjira.data.di

import com.devartlab.rubyjira.data.datasource.changePassword.ChangePasswordDataSource
import com.devartlab.rubyjira.data.datasource.changePassword.ChangePasswordDataSourceImpl
import com.devartlab.rubyjira.data.repositories.ChangePasswordRepositoryImpl
import com.devartlab.rubyjira.domain.repositories.ChangePasswordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ChangePasswordModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher):ChangePasswordDataSource{
        return ChangePasswordDataSourceImpl(dispatcher)
    }

    @Singleton
    @Provides
    fun provideRepository(changePasswordDataSource: ChangePasswordDataSource):ChangePasswordRepository{
        return ChangePasswordRepositoryImpl(changePasswordDataSource)
    }
}