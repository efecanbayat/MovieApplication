package com.efecanbayat.movieapplication.data.base

import com.efecanbayat.movieapplication.data.model.APIError
import com.efecanbayat.movieapplication.utils.DataState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Flow<DataState<T>> {
        return flow<DataState<T>> {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(DataState.Success(body))
            } else {
                val error = Gson().fromJson(response.errorBody()?.string(), APIError::class.java)
                emit(DataState.Error(error))
            }
        }.catch {
            emit(DataState.Error(APIError(-1, it.message ?: it.toString())))
        }.onStart {
            emit(DataState.Loading())
        }.flowOn(Dispatchers.IO)
    }
}