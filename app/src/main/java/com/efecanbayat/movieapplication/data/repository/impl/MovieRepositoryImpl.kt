package com.efecanbayat.movieapplication.data.repository.impl

import com.efecanbayat.movieapplication.data.remote.source.RemoteDataSource
import com.efecanbayat.movieapplication.data.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
}