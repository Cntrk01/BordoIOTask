package com.example.bordoiotask.domain.use_case

import com.example.bordoiotask.data.repositoryimpl.BordoRepositoryImpl
import com.example.bordoiotask.data.response.BordoHomeItem
import com.example.bordoiotask.data.response.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BordoHomeUseCase @Inject constructor(private val bordoRepositoryImpl: BordoRepositoryImpl){
    suspend fun bordoGetHome(): Flow<Response<List<BordoHomeItem>>> = bordoRepositoryImpl.bordoGetHome()
}