package com.devartlab.rubyjira.data.di

import com.devartlab.rubyjira.data.datasource.updateProfile.UpdateProfileDataSource
import com.devartlab.rubyjira.data.datasource.updateProfile.UpdateProfileDataSourceImpl
import com.devartlab.rubyjira.data.repositories.UpdateProfileDataRepositoryImpl
import com.devartlab.rubyjira.domain.repositories.UpdateProfileDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UpdateProfileDataModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher):UpdateProfileDataSource{
        return UpdateProfileDataSourceImpl(dispatcher)
    }

    @Singleton
    @Provides
    fun provideRepository(updateProfileDataSource: UpdateProfileDataSource):UpdateProfileDataRepository{
        return UpdateProfileDataRepositoryImpl(updateProfileDataSource)
    }
}