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
                    image = "https://thevarsity.ca/wp-content/uploads/2018/07/Comment_Course-Selection_Troy-Lawrence-scaled.jpg",
                    title = "Salman Rushdie Attack: Need to condemn, need to listen",
                    date = 1691671905650
                ),
                EditorialItem(
                    image = "https://thevarsity.ca/wp-content/uploads/2018/07/Comment_Course-Selection_Troy-Lawrence-scaled.jpg",
                    title = "‘Is this how justice ends?’: Bilkis Bano’s question should haunt the Indian republic",
                    date = 1688774400000
                ),
                EditorialItem(
                    image = "https://thevarsity.ca/wp-content/uploads/2018/07/Comment_Course-Selection_Troy-Lawrence-scaled.jpg",
                    title = "‘Is this how justice ends?’: Bilkis Bano’s question should haunt the Indian republic",
                    date = 1688774400000
                ),
                EditorialItem(
                    image = "https://thevarsity.ca/wp-content/uploads/2018/07/Comment_Course-Selection_Troy-Lawrence-scaled.jpg",
                    title = "‘Is this how justice ends?’: Bilkis Bano’s question should haunt the Indian republic",
                    date = 1688688000000
                ),
                EditorialItem(
                    image = "https://thevarsity.ca/wp-content/uploads/2018/07/Comment_Course-Selection_Troy-Lawrence-scaled.jpg",
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