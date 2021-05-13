package com.amitesh.webskittertestproject.data.models

data class AlbumItem(
    var albumId: Int=0,
    var id: Int=0,
    var thumbnailUrl: String="",
    var title: String="",
    var url: String="",
    var selected:Boolean = false
)