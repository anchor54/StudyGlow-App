package com.example.studyglows.network.apis

import com.example.studyglows.shared.model.CategoryFilter
import com.example.studyglows.screens.home.common.models.CourseProfileModel
import com.example.studyglows.screens.home.common.models.Educators
import com.example.studyglows.screens.home.common.models.FAQ
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.screens.home.common.models.OngoingCourse
import com.example.studyglows.screens.home.common.models.SubjectwiseCourse
import com.example.studyglows.screens.home.common.models.PopularSubjects
import com.example.studyglows.shared.model.CategorizedList
import com.example.studyglows.shared.model.SearchResultItem
import retrofit2.Response
import javax.inject.Inject

class CourseApis @Inject constructor() {
    suspend fun getMostPopularCourses(): Response<List<Course>> =
        Response.success(
            listOf(
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20220805/ir-thumblin_RYUQVut.webp",
                    title = "International Relation",
                    originalPrice = 2000f,
                    discountedPrice = 20f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20220731/sddefault.jpeg",
                    title = "Modern History",
                    originalPrice = 1000f,
                    discountedPrice = 600f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20220629/1mg-logo-large.png",
                    title = "UPSC History Course",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                )
            )
        )

    suspend fun getCurrentlyWatchingCourses(): Response<List<OngoingCourse>> = Response.success(listOf())
    /**
     * listOf(
    OngoingCourse(
    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
    courseName = "Public Speaking",
    currentChapter = "Chapter 1",
    progress = 0.25f
    ),
    OngoingCourse(
    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
    courseName = "Public Speaking",
    currentChapter = "Chapter 2",
    progress = 0f
    ),
    OngoingCourse(
    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
    courseName = "Public Speaking",
    currentChapter = "Chapter 3",
    progress = 1f
    ),
    OngoingCourse(
    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
    courseName = "Public Speaking",
    currentChapter = "Chapter 1",
    progress = 0.5f
    )
    )
     * */

    suspend fun getPopularSubjectCourses(): Response<PopularSubjects> =
        Response.success(
            PopularSubjects(
                listOf(
                    SubjectwiseCourse(
                        subjectName = "Prepare for SSC",
                        courses = listOf(
                            Course(
                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                                title = "PUBLIC SPEAKING",
                                originalPrice = 1000f,
                                discountedPrice = 400f,
                                isBought = false,
                                tag = "Most Popular"
                            ),
                            Course(
                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                                title = "PUBLIC SPEAKING",
                                originalPrice = 1000f,
                                discountedPrice = 400f,
                                isBought = false,
                                tag = "Most Popular"
                            ),
                            Course(
                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                                title = "PUBLIC SPEAKING",
                                originalPrice = 1000f,
                                discountedPrice = 400f,
                                isBought = false,
                                tag = "Most Popular"
                            ),
                            Course(
                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                                title = "PUBLIC SPEAKING",
                                originalPrice = 1000f,
                                discountedPrice = 400f,
                                isBought = false,
                                tag = "Most Popular"
                            )
                        )
                    ),
                    SubjectwiseCourse(
                        subjectName = "Prepare for UPSC",
                        courses = listOf(
                            Course(
                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                                title = "PUBLIC SPEAKING",
                                originalPrice = 1000f,
                                discountedPrice = 400f,
                                isBought = false,
                                tag = "Most Popular"
                            ),
                            Course(
                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                                title = "PUBLIC SPEAKING",
                                originalPrice = 1000f,
                                discountedPrice = 400f,
                                isBought = false,
                                tag = "Most Popular"
                            ),
                            Course(
                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                                title = "PUBLIC SPEAKING",
                                originalPrice = 1000f,
                                discountedPrice = 400f,
                                isBought = false,
                                tag = "Most Popular"
                            ),
                            Course(
                                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                                title = "PUBLIC SPEAKING",
                                originalPrice = 1000f,
                                discountedPrice = 400f,
                                isBought = false,
                                tag = "Most Popular"
                            )
                        )
                    )
                )
            )
        )

    suspend fun getAllCoursesOfSubject(subjectName: String): Response<SubjectwiseCourse> =
        Response.success(
            SubjectwiseCourse(
                subjectName = "All Courses for $subjectName",
                courses = listOf(
                    Course(
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        title = "PUBLIC SPEAKING",
                        originalPrice = 1000f,
                        discountedPrice = 400f,
                        isBought = false,
                        tag = "Most Popular"
                    ),
                    Course(
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        title = "PUBLIC SPEAKING",
                        originalPrice = 1000f,
                        discountedPrice = 400f,
                        isBought = false,
                        tag = "Most Popular"
                    ),
                    Course(
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        title = "PUBLIC SPEAKING",
                        originalPrice = 1000f,
                        discountedPrice = 400f,
                        isBought = false,
                        tag = "Most Popular"
                    ),
                    Course(
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        title = "PUBLIC SPEAKING",
                        originalPrice = 1000f,
                        discountedPrice = 400f,
                        isBought = false,
                        tag = "Most Popular"
                    ),
                    Course(
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        title = "PUBLIC SPEAKING",
                        originalPrice = 1000f,
                        discountedPrice = 400f,
                        isBought = false,
                        tag = "Most Popular"
                    ),
                    Course(
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        title = "PUBLIC SPEAKING",
                        originalPrice = 1000f,
                        discountedPrice = 400f,
                        isBought = false,
                        tag = "Most Popular"
                    ),
                    Course(
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        title = "PUBLIC SPEAKING",
                        originalPrice = 1000f,
                        discountedPrice = 400f,
                        isBought = false,
                        tag = "Most Popular"
                    ),
                    Course(
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        title = "PUBLIC SPEAKING",
                        originalPrice = 1000f,
                        discountedPrice = 400f,
                        isBought = false,
                        tag = "Most Popular"
                    )
                )
            )
        )

    suspend fun getCourseDetails(courseId: String): Response<CourseProfileModel> =
        Response.success(
            CourseProfileModel(
                imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                courseTitle = "UPSC IAS Live Foundation",
                brief = "The UPSC IAS Live Foundation Course brings the best content and expert faculties at an affordable price.",
                educators = listOf(
                    Educators(
                        educatorName = "Shashank Tyagi",
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        achievements = listOf(
                            "he has coached coached many IAS officers, celebrities, CEO, Start-up owners.",
                            "Honours in English Literature",
                            "PG in Public Administration.",
                            "Attempted UPSC CSE Mains twice."
                        )
                    ),
                    Educators(
                        educatorName = "Shashank Tyagi",
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        achievements = listOf(
                            "he has coached coached many IAS officers, celebrities, CEO, Start-up owners.",
                            "Honours in English Literature",
                            "PG in Public Administration.",
                            "Attempted UPSC CSE Mains twice."
                        )
                    ),
                    Educators(
                        educatorName = "Shashank Tyagi",
                        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                        achievements = listOf(
                            "he has coached coached many IAS officers, celebrities, CEO, Start-up owners.",
                            "Honours in English Literature",
                            "PG in Public Administration.",
                            "Attempted UPSC CSE Mains twice."
                        )
                    )
                ),
                faqs = listOf(
                    FAQ(
                        question = "How to access live class after purchase?",
                        answer = "answer"
                    ),
                    FAQ(
                        question = "How to access live class after purchase?",
                        answer = "answer"
                    ),
                    FAQ(
                        question = "How to access live class after purchase?",
                        answer = "answer"
                    ),
                    FAQ(
                        question = "How to access live class after purchase?",
                        answer = "answer"
                    )
                )
            )
        )

    suspend fun getSimilarCourses(courseId: String): Response<List<Course>> =
        Response.success(
            listOf(
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                )
            )
        )

    suspend fun getAllCourseCategoryFilters(): Response<CategorizedList<String>> =
        Response.success(
            CategorizedList(
                categoryMap = mapOf(
                    Pair(
                        "EXAMS",
                        listOf(
                            "IBPS RRB",
                            "IBPS PO",
                            "SBI PO",
                            "UPSC CSE",
                            "IDBI",
                            "RBI ASSISTANT",
                            "ICAR",
                            "SSC-CHSL",
                            "SSC- CGL",
                            "IB"
                        )
                    ),
                    Pair(
                        "SUBJECTS",
                        listOf(
                            "QUANT",
                            "IBPS PO",
                            "GA",
                            "ENGLISH",
                            "IT",
                            "SOCIOLOGY",
                            "HISTORY",
                            "POL. SCI.",
                            "ECONOMICS",
                            "GEOGRAPHY",
                            "PHILOSOPHY",
                            "PSYCHOLOGY"
                        )
                    ),
                    Pair(
                        "FACULTY",
                        listOf(
                            "ADITYA SIR",
                            "ASHSIH SIR",
                            "GAURAV SIR",
                            "HARSHITA MAM",
                            "IDBI",
                            "LOKESH SIR",
                            "MUKESH SIR",
                            "NIKITA MAM",
                            "SABA MAM",
                            "RADHEY SIR",
                            "SHEETAL MAM",
                            "VIVEK SIR",
                            "YOGESH SIR"
                        )
                    )
                )
            )
        )

    suspend fun getFilteredCourses(filters: CategorizedList<String>): Response<List<Course>> =
        Response.success(
            listOf(
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                ),
                Course(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "PUBLIC SPEAKING",
                    originalPrice = 1000f,
                    discountedPrice = 400f,
                    isBought = false,
                    tag = "Most Popular"
                )
            )
        )

    suspend fun search(text: String): Response<List<SearchResultItem>> =
        Response.success(
            listOf(
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics"),
                SearchResultItem("id", "#Geo-Politics")
            )
        )
}