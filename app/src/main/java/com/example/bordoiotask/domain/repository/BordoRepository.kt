package com.example.bordoiotask.domain.repository

import com.example.bordoiotask.data.response.BordoItem
import com.example.bordoiotask.data.response.BordoList
import com.example.bordoiotask.data.response.Response
import kotlinx.coroutines.flow.Flow

interface BordoRepository {
    suspend fun bordoGetHome() : Flow<Response<List<BordoItem>>>
    suspend fun bordoGetProject() : Flow<Response<List<BordoItem>>>
}