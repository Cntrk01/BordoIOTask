package com.example.bordoiotask.data.repositoryimpl

import com.example.bordoiotask.data.remote.BordoApi
import com.example.bordoiotask.data.response.BordoItem
import com.example.bordoiotask.data.response.BordoList
import com.example.bordoiotask.data.response.Response
import com.example.bordoiotask.data.response.toBordoItem
import com.example.bordoiotask.domain.repository.BordoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BordoRepositoryImpl @Inject constructor(private val bordoApi: BordoApi) : BordoRepository {
    override suspend fun bordoGetHome(): Flow<Response<List<BordoItem>>> {
        return flow {
            try {
                emit(Response.Loading())
                val homeItem = bordoApi.bordoGetHome().toBordoItem()
                emit(Response.Success(data = homeItem))
            }catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: HttpException) {
                emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }
        }
    }

    override suspend fun bordoGetProject(): Flow<Response<List<BordoItem>>> {
        return flow {
            try {
                emit(Response.Loading())
                val projectItem = bordoApi.bordoGetHome().toBordoItem()
                emit(Response.Success(data = projectItem))
            }catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: HttpException) {
                emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }
        }
    }
}