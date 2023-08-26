package com.example.studyglows.network.apis

import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairDetails
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairItem
import retrofit2.Response
import javax.inject.Inject

class CurrentAffairApis @Inject constructor() {
    suspend fun fetchAllCurrentAffairs(): Response<List<CurrentAffairItem>> =
        Response.success(
            listOf(
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                    date = 1691625600000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                    date = 1691625600000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                    date = 1691625600000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                    date = 1691625600000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://i.ytimg.com/vi/2k2WowXugaA/hqdefault.jpg",
                    date = 1691452800000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://i.ytimg.com/vi/2k2WowXugaA/hqdefault.jpg",
                    date = 1691452800000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://i.ytimg.com/vi/2k2WowXugaA/hqdefault.jpg",
                    date = 1691452800000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://i.ytimg.com/vi/2k2WowXugaA/hqdefault.jpg",
                    date = 1691452800000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://blogmedia.testbook.com/blog/wp-content/uploads/2023/02/ugc-ca-banner-1-733e690d.jpg",
                    date = 1691366400000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://blogmedia.testbook.com/blog/wp-content/uploads/2023/02/ugc-ca-banner-1-733e690d.jpg",
                    date = 1691366400000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://blogmedia.testbook.com/blog/wp-content/uploads/2023/02/ugc-ca-banner-1-733e690d.jpg",
                    date = 1691366400000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://blogmedia.testbook.com/blog/wp-content/uploads/2023/02/ugc-ca-banner-1-733e690d.jpg",
                    date = 1691366400000
                )
            )
        )

    suspend fun fetchCurrentAffairDetails(id: String): Response<CurrentAffairDetails> =
        Response.success(
            CurrentAffairDetails(
                title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions League",
                image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                contents = listOf(
                    "Young striker Manisha Kalyan has become the 1st Indian footballer to play at the UEFA Women’s Champions League.",
                    "She made her debut for Apollon Ladies FC in the European Club competition in Engomi, Cyprus.",
                    "She replaced Cyprus’ Marilena Georgiou in the 60th minute at the Makareio Stadium",
                    "Young striker Manisha Kalyan has become the 1st Indian footballer to play at the UEFA Women’s Champions League.",
                    "She made her debut for Apollon Ladies FC in the European Club competition in Engomi, Cyprus.",
                    "She replaced Cyprus’ Marilena Georgiou in the 60th minute at the Makareio Stadium",
                    "Young striker Manisha Kalyan has become the 1st Indian footballer to play at the UEFA Women’s Champions League.",
                    "She made her debut for Apollon Ladies FC in the European Club competition in Engomi, Cyprus.",
                    "She replaced Cyprus’ Marilena Georgiou in the 60th minute at the Makareio Stadium",
                )
            )
        )

    suspend fun getAllCategoryFilters(): Response<List<String>> =
        Response.success(
            listOf(
                "National Affairs",
                "Defense",
                "Agreement",
                "International Affairs",
                "Obituaries",
                "State Affairs",
                "Book & Authors",
                "Science & Technologies",
                "Summit & Conference",
                "Important Dates",
                "National & International Organizations",
                "Honours & Awards",
                "Art & Culture",
                "Sports",
                "Banking & Finance"
            )
        )

    suspend fun filterCourses(filters: List<String>): Response<List<CurrentAffairItem>> =
        Response.success(
            listOf(
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                    date = 1691625600000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                    date = 1691625600000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                    date = 1691625600000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://m.media-amazon.com/images/I/51fSJAaysFL._AC_UF1000,1000_QL80_.jpg",
                    date = 1691625600000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://i.ytimg.com/vi/2k2WowXugaA/hqdefault.jpg",
                    date = 1691452800000
                ),
                CurrentAffairItem(
                    title = "Manisha Kalyan becomes 1st Indian to play in UEFA Women’s Champions league",
                    image = "https://i.ytimg.com/vi/2k2WowXugaA/hqdefault.jpg",
                    date = 1691452800000
                )
            )
        )
}