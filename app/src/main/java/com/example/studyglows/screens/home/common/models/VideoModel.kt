package com.example.studyglows.screens.home.common.models

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.google.gson.annotations.SerializedName
import java.util.UUID

class PlaylistModel(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("imageUrl") val imageUrl: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("videos") val videos: List<VideoModel> = listOf(),
    @SerializedName("resources") val resources: List<ResourceModel> = listOf()
)

class ResourceModel(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("title") val title: String = "",
    @SerializedName("link") val resourceLink: String = "",
    @SerializedName("isViewable") val isViewable: Boolean = false
)

class VideoModel(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("title") val title: String = "",
    @SerializedName("length") val videoLength: Long = 0,
    @SerializedName("videoUrl") val videoLink: String = "",
    @SerializedName("isViewable") val isViewable: Boolean = false,
    @SerializedName("viewStatus") val viewStatus: ViewStatus = ViewStatus.TO_WATCH
)

enum class ViewStatus(val status: String) {
    TO_WATCH("play"),
    COMPLETED("completed"),
}

fun List<VideoModel>.getMediaItems(): List<MediaItem> =
    map {
        MediaItem
            .Builder()
            .setUri(it.videoLink)
            .setTag(it)
            .setMediaMetadata(
                MediaMetadata
                    .Builder()
                    .setDisplayTitle(it.title)
                    .build()
            )
            .build()
    }