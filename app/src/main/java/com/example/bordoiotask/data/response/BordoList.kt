package com.example.bordoiotask.data.response

class BordoList : ArrayList<BordoItem>()

fun BordoList.toBordoItem(): List<BordoItem> {
    val result = mutableListOf<BordoItem>()

    for (item in this) {
        val bordoItem = BordoItem(
            id = item.id,
            title = item.title,
            desc = item.desc
        )
        result.add(bordoItem)
    }

    return result
}