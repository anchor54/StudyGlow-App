package com.example.studyglows.network.apis

import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.AlertItemResponse
import retrofit2.Response
import javax.inject.Inject

class SavedItemsApis @Inject constructor() {
    suspend fun getSavedEditorials(): Response<List<EditorialItem>> =
        Response.success(
            listOf(
                EditorialItem(
                    image = "https://i.pinimg.com/originals/b8/a8/20/b8a820b90a470a0e723e8e4c4d1c3d0b.png",
                    title = "Salman Rushdie Attack: Need to condemn, need to listen",
                    date = 1691671905650
                ),
                EditorialItem(
                    image = "https://piktochart.com/wp-content/uploads/2022/12/large-18.jpg",
                    title = "‘Is this how justice ends?’: Bilkis Bano’s question should haunt the Indian republic",
                    date = 1688774400000
                ),
                EditorialItem(
                    image = "https://imgv3.fotor.com/images/videoImage/create-various-bridal-shower-invitation-with-fotor-copy.jpg",
                    title = "‘Is this how justice ends?’: Bilkis Bano’s question should haunt the Indian republic",
                    date = 1688774400000
                ),
                EditorialItem(
                    image = "https://img.freepik.com/free-vector/youtube-background-thumbnail-with-text-full-editable-template_1361-2732.jpg",
                    title = "‘Is this how justice ends?’: Bilkis Bano’s question should haunt the Indian republic",
                    date = 1688688000000
                ),
                EditorialItem(
                    image = "https://www.shutterstock.com/image-vector/news-background-world-map-backdrop-260nw-691718833.jpg",
                    title = "‘Is this how justice ends?’: Bilkis Bano’s question should haunt the Indian republic",
                    date = 1688688000000
                )
            )
        )

    suspend fun getSavedJobs(): Response<List<AlertItemResponse>> =
        Response.success(
            listOf(
                AlertItemResponse(
                    title = "Bihar DLRS Various Post Online Form 2022",
                    tag = "BIHAR",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "SBI PO Online Form 2022",
                    tag = "SBI",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "RSMSSB Common Eligibility Test CET Online Form 2022",
                    tag = "RSMSSB",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "UPSC GEO Scientist Online Form 2022",
                    tag = "UPSC",
                    date = 1697826600000
                )
            )
        )
}