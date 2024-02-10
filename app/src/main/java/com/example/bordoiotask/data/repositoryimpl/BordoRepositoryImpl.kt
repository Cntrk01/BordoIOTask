package com.example.bordoiotask.data.repositoryimpl

import com.example.bordoiotask.data.remote.BordoApi
import com.example.bordoiotask.data.response.BordoHomeItem
import com.example.bordoiotask.data.response.BordoProjectsItem
import com.example.bordoiotask.data.response.Response
import com.example.bordoiotask.data.response.toBordoHomeProjectsItem
import com.example.bordoiotask.data.response.toBordoProjectsItem
import com.example.bordoiotask.domain.repository.BordoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class BordoRepositoryImpl @Inject constructor(private val bordoApi: BordoApi) : BordoRepository {

    override suspend fun bordoGetHome(): Flow<Response<List<BordoHomeItem>>> {
        return flow {
            try {
                emit(Response.Loading())
                val homeItem = bordoApi.bordoGetHome().toBordoHomeProjectsItem()
                emit(Response.Success(data = homeItem))
            }catch (e: SocketTimeoutException) {
                emit(Response.Error("Timeout.Try Again"))
            }
            catch (e: HttpException) {
                emit(Response.Error("Check your internet connection.."))
            } catch (e: IOException) {
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }catch (e: Exception) {
                emit(Response.Error(e.message.toString()))
            }
        }
    }

    override suspend fun bordoGetProject(): Flow<Response<List<BordoProjectsItem>>> {
        return flow {
            try {
                emit(Response.Loading())
                val projectItem = bordoApi.bordoGetProject().toBordoProjectsItem()
                emit(Response.Success(data = projectItem))
            }catch (e: SocketTimeoutException) {
                emit(Response.Error("Timeout.Try Again"))
            } catch (e: HttpException) {
                emit(Response.Error("Check your internet connection.."))
            } catch (e: IOException) {
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }catch (e: Exception) {
                emit(Response.Error("An unexpected error occured"))
            }
        }
    }
}