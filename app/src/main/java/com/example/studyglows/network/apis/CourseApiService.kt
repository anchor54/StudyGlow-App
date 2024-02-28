package com.example.studyglows.network.apis

import com.example.studyglows.network.models.CartPostRequestBody
import com.example.studyglows.network.models.ChapterList
import com.example.studyglows.network.models.ChapterResourceList
import com.example.studyglows.network.models.CourseList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

//class CourseApisImpl @Inject constructor() {
//    suspend fun getMostPopularCourses(): Response<List<Course>> =
//        Response.success(
//            listOf(
//                Course(
//                    imageUrl = "https://image.cnbcfm.com/api/v1/image/104002682-GettyImages-498004278.jpg?v=1529472900",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "data:image/jpeg;base64,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",
//                    title = "International Relation",
//                    originalPrice = 2000f,
//                    discountedPrice = 20f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://img.jagranjosh.com/images/2021/October/19102021/History-od-Modern-India.webp",
//                    title = "Modern History",
//                    originalPrice = 1000f,
//                    discountedPrice = 600f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://lms.chanakyamandal.org/wp-content/uploads/2020/11/History.png",
//                    title = "UPSC History Course",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://image.cnbcfm.com/api/v1/image/104002682-GettyImages-498004278.jpg?v=1529472900",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                )
//            )
//        )
//
////    suspend fun getCurrentlyWatchingCourses(): Response<List<OngoingCourse>> = Response.success(listOf())
//    /**
//     * listOf(
//    OngoingCourse(
//    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//    courseName = "Public Speaking",
//    currentChapter = "Chapter 1",
//    progress = 0.25f
//    ),
//    OngoingCourse(
//    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//    courseName = "Public Speaking",
//    currentChapter = "Chapter 2",
//    progress = 0f
//    ),
//    OngoingCourse(
//    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//    courseName = "Public Speaking",
//    currentChapter = "Chapter 3",
//    progress = 1f
//    ),
//    OngoingCourse(
//    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//    courseName = "Public Speaking",
//    currentChapter = "Chapter 1",
//    progress = 0.5f
//    )
//    )
//     * */
//
//    suspend fun getPopularSubjectCourses(): Response<PopularSubjects> =
//        Response.success(
//            PopularSubjects(
//                listOf(
//                    SubjectwiseCourse(
//                        subjectId = "SSC",
//                        subjectName = "Prepare for SSC",
//                        courses = listOf(
//                            Course(
//                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                                title = "PUBLIC SPEAKING",
//                                originalPrice = 1000f,
//                                discountedPrice = 400f,
//                                isBought = false,
//                                tag = "Most Popular"
//                            ),
//                            Course(
//                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                                title = "PUBLIC SPEAKING",
//                                originalPrice = 1000f,
//                                discountedPrice = 400f,
//                                isBought = false,
//                                tag = "Most Popular"
//                            ),
//                            Course(
//                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                                title = "PUBLIC SPEAKING",
//                                originalPrice = 1000f,
//                                discountedPrice = 400f,
//                                isBought = false,
//                                tag = "Most Popular"
//                            ),
//                            Course(
//                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                                title = "PUBLIC SPEAKING",
//                                originalPrice = 1000f,
//                                discountedPrice = 400f,
//                                isBought = false,
//                                tag = "Most Popular"
//                            )
//                        )
//                    ),
//                    SubjectwiseCourse(
//                        subjectId = "UPSC",
//                        subjectName = "Prepare for UPSC",
//                        courses = listOf(
//                            Course(
//                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                                title = "PUBLIC SPEAKING",
//                                originalPrice = 1000f,
//                                discountedPrice = 400f,
//                                isBought = false,
//                                tag = "Most Popular"
//                            ),
//                            Course(
//                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                                title = "PUBLIC SPEAKING",
//                                originalPrice = 1000f,
//                                discountedPrice = 400f,
//                                isBought = false,
//                                tag = "Most Popular"
//                            ),
//                            Course(
//                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                                title = "PUBLIC SPEAKING",
//                                originalPrice = 1000f,
//                                discountedPrice = 400f,
//                                isBought = false,
//                                tag = "Most Popular"
//                            ),
//                            Course(
//                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                                title = "PUBLIC SPEAKING",
//                                originalPrice = 1000f,
//                                discountedPrice = 400f,
//                                isBought = false,
//                                tag = "Most Popular"
//                            )
//                        )
//                    )
//                )
//            )
//        )
//
//    suspend fun getAllCoursesOfSubject(subjectName: String): Response<SubjectwiseCourse> =
//        Response.success(
//            SubjectwiseCourse(
//                subjectId = subjectName,
//                subjectName = "All Courses for $subjectName",
//                courses = listOf(
//                    Course(
//                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                        title = "PUBLIC SPEAKING",
//                        originalPrice = 1000f,
//                        discountedPrice = 400f,
//                        isBought = false,
//                        tag = "Most Popular"
//                    ),
//                    Course(
//                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                        title = "PUBLIC SPEAKING",
//                        originalPrice = 1000f,
//                        discountedPrice = 400f,
//                        isBought = false,
//                        tag = "Most Popular"
//                    ),
//                    Course(
//                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                        title = "PUBLIC SPEAKING",
//                        originalPrice = 1000f,
//                        discountedPrice = 400f,
//                        isBought = false,
//                        tag = "Most Popular"
//                    ),
//                    Course(
//                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                        title = "PUBLIC SPEAKING",
//                        originalPrice = 1000f,
//                        discountedPrice = 400f,
//                        isBought = false,
//                        tag = "Most Popular"
//                    ),
//                    Course(
//                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                        title = "PUBLIC SPEAKING",
//                        originalPrice = 1000f,
//                        discountedPrice = 400f,
//                        isBought = false,
//                        tag = "Most Popular"
//                    ),
//                    Course(
//                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                        title = "PUBLIC SPEAKING",
//                        originalPrice = 1000f,
//                        discountedPrice = 400f,
//                        isBought = false,
//                        tag = "Most Popular"
//                    ),
//                    Course(
//                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                        title = "PUBLIC SPEAKING",
//                        originalPrice = 1000f,
//                        discountedPrice = 400f,
//                        isBought = false,
//                        tag = "Most Popular"
//                    ),
//                    Course(
//                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                        title = "PUBLIC SPEAKING",
//                        originalPrice = 1000f,
//                        discountedPrice = 400f,
//                        isBought = false,
//                        tag = "Most Popular"
//                    )
//                )
//            )
//        )
//
//    suspend fun getCourseDetails(courseId: String): Response<CourseProfileModel> =
//        Response.success(
//            CourseProfileModel(
//                imageUrl = "https://www.ncertbooks.guru/wp-content/uploads/2022/05/Course-details.png",
//                courseTitle = "UPSC IAS Live Foundation",
//                brief = "The UPSC IAS Live Foundation Course brings the best content and expert faculties at an affordable price.",
//                features = listOf(
//                    CourseFeature(type = "doc", title = "Includes Test Series"),
//                    CourseFeature(type = "calendar", title = "Validity: 2 Years"),
//                    CourseFeature(type = "eye", title = "Weekly live classes"),
//                    CourseFeature(type = "doc", title = "Includes Test Series"),
//                    CourseFeature(type = "doc", title = "Includes Test Series"),
//                    CourseFeature(type = "points", title = "CSAT Included")
//                ),
//                educators = listOf(
//                    Educators(
//                        educatorName = "Shashank Tyagi",
//                        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0aKRdwXitIGaoGbaFU7y6UyACYpcGnKfdew&usqp=CAU",
//                        achievements = listOf(
//                            "he has coached coached many IAS officers, celebrities, CEO, Start-up owners.",
//                            "Honours in English Literature",
//                            "PG in Public Administration.",
//                            "Attempted UPSC CSE Mains twice."
//                        )
//                    ),
//                    Educators(
//                        educatorName = "Shashank Tyagi",
//                        imageUrl = "https://www.ncertbooks.guru/wp-content/uploads/2022/05/Course-details.png",
//                        achievements = listOf(
//                            "he has coached coached many IAS officers, celebrities, CEO, Start-up owners.",
//                            "Honours in English Literature",
//                            "PG in Public Administration.",
//                            "Attempted UPSC CSE Mains twice."
//                        )
//                    ),
//                    Educators(
//                        educatorName = "Shashank Tyagi",
//                        imageUrl = "https://www.ncertbooks.guru/wp-content/uploads/2022/05/Course-details.png",
//                        achievements = listOf(
//                            "he has coached coached many IAS officers, celebrities, CEO, Start-up owners.",
//                            "Honours in English Literature",
//                            "PG in Public Administration.",
//                            "Attempted UPSC CSE Mains twice."
//                        )
//                    )
//                ),
//                faqs = listOf(
//                    FAQ(
//                        question = "How to access live class after purchase?",
//                        answer = "answer"
//                    ),
//                    FAQ(
//                        question = "How to access live class after purchase?",
//                        answer = "answer"
//                    ),
//                    FAQ(
//                        question = "How to access live class after purchase?",
//                        answer = "answer"
//                    ),
//                    FAQ(
//                        question = "How to access live class after purchase?",
//                        answer = "answer"
//                    )
//                )
//            )
//        )
//
//    suspend fun getSimilarCourses(courseId: String): Response<List<Course>> =
//        Response.success(
//            listOf(
//                Course(
//                    imageUrl = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoGBxIQERYREREYFhYWGBkWGhYZGBgaGBoYGhYZGCEWGhoaICsiHxwoIBYYIzYkKCwwMzExGSI3PDcvOyswMS4BCwsLDw4PHRERHTApISkwMDAwLjAwMDAwMDAwMDAwMDAyMC4wMDAwMDAwMDAwMDAwOTAwMDAwMDAwMDAwMDAwMP/AABEIAKgBLAMBIgACEQEDEQH/xAAcAAABBAMBAAAAAAAAAAAAAAAAAgMEBQEGBwj/xABJEAACAQMCBAQDBAYHBgQHAQABAgMABBESIQUGEzEiQVFhBzJxI1KBkRRCYoKhwQgVM2OSorFTcnSz0fBDhMPhJTSTpLLC8ST/xAAaAQEAAwEBAQAAAAAAAAAAAAAAAQIDBAUG/8QAKhEBAAICAQMCBgEFAAAAAAAAAAECAxESBCExQVEFEzJhkaGBInHB0fD/2gAMAwEAAhEDEQA/AOY4oxSsVnFce30XAjFZxSsVlUJOAMk+VNnAjTT9hZPPIsUaF3c4VR5n6nYD1J2ArauB8kSq3Wv4zHCmGKEjU+/yEKSUHbOcHfG3cbzwqwihiaWNUjXSrKQE8yGC5VQTt65P5VpWkz5cebqqV7V7z+nObHkS7mkRBoAY+Jskqg7kkgYOBucGr2L4bRyEiIzPGvhMyhD4wBnCea/jkZFbbwvh+xENwo6w6rKsbMm+di7dj4dx7DbalWPDrgzzXEUDkNpAUlAC6ZGoByM4ycHyz2rSKVcVupyT6tSuuULCFUEiyhWcozkkSodvEykBSoz2A/E0+eRLVYyFGsDtcSOY192bxaSB5BN/9K2iKe6ikWRokErMyNG+WJTwkEMT83pjAz3pBLupMsNus7AqWjBjBDAqRIzYBPn+6O1W4x7M/m395anL8LfED1SkbBSJMq8ZJA2V0ByMnbUFJqkk5AuWLdCSOREbQzFimh/uyBh4fZvlPkc7V0huAT20JEUTTKQNSQzBGIx+sChJz6AmmGmsoyGktJImOg9THVXxrnEitudwc4332IqOFWlepyR6uO8Q4fJbuUlXBBx6jP1qPiu1Xc8MkkaGGCZT3MCaJMgjBVCfETr3jYeRwcrk0vNHJ8EsqlIHgU/NMkE4RTjYuj/eJ8iMHuSN6pNJ9HTj6yJ7Xhy7FGKteO8Cms30yowB+VmRkz+DDv8An9arcVlPZ211eN18EYrGKXijFNp4EYrGKcxWMVO1ZqRisYpeKMVO1eJvFGKXisYptWakYoxSsUYqdompGKxil4rGKnas1JxRilYrGKbVmpOKKzRip2rME0YpWKxipVmCaxilUURomsUrFFSjSxxWcUrFZxXJt7vAgJnYVv8AyPyk8L/pN4gVememrAFtTAbnVspAyd/cbHaq34d8EM1x1mXwxglMjOqUghQB54Jzkdjprod3YPNCBOyiRWUyENt3O2VzhsEHT+z6VvjpvvLyuu6jjM46/wA/6EloFiMLLs40ZaQkklwfE2PUD8vyYiiKI0OpSiB4cIJDpJGncsPFuAMjO+BUviNqs2nLMVDgEnUoHgZVfyBAcqc9hk+Qpi4u3zJHFGxEakdVhpDuSF1DB3G7Enz9q2jbyuxkw3GhVt4HjQt430911bhQDhV0gDb0p2bhXVV+tcPHbrLqJ1qpXTrGlTj9ZTGTtvkYxuaau+EzSSCa+kZFiSNWRZCiOVUZYsDsDkjbG4J88U7d29uYZZLq5kEJdfCmPMDQ5GCSdIUZx+qd8CmwWL2c0qqksiRK3TXDuMyFNh1GY5byxt8/ffFV8MXC57lZIbiUzayDFJkhicqRnGxAYnc9wPOpfLHDOHyaI7ZjLF1WJ1Z/tDEW3H7ibiqyW94Zb3Aggi+1L9FpAq9RpC+gtltwck9vXyqUHb7hKwi2na5kjLRDSBKEOfnOSysxAMn6pGNx6Va2MHViMv6VA2V0jWhdfmGC+43JBGcEbetV/G5uEdKKK5Rz86dRXbKOpAdSCcA7qe2+xHlSrLli3mtilvM3SWKbpyZ07MxzqyPIt59tOfTBKXFc3cIdLizt2QlNDRquiRS2CfQ4OnY+o3GchuHjhlDiCaWCYHDwOTJGGQ41RmTOFz3UYztjHnF5S4bcQ7LMsqrpcBpVwNDB8LozucY7VNub24inXrcPtpbZ3KiaMLIVycKHb1zgaiuPXGaIPcTkupYADA2A+lk6aywyprA16cFk2DEjbB7VoXOnKTa/0i2tikTKGwo+zGc/ramUEDvuB6AHY7hZ8bSScCB2gkGfs9RWNwc+ExMSocHYFfQbEdlS8QuEjKSSmNmRJFYDKOuSoHiGNQwCRnJAHfbRSa7a481sdtw4yVxsaTiukc98De4PWEf2gXAIjZephiDk48eApYMOwO5Pcc7xXPas1l7eDLXNXcefWDWKxindNYxUbbTQ3isYpzFYxTas0IxWMUvFYxU7VmhGKxil4oxU7UmpvFGKXisYqdqTUjFYxS8VjFTtWakYrGKXisYqdqzUjFGKXisYqds5gnFYxWSw9RSTIvrU91JmseZGKKQZlrHXHoanUqTkp7rnFOW8Ot1QHBZgufqcfzpNbZ8NuFGW5M/lAMgEEqzMCu58goyc+W1ctY3OnvZ7xjxzafRvNqqW1uIbcF2iYQq2oYDHGMn0YtnYdz61NvLxkMQiiYv4GYaGGVXDNkY3xqIx3yx98pS0A1nThZXyzb7MrZyN/CRkHy9fLacJQsgwxLxnWxbJBUggr23yAQAMgHz2rrjT5a0zM7lDvJI4EJaR2B0mQhjqbJbGCP8AebAGMgD0qsinbryJFbotuGaJpi0mts7DTnI+bTnYefmMVZxcNXWXlaVw8odi5KnSRoXGDnShIOT2qDfcdEd7/V8a6FQO0jP3KAasrj1LKPofWrqo3M/DLjibo4EqIiqrjSCjkEkuAXGM5I7ZqVxLlyOaKWWeZVVpFWTugKqAVXUWOkeLP8+1V3Mlje3wgjjcoBHlpA50kEggYU+JgNvf13q0PD57m3eyIDBlRZJ28GXjC/aAb4ZgFJG+KCLwWPh+oRW7LEsbJ1HjZk1Fg4UtK2+dsZznbbFR5m4KbvSqulysq4LZ8UuofMxJJJPmfOpHAuSzajoFkfqyKxYNnGlHwM6dtxnse9RJOV7Fbg3E1wWmaXqqvUQRBtetQCMP93v50EvmTgvD0hge67EyEKWdQXJGTmPDH5BsTj0pXAIbf9HkENxot2WRdMa7qrbPvKMkZIJI7d8inOPcCiuoIo7y8EZDu6BUHcu4wGHcd+9M8L5Z6MXRjkV1VJsOW79TQo7gDYuRuR5+m5Ct4bymsDvNaXnVV42jHzK6OcMHVh5eEjIz9aFa+tOIGNzrhZ0D9y2ghQzKqg9jqIC+nYdqZ5L5fntLjXiSQFNOjRo/XRyfE2OyH86m8RveLwXolAkFrLKmNkfSjsNmG5Xv37b96CRxniXDYZrZbuCUyiKJVmUMpR1Ooq2SDnJDEEHbG1T7zijQyoILjXEuhGhcRsHUYGFdNw4zgBhuVI3YYNNzPzKBIkN3HqinGpl06hqVumfCSCDhUIKsCCfPzn3HLTPNE9vOIp1Z5XDHBdOpo0gj16R75H51H9wkcYuG6kUMYTz0EhcYlCAKoO6knGoLjYbKSCdO524RNIxuHROp/wCKY8AEiNWLFQMZG5JB9c9snf7m6SG4hjngZCsS6LgugUMpd28QLDBKagGHkO2+Ku/4z1V6K2/U8RQ9SNPHFhcDUjbnSR3HddiKravKNNsOW2O8WhyTTRin+Pf/AOaXQ8TpkalBHde2QT3GQRn2qtbii+Sk/UgVzfLv7PfnrOn1vklYrBFQW4qfJB+JzTR4i/sPwq0YrsbdfgjxMz/CyxScVWG9kP638BSDcOf1j+Zq8Yp92FviOP0rK1IpBcDuR+YqqLZrFWjF92FviHtX9rNrhB+sP4mkNdp7n8P+tV9GatGOGVutvPiITGvB5KaQb39n+NRqKn5dWU9Vkn1PG7b2rBuG9f4CmqKtxhSct59ZKMjep/Okk0UVLOZmfIoooogUUUUGxgV0z4bM8NqNK/20uASQBqKsN8/sqCMeu/cVzSus/D3SLONWPi3cA/KBvhs42xt+X5cWH6n0fxS2sUR7ytgkvRPiXMkjE74MZJWM5yNwoAP8fMU9dMYwIlI6kQCDSxYqzsEQEkZ7lT+79KW2NTeZYHKnAyOxLnzAJUbebDfFRpQisrzaA7mNUZxks6HwqSAdjuDk75866tvn9MHhYFw925y8muLRvgLgLkr55dNRXtjGO28XinEoLaaMTyM5n0qGWNOmBIdALMTqwCfwxTnFre4NyZ1ybdYnaIAAaZSukLgbnHi33A2xWOYeHWKHqXJAEcSIgfT4cA+IIe7djnf+FWQj898Su4IIXtV3YGOTTHqcOpCnGATvj/SsrbXDcPcHU0gt4g482lbWScntp1D0p3jMkEML3M6vLpYI0YbQuSiuMYG4wwP40qx4hHNw9ru3iVXMeoAqGKsuuMqTjLeJcH1yD50FHyPY3XD9Us6krJJEAqvrcMpckYG3iUkd9yBTfFuR7uS+/Sunpg6schDMNYQMpPgUnfAPnT3I/MN1ezSNKykRvAoUKAAZJgpPqfCredQ+N8fuY+L9FZnMSzxoU7gprUEE4+7Ud0dllzfy7NeQRCDBbqSyMr6k0qzsRjI7HJ3x5VJ5Ws5YbKW0K9SZEkH2eWXLuXGGIBPkDgefnUX4k8TuIFhaCRlUqBlcEeEvnfG25A9sYqRwniVweFSTvIeqluZw+AO8kuM4GCNEY2PpUip5MtOI287rcdVRoXSrtqQubiJcackZIZh+NC8Zni4s1oQpi6hTBXBCaMndcajjPzZ3qZyHzBLe9U3Ol1g0Pvk7eN2+YkdohsBSOBcxQT3RgmtySpbUwkzGSmx+zI2GRgDJoJ/Gr7h0V3HaXduXdViClBgLIRkscEHJJG4yfDUPm7h0dxcQtA8jyx6OmMo66CxPi0eIJ/eHODqz7L4oLOe/6EsapcxSEhw7q+cmVUJ0lZF0soHmB6dqk33K0txc288YA/RVRZUDblj9qFHbYaxke+2aCJdRs97EjRmQyJIySaSgD5eNWcAAFWDKAPUE+Rzbx9OOORklUxmUois6r00EiBzq7EN0g2VGR1DkE5y1DxuVOITWxhAjVE6ZdMltHgXcn5ckE9iCzd9qgcJtw8LSKqvG8vVMSDCqTKiBMZzqwG27bb5G9Bpvxcj6+LgE/ZsyHJXSVZiQVC7ZByuc7gDYefOa7T8RSFsZYI0QsR87aclIQmopkDK6hJvvsFPrji1IJFFFFSgUUUUBRRRQFFFFAUUUUBRRRQFFFFAUUUUBRRRQbJXV+UYT+jQnbUEQb/KVZVzn1GMj865RXTeSrs/okMvcLmJ8eWljpP10lf8As1yYPqfQ/Fqz8uJ+7ZbdBHrYLt9mo0nP2ZJGTkAABnyxHbbuKzoiaLWzA+ORwdQwpUrENvUquf3z54p2EAMPRiMEHwjI8/r5fWo/EVjnVEkjYdOUSISAu6sCwIGdm3B9yfauiZ93gQZ4/wASkghWeOMO4kERBBLMioHIQggKNOQTjbDGo/OXBor0wa30MmnIIB1x5DAYB7429N6m8a4r+h2ys8QcapNWk52cnZgyjbxoM7+W29Inlm/QZZIw6yBI8BMmQOy5KagMkAFd/rUhfGYbd7bpXmUV5GcksFGFKpuwPkunsfSovAbOBxptJyIAWOItixA8ahycY7HPnk+1RJI2uuHIt0DFKUZTqwcsraS2FJPiaFScA48VTOS7FLO3SKSVQdEvj+XJkmAGA2+Qox71IOWuLWdxI62iyJ0ijNqwdRJbBXJO+Vx+8O9Vyc7n9IS1MBaSRo0ZwyhcuqDUBpJ2zn8M0rgvK/6DIZoJS7yYXQ5TTgsHDHG4IKr+DEd8VJPAbJbxZxcI04GVhTV+rGRsWffCjO/pQZ5g5lThccAW2jcSoWBwNe2BliRvkHv7UvifG1js1uCpCTojugxkI0ZfQMnHzToB/PyjcyW1hcmOK6mMTQqIlDSRp2SPOCSc7+fvT99wiCSzWC5mMSr4Iyg1MY1EQGSBjJMIzsOxoI/LM0FxE88Qlwuco2lAQhj150E5GiUjB+8dqa5ZSwuLhp7SMK+wbDSkaXbJYh1AG69wO2qp/DOHRWtqYIZGZZDpjcqSWMsiMdlGxCxnY4+WoXKfLT2UFwu+ZAi6nxHgE6AMZJ7O+/lt+BCfacsJPeniqyhtZJMR2CjT0hhiPm0KDggYJqq4OLluKvK0gaNzHkRy6lHUlA0OoOx3lxkdl2JArHJHBJrdrm4uAjF1eQmNg6spjlLbr+0Vpnl+6ltbSe5uSR9oQgZFDZCsNW4yW3yCc7qB+tQS+AT4a8nkUKtvMdMoPjkCMwRCSCBuV8sdzgb1PiSON7aCFzh3LyDs+ZAcSBlONIeTbB/X8/KPxW9jns7aB41iFx0whBcsDGFyMrjJKnAJ2Oogg9jNRNMqnVuscNuIzuvVaRnV2xthdab+ZyNt8BTcxxJHZSPP9rI0UoVQPmDuuoYG4wXffyI+bc1woV2fnRNFjLPOdLoskURJw5XESLt3Gzydjv3OSSw4zSCXVfhdwywk4XLcXlvGwilk1SMmpgixxv5DJA1HYUrnPkuweCDiFioELSRdRVLhHikdU1AHdGyQCNu57EbvfCG2jn4TdQzMVjeSRXYELpRoIwW1MCBgDOTttUzne7s+G8IWxgmDHVGI11q8hC3CztI2nyyrb4AywAqUNZ+MnK9pw8236JB0up1dfjkfOjpY/tGOPnPb1qbwXkaym4Ab94264huJNQdgNUckoXw5xjCLUz+kShxZnyDXA/Pokf6GrTlTflV/a2vf/wA5zQVfIfwvseIcNguZmmWSTqajG6geGaRBgMjeSCnuZfgnEkLvYzSGVAWEcuhg+BnQGVV0sfLIIztt3Gw/DC2MvL8MQIBeO5jBPYFpplyfberDlOwbgnDCt3P1BDrlLAMQqdxGmdyNj5d2NBw/4dcspxS8FtJI0alHfUoBOVxtv9asvihyFHwf9H6c7S9bqbMgXTo6fmDvnqfwqV8Bt+LZ/uZTt9VrYP6SPax/8z/6FBy7gPDjdXMNsG0maRItWM6dbBdWMjOM5xnyrfeYvgvNaW0tz+mRusSNIV6bKSFGcDc71q3wyTVxazH98p/LJ/lXfPic+OEXh/umH5kD+dB5hrauWPhvxHiCh4odER3Esp0IR6qMFmG/cAj3rZvghyNHeM19dIHiibRHGwyryAAlmB7quRt2JP7OD2bjXGreyiM9zMscY2y3md8Kqjdm2OwBOxoOMn4D32jP6Vb6/u/aaf8AFoz/AArVua/h5f8ADUMs8SmIEDqowZMk4AI2YfiPOuux/G3hRk0ETqv+0MY0fkGL/wCWoPxm5gt7ngqy20qypLPGgZT2IV5MMDupwvYgEZoOE0UUUBRRRQbGK6H8KZ0eOeA7sGEmn9grpZsZ8iF7D9YVzqrjlC86N7A+vSOoqsxBICv4DkAjyP4d/KuHHbjaJfV9Xj+Zhmv8/h1q1l6ZMMny/qk9sHyNTNRTJwXAJOnPiwVPyk9jqwc57Z/DFxbq4KkdiQPUH/sVCgmKMFc9uxrt8vlfBfDLVpIjHM3UV9ZIOCRqz9kWPbAY7ntj2Aov7+SOCR4CkgMhOADnXGo8GQfWJvIdqkrqUiSLTqBJGoEqSV074I/1/wBKY4TaCJmEZbLkNpYnAfVrJ9T2J2z2HbeqpR+UeIyXcPXlQIWWVwqDOOnLoGnVnctk7d+9V3KPEZp4JkvdYkSVWDyqUBQsjDJYAYBVvp4au4nCoy27INGF8KFAqhw5wvoTtnbvn3qn5b4ub4u89ukYifpkZJDFIzISy+HBUD/MasK/4dcvNb3EzNoIMkaIysjalEhdjlScA6E705e8GuF4seIkKYQS50upYI1uVzpPf5qm8tccku7q6t5Qo6J0xaB0yP7RDkqRnfRVdwM3bXtxA7uLeNWVYyoChmKoiA4ztqOwPlQSubuTZ724juFAWHYvqyrANKWI0kZzpI9qz8RbCS4toYotPgfqkEgf2hlwN9h8p71GvOJXD8YjTW3Qy5w3ylQsjDc+WkJtnFY45NcLxWOC3zGhI6mFXHTBIwwIxjTGxB7/AGlEHeM8Nmk4XHb20TSFVSMlRtso1E+WPnG336XPazW/BQqqzT5VcDLsCQWJA37BwD7L7VH5pv7g3EFrASBJp1jfSG6cTk4BAwAxznPy1YcV448HEoYYME+MyatRCqVBYjB74jUen2bbdjRKsu+INYcNj16TcORgFQO7k4IXHh2wRtnSwPernmriTyC2t5YlZp9LOqxqx8LEEHWcafLO/c/UN3/EHuOIi36SkIviIVdO3UXdjuCGz2x2XepP9Z27LJczHxRfYRSr2UaMZGrwgswc75x277UCbpYB32NqrJHtqj1sC2GwM7KVGPZu/em4LcqIJZxvGhmfzZpTJIVUZGWHhck7bDUTtmmFhihtEhlmY5lNy7A+NgApCg4GxLEBsAYQ9u1O3kZSdpXyT0jH01zgvLGD0lHqqsTq7nJPniggXnC/60iaKd3SPRGB0yo3zrJOdQOWGc7Hv2Gw5NzdwWKzvXtY3d1TRqZkGQWAOwU7gBl9N8j3PduHA6NTKFZ/EVUYVdgAoHoFCqPYVo3M88/CuIy33R61pciNZRgHSVAXGfJsrkZ2OrHcZEwqr+D30XCLfiHC7yTEzo4VkVmjJlthgZwCD4l7itF4jwO4t0SSWJlSQZSTZo32z4XUlTt710DjUNhc8Zke8dBBPZxyJIzFPEUj0upBHi0qcA9+2DWsT3DPwKJWORFfOq+ytCH0j94sf3qDqF5YQcy8MhZZunIpViQA5jlC6Xjdcg4Ocjt+qe2xlcZs4uE8BltzJqCwSQhiMF5JtY2Xfu0hON8AexNNfDjkt+FNOTOkyTCPQyqVPgL5yNxjxjsT51zj4ocd63FXSRmkt4JETpB20+EL1QBnAYtrGRg7D0oOmfCwv/UMXTzr0XGnHfV1pcY984rPwo4lxWZJxxOORdBTpvLH03OdWpcYGpRhTnHmdz5WTxx8J4bI1quqKCOSVEYkggkyYDd8Ek9896icm85XHFLWSeC1iR0kMeJZ3CEhVbVlYiceLt7d6DX+WuFJa81XEcShUMBkCjspdYmYAeQ1FiB5Aiof9I872Q9p/wD0f+lVfw75jB49LPeTIzzh4VaNZGR3LoiLGApOjSmFJ8gM711ji1twy+k6FyLeaSLbpuydVNQB+XOtc4B99qDgvwoXPF7Qf3hP5Ruf5V234xSleC3RH3Y1/wAU8a/zrXb3lSz4fxrhX6HD0uq1yXGt3B0RDTjWxx8zdvWrv41Pjg1z7mIf/cRn+VA/8HEVeDWuk5yJCT+0Z5M/kdvwrm39Ia+kbiEUBJ6ccKsq+Wp3fU31IRR+7V/8AeaVaJuGyNiRC0kQP6yNuyj9pWy2PRj901ffFb4ff1siTQMqXMQKjVsskZOemzAZBByVPbxMD3yA87U4JmCGMMdDMrFc7FlDBWI9QHcA/tH1q9uvh/xSJxG3D5iT5ohkT/GmV/jTfMvJ91w6OF7tBGZ+ppj1BnATRktpyBnqDAznY5xQUVFFFAUUUUF/ms5pGazmvP0+vi7rvw34493bFJXLPAQm5XLK+yn128QOe+kb77XxsNXi+ZSM7AjGS2MefZa4jwfi0tpKJoWww2IPZlPdWHmNh+QrtnDOIi9gWeJ2IkBxsAyshzpIGNwVIIzgjsd8100vuHg9d0/C3KPE/oKrR9tx/H8R/MU6GVx/3/A0oRGGMmRydOkknLMclgewHqvYfgM1GieOUa42BBzuOxwcH675GRWu3Bo5DbLFqaMYYjufPJ9MHy32oFuQsioI9cgdy6roGuQBWYjzbGck7nPtRqI7/wDWnElqRC8FyzOyaNDFpCCAWAVmIDDcDbOPpTdnx55ZZtVuIhDo6bD7RpSS2MgkEkMqgEnu3lU+SIFXUADWrA+niGCT+G1N2HDo0l6ukIzNqk0Z0yEaj8rZC5LZO3n+NBSctcSe6ae2kQCO1wvVydygYYxjcYjbz7YyN6k8K5qlvbiaJ0CRRAZ0gNmRiAFwBnPzDGrf/Szu4V0SrFEsZlDKdON3fUCxI3JOTv7jbvUdYUt3afpbSudUey+LQ7Z8IPnjBxnf1oK7l/i/XmuZHiylu7OW1bM2CTGFAI8ONOQSDt5UcC4kkwmuXhRFj8GsqEeTQpVsyZJKnSDk7DHluRNWJYoBEkIjEjplV1EEa1JGo7liFAO+e/ak8SsYJBHaRQt0lGZEDkKA2lwM922Gcbd+/eoDdzfQJZqxRxPdKPGNmkJbJONyActtjJGRiky2tvBELdkbpxAvpOd2yrqrvjc7O2R5SCpssUZLTt88YSKEFCwU4LM+nbckvj2WkCy1GNHzIEBkLMdIeZyD4hnJAUFsftYznFQIrxCUL1WBYgyyKhI1INlQZwQiFGyT6+ZYZcWJpWJuEXXqLDue5Bxvjy0e3cee1TxbiyrKzQKCx+yM7YVCWYZA7avlVQBgYydsk1dW+rGXOpjuzepO/wDOphMxMRtLU1o3xDt+JlpUt4+vbXKRoUVNTRMuMnGdskZ1dvoQDW7IaeQ1ZRy+blaObiVrY3RYYsEBKMARJGH7Egg40kdqlfE7l6Dh/CYYLfVpF0GLMQWZmhkyzEADsoGw7AV0oICQSASucEgZGRg4PlsSKrOb+Wo+J24t5ZGQLIJAy4J1BXUZB7jDnbby3oKn4fcrtweO5mlnWSB40lBAK4EayMxIOQBgjcE9vauacO4Ub2x4jeuA0qPHIuMavFIzSkDvpCuD+HtW52/Id9Y2l5DbyrcdeJY0UHpkEyAOSrtoH2ZbcN/71nDfg5LNbRyNP0Jzq1QyJqUYchfEpyMgA9j81BtdjxP9J5ZdyfEtpJE3mcxK0eT7kKD+Nc04VzcbXhE1lESJbiZi7Y+WHpIpAPq5BX2Ab1FX/wAP71l4VxaycaWijkkwe+WjZHGPYxp/irVIOXmfhb38ecw3HSkHpGyIVf2w5x76x6UHTvgzyN+jIL+5T7WQfZKe8cbD5z6O4P4Kf2iBp/OYzzMf+Jth/CKutfD/AJlHE7KOckdVfs5R6SKBk49GBDD/AHseVcl5p35m/wDN2w/5VBb/AB34nLBxK3aGR43SDWrIxUgtLKpII9QuD6jar/4d8Zj49YSWHEGaSWMhnOrS0kevUsnhxurYU/Rc5zWp/wBIM/8AxKH/AIVP+fNWu8Gu7jgl7b3BHzRRzYBOJIJkDFfyJHsy58qDeeePhtbcKt24haz3CvEyFRrTILSBcqwQEYzn8O9bJ8K/iM3EmNrOhM0cRkMwVVV1UopLICdLZde2x32XtTfxk4hHNwPqxNqSZ4WVvVWOsfQ7dq1D+jrBm9uZPuwaf8UqH/8ASg7oDXFP6Rl4rz2sasC0aSFh5gsyYz/hpXxd+JM3XewspDGsZ0yyocO7jvGrDdVXscYJII7d+Z8O4Vc3TMIIJZmG7dNHkIz5tpBxnfc0EKipN/w+a3bpzwyRPjOmRGRseuGAOK3LkCLhV+4s76DozPhYriKR0DtjARkYlFcnsQoBO2AfmDRKK7XxH4DwN/YXsqe0kaSfhlSmPKqOf4D3ur7O6tmX1bqofyCkfxoNKzWc03ms5ri0+nixzNWnLXH5bGZZI2OnI6kednXO4IO2cZwfKqjNZzTwW1aONvDu3COKWt4heKZXaQEaGPjUjBCmMnPceX3gQdgal2ska61txrxHJqf9UEgH8WyoJG52OdzvxDluRkuFkRtLp4lP7yggg9wVLDHvXT+Ec6ozBTCsba1Bk1HR5DJ22GD/ABOSNzXRSZtG3hdRjrjvqJWvDUlVWlnl+yOkrqXDJgkNnYFh7kDB8qeglWQto30kjUMEEA4yCMgigROH68kqdJdUiDUSBv56huQSMHtjt5YRdFl/+QiEfV8ZYx6V1nPiBxnOT3wcbVbbBaWt+Eyrxhh2PsfQg+dOTTW7jIUqf+/TIqovVhRWaONVn8MRdFByMHSCT3bGdu5wPasWVrLFDhs3Erayqr4TjGrbUd8e5/XA96naNJTuB23pF5eMY9KIC3kx8h+Wc1CM7ooEyESsRpjVWOxKjxYyNsnO+Nts71meJIiw1GVpBh0xpaNQdQfPbYFTvjIAPnQQ0FwvY59yFPqdsj3P50iZLhslnA1Zz2GcjTvgem1Lj4YpyWncxgLpkVSVZmbTpALb7lRkbZb2NLPBjC2XjDE4bSzqXjQHeR1HgxgbAsB7sdqbNEx207lTLKzgEEKpIBI99u3tUDmHj/SDjUuPlJ/UH92MDJ+g/EmofG+fuiGEDK7v8p840IA05U4DbZ7EgtjIxWicT4nLcP1JWycAADZVUDAVQNgAMDHtWV8kV7Q9Dpegvknd+1f2VxDiEk7apGJx2HkPoOw/CpPCuP3Ft/ZSHT9xvEn5Ht+GKqs1nNcu7b3vu+gjFi4cOMa9nQuE8/QvgXCGM/eXLJ9cfMPpvW22d0kq64nV19VII+m3nXEc0/Z30kLa4pGRvVSR+B9R7GtaZ7R5efn+FY798c6n28w7ipp1TXNuD/EaRMLcxiQffTCv9Svyn8NNbrwbmC3uv7GYFvuHwuP3TufqMiuiuStvDx83R5cP1R2947wuFNOKaZU0tTWjlVdzyhZyGZxEI3uI5IZJIzpZkkwWyN1LZAOSpORTHK3JMNjbXFoZGmhnZiQwAYK0axlSR3O2cgDv7Zq/U0sGg5NyVw694DxFkmhkNpMTG06rqjwrHpzsVzoxncMRgO3pVNxzfmhf+Otx/niFd1Vqr7zl60mlSeS2jMsbpIsunTIHRgykuuCcEDY5FByD4+tniae1tH/zJT/Otp505SN5wK0mjXM1rawsAO7R9FOonudtQ/3SB81WPxB+Gw4pMLhLnpSCMR6WTUhCliNwQVPiPr9K3ThkBigiiOMxxohx2yqBds+W1B5uPNDtwr+rXyQlwk0Z9E0S60+mtlYf7zVvP9HbZ72THypF/rIcf5a1b4rcq/1det01xBNmSL0XfxRD/dJ2/ZK1uX9HKPw3rkbFoF/ITEj/ADCg5FcTNIzSMcsxLMT5sxyT+Zr1NynwGLh1rHbRKBoUaz5vIQNTt6kn8hgdgK8z808IayvJ7VgfspGUZ7lDujfipU/jXprlfjcd9aw3SHIkUFh91+zIfcNkUEfmjgdvxa1eByj7HRIpDGOTycEdsEbjzGRXIeH/AAU4kxBklhh7Hd2Zx9NCkZ/eqj4zynxeyle4e3mjJLuZYjqCgksSXiJ0j64pfCvihxW3wBdtIo/VmCyZ+rMNf5NQei+GxypDGs7iSRUUO4GA7AAF8eWTvj3qTmtW+GfMc/ErEXNxGiMZHVemGCsi6RqwxODq1Dv5Vs+aDytmjNJzRmuTT6DkVms5pOaM00tybPyZwrrxXco+aGOMoPVmmU4+pEZH41Ks/wCzz6kn+JA/gBU74eLpsZSvzT3KQ/8A0omkH+Z1/Or3nblJrctPbKWhbJaMDLRnzKjuyHvgbjPp26KRqrxuqtyyS1W34lLE2Y3xg50kBkz7owK/wrYbXn+TSY5Y10nT4oxhgR3IVjgE+2K1ENncUqrac+2/WfPFrbjCMQjEl2lB3LbAnQGy3buR270PzJZRjqQXMLSOulGLdNYznOlmkY4TIGBnG3vvyjitwZHEa9lOPq3/ALD+dROItjTGOw//AIP4Z/Oq+ulvTbs9zznYZUtf6piBvqBhwVOSdOFUfXcED3zFk+IEFuenHOJV3aaRBqUsxwOk2nTnPqDspwD3rkVjYPL4hsg+Zzso/wCp9hvT0jr8qDCj17se2pvf27D8ya2txhv02Gctvt6t64l8RIy+YLZ8DxAySs2qTYa3BBz2GACP5Vqd9xy4m1B5ThjkqPCpIAXcDvsAN6rc1nNYTa0vYx4MVO8R/k5mjNN5rOapp1RcvNZzSM0ZqNLRcvNZ1UjNGaaW5nNVZV8HI7ims1nVTSebaeC8+XdvhXbrJ92TJbHs/f8APP0rd+Cc/wBpPgO/Qf7shAX8JPlx9cfSuP6qNVaVyWq4s3R4cnfWp94eiEbIyOx3zTgNcG4NzJdWZ+wmZVz8h8UZ3+6dhn1GD71vXA/inE+Fu4jGf9pHlk+pX5lH01VvXLWfLycvQZKd694+3n8Ohg1kGofDuIwzp1IJUkX1VgcH0PofY1KBrVxTEx2k6DSgaaBpQNEGeJ8Lguk6dxDHKoOQJEVsHtkZGx9xUblzlq24eJFtY+msjBmXUzDIGNtRJHf1qxBpQNBpXxS5B/rONZoMLdRDAzssid+mx8mBJKt23IOxyvJuBcycR4HM8SgxnP2lvKpKE4xq05BG2PEpGQBuRXo8GoXF+DW14mi5gjmUdtaglc/dbuv4EUHFOZvi9d3lu9usMUKyKUkZdTMynYquo4UEZB2Jwe4rSuF8PluZUghQvJI2lVHcn+QAySTsACTXeJfhBwlmyIpFH3VlfH+bJ/jWxcvcrWfD1ItYFjLbM+7Ow9C7Etj2zj2oHuVeDLYWcNqpz0kwW+85JZ238izMfxqzzSM1nNB5WzRmsVjNc72uReaM0nNGajSeTp3w/CsnDolPeWeZx7h1UZ/di/jXSuNyYAFch+FvEI4b62jlIUPHIQx7a2L4U+mQO/rj1rq/GWzIB6AVvXw8nJO7zP3lQcQ5et5zqeMBj3ZfCT9fI/U1rXMPLsNugIlbUx0quBn67+QG9btM4UEnYDeufcUv2uZjIOxPTiHoM/N+Pf6CkzqNqRG5VvDuVIyDJ1XAXIyQvluT/rv7GoIs4AxcRaj6yHV/l2X8wa2XjUoggES928P4eZ/0H4mtWu7tYxvufIf9+VUrbtyle1P6opX/AKTPGLwnEefr6AeSgDYetVuaw7kkknJO5rGaytO5evgrGOnGCs1nNIzWc1XTfkVms5pGaM00nmXmjNIzRmmjmczRmm80ZqNJ5nM0ZpvNGaaTzOaqNVN5ozTR8w5qo1U3mjNNI+Yl2d9LA4khkaNx+sjFT9Mjy27VuvAfirPFhbqMTL99cJJ9SPlb8l+tc/1UZq9ZmPDDJTHf6od/4BzbaXuBDMNZ/wDCfwyfTSfm/dyPersGvMhNbRwD4iX1rhTJ14x+pLljj9l/mHtkkD0rWuT3efl6PXek/l3QGlA1pXL/AMT7K4wsxNu58pN48+0o2x7sFrco5AwDKQQRkEHII9QR3FaRMT4cdqWrOrQdBrINNg1kGpVOZrOaQDWc0C80ZpOaM0HlbNZzRRWD1xmjNFFEth4VDm5I8oo1T8dKr/rqrcLLmqeEhXHVUAABiQwA8g2+31B/Cs0Vu8iTvMPOEDW5TEiM+xyBjHoCD/KtbsOOQBi+GYKNKgKO/mdyPYfnWKKyy+GuPyqOPcxPPKdC6VXwjJyfc+gOfr2qnLEnJOSfM0UVFvDo6SImZtPkZozRRVHczmjNFFEjNZzRRQGaM0UVAM0ZooosM0ZooojYzRmiihsZrGaKKIGaxmiiiBmsZooqyozVnwTmW6sjm3mZBnJT5oz9UbK5274z70UVMKW7x3dC5e+MEbYS9h0H/ax5ZPq0ZOofgW+grf8AhPGILpOpbzJKv7JyR7MvdT7EA0UVpFpcGbHWveE0Gs5ooq7nZzWc0UUH/9k=",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_800,h_450/https://jefmenguin.com/wp-content/uploads/2023/06/motivational-public-speaker.jpg",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "data:image/jpeg;base64,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",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_800,h_450/https://jefmenguin.com/wp-content/uploads/2023/06/motivational-public-speaker.jpg",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "data:image/jpeg;base64,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",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_800,h_450/https://jefmenguin.com/wp-content/uploads/2023/06/motivational-public-speaker.jpg",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//            )
//        )
//
//    suspend fun getAllCourseCategoryFilters(): Response<CategorizedList<String>> =
//        Response.success(
//            CategorizedList(
//                categoryMap = mapOf(
//                    Pair(
//                        "EXAMS",
//                        listOf(
//                            "IBPS RRB",
//                            "IBPS PO",
//                            "SBI PO",
//                            "UPSC CSE",
//                            "IDBI",
//                            "RBI ASSISTANT",
//                            "ICAR",
//                            "SSC-CHSL",
//                            "SSC- CGL",
//                            "IB"
//                        )
//                    ),
//                    Pair(
//                        "SUBJECTS",
//                        listOf(
//                            "QUANT",
//                            "IBPS PO",
//                            "GA",
//                            "ENGLISH",
//                            "IT",
//                            "SOCIOLOGY",
//                            "HISTORY",
//                            "POL. SCI.",
//                            "ECONOMICS",
//                            "GEOGRAPHY",
//                            "PHILOSOPHY",
//                            "PSYCHOLOGY"
//                        )
//                    ),
//                    Pair(
//                        "FACULTY",
//                        listOf(
//                            "ADITYA SIR",
//                            "ASHSIH SIR",
//                            "GAURAV SIR",
//                            "HARSHITA MAM",
//                            "IDBI",
//                            "LOKESH SIR",
//                            "MUKESH SIR",
//                            "NIKITA MAM",
//                            "SABA MAM",
//                            "RADHEY SIR",
//                            "SHEETAL MAM",
//                            "VIVEK SIR",
//                            "YOGESH SIR"
//                        )
//                    )
//                )
//            )
//        )
//
//    suspend fun getFilteredCourses(filters: CategorizedList<String>): Response<List<Course>> =
//        Response.success(
//            listOf(
//                Course(
//                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                ),
//                Course(
//                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
//                    title = "PUBLIC SPEAKING",
//                    originalPrice = 1000f,
//                    discountedPrice = 400f,
//                    isBought = false,
//                    tag = "Most Popular"
//                )
//            )
//        )
//
//    suspend fun search(text: String): Response<List<SearchResultItem>> =
//        Response.success(
//            listOf(
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics"),
//                SearchResultItem("id", "#Geo-Politics")
//            )
//        )
//}

interface CourseApiService {
    @GET("/v2/course")
    suspend fun getAllCourses(): Response<CourseList>

    @GET("/v2/course/chapters/{courseId}")
    suspend fun getAllChaptersForCourse(@Path("courseId") id: String): Response<ChapterList>

    @GET("/v2/chapter/resources/{chapterId}")
    suspend fun getAllResourcesForChapter(@Path("chapterId") id: String): Response<ChapterResourceList>

    @GET("/cart")
    suspend fun addCoursetoCart(@Body body: CartPostRequestBody, @Header("Authorization") auth: String)
}