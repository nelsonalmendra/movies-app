package com.nelsonalmendra.movies.di

import com.nelsonalmendra.movies.api.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesMoviesService(): MoviesService {
        return MoviesService.create()
    }
}