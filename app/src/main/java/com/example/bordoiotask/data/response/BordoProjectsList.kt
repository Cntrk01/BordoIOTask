package com.example.bordoiotask.data.response

class BordoProjectsList : ArrayList<BordoProjectsItem>()

fun BordoProjectsList.toBordoProjectsItem(): List<BordoProjectsItem> {
    val result = mutableListOf<BordoProjectsItem>()

    for (item in this) {
        val bordoProjectsItem = BordoProjectsItem(
            id = item.id,
            image = item.image,
            title = item.title,
            desc = item.desc
        )
        result.add(bordoProjectsItem)
    }

    return result
}