package com.example.bordoiotask.presentation.project.state

import com.example.bordoiotask.data.response.BordoProjectsItem

data class ProjectState(
    val loading : Boolean ?= false,
    val error : String ?= "",
    val projectsList: List<BordoProjectsItem> ?= emptyList()
)
