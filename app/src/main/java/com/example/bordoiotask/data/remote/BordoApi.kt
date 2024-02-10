package com.example.bordoiotask.data.remote

import com.example.bordoiotask.data.response.BordoHomeList
import com.example.bordoiotask.data.response.BordoProjectsList
import retrofit2.http.GET

interface BordoApi {
    @GET("home")
    suspend fun bordoGetHome() : BordoHomeList

    @GET("projects")
    suspend fun bordoGetProject() : BordoProjectsList
}