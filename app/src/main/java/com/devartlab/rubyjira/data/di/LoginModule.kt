package com.devartlab.rubyjira.data.di

import com.devartlab.rubyjira.data.datasource.login.LoginDataSource
import com.devartlab.rubyjira.data.datasource.login.LoginDataSourceImpl
import com.devartlab.rubyjira.data.repositories.LoginRepositoryImpl
import com.devartlab.rubyjira.domain.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LoginModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher): LoginDataSource {
        return LoginDataSourceImpl(dispatcher)
    }

    @Singleton
    @Provides
    fun provideRepository(loginDataSource: LoginDataSource): LoginRepository {
        return LoginRepositoryImpl(loginDataSource)
    }

}