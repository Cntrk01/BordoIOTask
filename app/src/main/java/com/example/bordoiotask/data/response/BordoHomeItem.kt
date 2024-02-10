package com.example.bordoiotask.data.response

import com.google.gson.annotations.SerializedName

data class BordoHomeItem (
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("technologies")
    val technologies : List<Technologies> ?= emptyList()
)