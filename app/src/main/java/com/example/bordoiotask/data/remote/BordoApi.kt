package com.example.bordoiotask.data.remote

import com.example.bordoiotask.data.response.BordoList
import retrofit2.http.GET

interface BordoApi {
    @GET("home")
    fun bordoGetHome() : BordoList

    @GET("projects")
    fun bordoGetProject() : BordoList
}