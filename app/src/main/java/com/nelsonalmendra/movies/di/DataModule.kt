package com.nelsonalmendra.movies.di

import com.nelsonalmendra.movies.data.MoviesRepository
import com.nelsonalmendra.movies.data.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsMoviesRepository(
        moviesRepository: MoviesRepositoryImpl
    ): MoviesRepository
}
