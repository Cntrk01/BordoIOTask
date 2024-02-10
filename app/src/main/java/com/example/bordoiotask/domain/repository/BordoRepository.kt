package com.example.bordoiotask.domain.repository

import com.example.bordoiotask.data.response.BordoHomeItem
import com.example.bordoiotask.data.response.BordoProjectsItem
import com.example.bordoiotask.data.response.Response
import kotlinx.coroutines.flow.Flow

interface BordoRepository {
    suspend fun bordoGetHome() : Flow<Response<List<BordoHomeItem>>>
    suspend fun bordoGetProject() : Flow<Response<List<BordoProjectsItem>>>
}