package com.example.studyglows.network.apis

import com.example.studyglows.screens.editorial_currentaffair.editorial.model.Author
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialDetails
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem
import retrofit2.Response
import javax.inject.Inject

class EditorialApis @Inject constructor() {
    suspend fun getEditorialList(): Response<List<EditorialItem>> =
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
                ),
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
                ),
            )
        )

    suspend fun getEditorialDetails(id: String): Response<EditorialDetails> =
        Response.success(
            EditorialDetails(
                image = "https://piktochart.com/wp-content/uploads/2022/12/large-18.jpg",
                date = 1691712000000,
                title = "Salman Rushdie Attack: Need to condemn, need to listen",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                author = listOf(
                    Author(
                        name = "Alok Raj"
                    )
                ),
                content = "It is a surprisingly difficult call, writing about the stabbing of Salman Rushdie in western New York. What can one say beyond saying that it is simply wrong, wrong, wrong — despicable, barbaric, heartbreaking, tragic — but what is the point of multiplying adjectives? The world was a crappy place even before this happened. It is even crappier now. I am pretty much a free speech fundamentalist — almost, most of the time, and such an event merely reinforces that belief. Well, almost.\n" +
                        "\n" +
                        "But it is impossible to finesse that “almost” in the context of the murderous attack that might well leave Rushdie with significant physical damage: One eye gone, liver compromised. Or, indeed, to seek to derive some ironic consolation from the fact that at least his voice is back — practically as soon as he got off the ventilator. As Aatish Taseer reported, Rushdie is back to talking, and joking. Free speech indeed — but at what cost!"
            )
        )
}