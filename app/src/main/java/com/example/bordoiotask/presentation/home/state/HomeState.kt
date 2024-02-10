package com.example.bordoiotask.presentation.home.state

import com.example.bordoiotask.data.response.BordoItem

data class HomeState (
    val error : String ?="",
    val loading : Boolean ?=false,
    val homeData : List<BordoItem> ?= emptyList()
)