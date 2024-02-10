package com.example.bordoiotask.domain.use_case

import com.example.bordoiotask.data.repositoryimpl.BordoRepositoryImpl
import com.example.bordoiotask.data.response.BordoHomeItem
import com.example.bordoiotask.data.response.BordoProjectsItem
import com.example.bordoiotask.data.response.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BordoProjectUseCase @Inject constructor(private val bordoRepositoryImpl: BordoRepositoryImpl) {
    suspend fun bordoGetProject(): Flow<Response<List<BordoProjectsItem>>> = bordoRepositoryImpl.bordoGetProject()
}