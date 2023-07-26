package com.example.studyglows.network

import com.example.studyglows.screens.courseprofile.CourseProfileModel
import com.example.studyglows.screens.courseprofile.Educators
import com.example.studyglows.screens.courseprofile.FAQ
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.screens.home.common.models.OngoingCourse
import com.example.studyglows.screens.home.common.models.SubjectwiseCourse
import com.example.studyglows.screens.home.common.models.PopularSubjects
import kotlinx.coroutines.delay
import retrofit2.Response

class CourseApis {
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
}