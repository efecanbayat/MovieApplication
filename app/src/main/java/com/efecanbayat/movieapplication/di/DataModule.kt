package com.efecanbayat.movieapplication.di

import com.efecanbayat.movieapplication.data.remote.api.MovieService
import com.efecanbayat.movieapplication.data.remote.source.RemoteDataSource
import com.efecanbayat.movieapplication.data.remote.source.impl.RemoteDataSourceImpl
import com.efecanbayat.movieapplication.data.repository.MovieRepository
import com.efecanbayat.movieapplication.data.repository.impl.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRemoteDataSource(movieService: MovieService): RemoteDataSource =
        RemoteDataSourceImpl(movieService)

    @Provides
    fun provideApiRepository(remoteDataSource: RemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource)
    }
}