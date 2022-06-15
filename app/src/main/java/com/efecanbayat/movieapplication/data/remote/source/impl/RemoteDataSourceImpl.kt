package com.efecanbayat.movieapplication.data.remote.source.impl

import com.efecanbayat.movieapplication.data.base.BaseRemoteDataSource
import com.efecanbayat.movieapplication.data.remote.api.MovieService
import com.efecanbayat.movieapplication.data.remote.source.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : RemoteDataSource, BaseRemoteDataSource() {

}