package com.example.studyglows.network.apis

import com.example.studyglows.screens.home.common.models.PlaylistModel
import com.example.studyglows.screens.home.common.models.ResourceModel
import com.example.studyglows.screens.home.common.models.VideoModel
import com.example.studyglows.screens.home.common.models.ViewStatus
import retrofit2.Response
import javax.inject.Inject

class LectureApis @Inject constructor() {
    suspend fun getPlaylistsForCourse(courseId: String): Response<List<PlaylistModel>> =
        Response.success(
            listOf(
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.TO_WATCH
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.TO_WATCH
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.TO_WATCH
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.COMPLETED
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.TO_WATCH
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.COMPLETED
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.TO_WATCH
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.COMPLETED
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.COMPLETED
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.TO_WATCH
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                            viewStatus = ViewStatus.TO_WATCH
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        )
                    )
                ),
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                            isViewable = true,
                        )
                    )
                ),
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    )
                ),
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    )
                ),
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    )
                ),
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    )
                ),
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    )
                ),
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    )
                ),
                PlaylistModel(
                    title = "Intro to Modern History",
                    videos = listOf(
                        VideoModel(
                            title = "History Lesson 1",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                        ),
                        VideoModel(
                            title = "History Lesson 2",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 3",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 4",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 5",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 6",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 7",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 8",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 9",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 10",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 11",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 12",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 13",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        VideoModel(
                            title = "History Lesson 14",
                            videoLength = 324,
                            videoLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    ),
                    resources = listOf(
                        ResourceModel(
                            title = "History Lesson 13",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        ),
                        ResourceModel(
                            title = "History Lesson 14",
                            resourceLink = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                        )
                    )
                ),
            )
        )
}