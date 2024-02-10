package com.example.bordoiotask.data.response

class BordoHomeList : ArrayList<BordoHomeItem>()

fun BordoHomeList.toBordoHomeProjectsItem(): List<BordoHomeItem> {
    val result = mutableListOf<BordoHomeItem>()

    for (item in this) {
        val bordoHomeItem = BordoHomeItem(
            id = item.id,
            title = item.title,
            desc = item.desc,
            technologies = item.technologies
        )
        result.add(bordoHomeItem)
    }

    return result
}