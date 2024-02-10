package com.example.bordoiotask.domain.use_case

import com.example.bordoiotask.data.repositoryimpl.BordoRepositoryImpl
import com.example.bordoiotask.data.response.BordoItem
import com.example.bordoiotask.data.response.BordoList
import com.example.bordoiotask.data.response.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BordoProjectUseCase @Inject constructor(private val bordoRepositoryImpl: BordoRepositoryImpl) {
    suspend fun bordoGetProject(): Flow<Response<List<BordoItem>>> = bordoRepositoryImpl.bordoGetProject()
}