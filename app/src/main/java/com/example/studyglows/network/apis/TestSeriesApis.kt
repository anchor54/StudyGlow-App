package com.example.studyglows.network.apis

import com.example.studyglows.screens.testseries.model.ExamCategory
import com.example.studyglows.screens.testseries.model.FreeMockTestModule
import com.example.studyglows.screens.testseries.model.PurchasedTestItem
import com.example.studyglows.screens.testseries.model.SavedTestItemModel
import com.example.studyglows.screens.testseries.model.TestAttemptDetails
import com.example.studyglows.screens.testseries.model.TestCardItem
import com.example.studyglows.screens.testseries.model.TestDetailsModule
import com.example.studyglows.screens.testseries.model.TestItem
import retrofit2.Response
import javax.inject.Inject

class TestSeriesApis @Inject constructor() {
    suspend fun fetchRecentlyAttemptedTests(): Response<List<PurchasedTestItem>> =
        Response.success(
            listOf(
//                PurchasedTestItem(
//                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
//                    title = "SBI CLERK",
//                    totalTests = 170,
//                    completedTests = 13
//                ),
//                PurchasedTestItem(
//                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
//                    title = "SBI CLERK",
//                    totalTests = 170,
//                    completedTests = 13
//                ),
//                PurchasedTestItem(
//                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
//                    title = "SBI CLERK",
//                    totalTests = 170,
//                    completedTests = 13
//                ),
//                PurchasedTestItem(
//                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
//                    title = "SBI CLERK",
//                    totalTests = 170,
//                    completedTests = 13
//                )
            )
        )

    suspend fun fetchPopularTests(): Response<List<TestCardItem>> =
        Response.success(
            listOf(
                TestCardItem(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "SBI CLERK",
                    subtitle = "Sectional Tests",
                    languages = listOf("English", "Hindi"),
                    totalTests = 170,
                    freeTests = 3,
                    price = 3000.0f
                ),
                TestCardItem(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "SBI CLERK",
                    subtitle = "Sectional Tests",
                    languages = listOf("English", "Hindi"),
                    totalTests = 170,
                    freeTests = 3,
                    price = 3000.0f
                ),
                TestCardItem(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "SBI CLERK",
                    subtitle = "Sectional Tests",
                    languages = listOf("English", "Hindi"),
                    totalTests = 170,
                    freeTests = 3,
                    price = 3000.0f
                ),
                TestCardItem(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "SBI CLERK",
                    subtitle = "Sectional Tests",
                    languages = listOf("English", "Hindi"),
                    totalTests = 170,
                    freeTests = 3,
                    price = 3000.0f
                )
            )
        )

    suspend fun fetchRecommendedTests(): Response<List<TestCardItem>> =
        Response.success(
            listOf(
                TestCardItem(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "SBI CLERK",
                    subtitle = "Sectional Tests",
                    languages = listOf("English", "Hindi"),
                    totalTests = 170,
                    freeTests = 3,
                    price = 3000.0f
                ),
                TestCardItem(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "SBI CLERK",
                    subtitle = "Sectional Tests",
                    languages = listOf("English", "Hindi"),
                    totalTests = 170,
                    freeTests = 3,
                    price = 3000.0f
                ),
                TestCardItem(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "SBI CLERK",
                    subtitle = "Sectional Tests",
                    languages = listOf("English", "Hindi"),
                    totalTests = 170,
                    freeTests = 3,
                    price = 3000.0f
                ),
                TestCardItem(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "SBI CLERK",
                    subtitle = "Sectional Tests",
                    languages = listOf("English", "Hindi"),
                    totalTests = 170,
                    freeTests = 3,
                    price = 3000.0f
                )
            )
        )

    suspend fun fetchFreeMockTests(): Response<List<FreeMockTestModule>> =
        Response.success(
            listOf(
                FreeMockTestModule(
                    title = "RRB OA Prelims Mock Test 1",
                    expiresAt = 1690050600000L,
                    maxMarks = 80,
                    totalQuestions = 80,
                    duration = 45
                ),
                FreeMockTestModule(
                    title = "RRB OA Prelims Mock Test 1",
                    expiresAt = 1690050600000L,
                    maxMarks = 80,
                    totalQuestions = 80,
                    duration = 45
                ),
                FreeMockTestModule(
                    title = "RRB OA Prelims Mock Test 1",
                    expiresAt = 1690050600000L,
                    maxMarks = 80,
                    totalQuestions = 80,
                    duration = 45
                ),
                FreeMockTestModule(
                    title = "RRB OA Prelims Mock Test 1",
                    expiresAt = 1690050600000L,
                    maxMarks = 80,
                    totalQuestions = 80,
                    duration = 45
                )
            )
        )

    suspend fun fetchExamCategories(): Response<List<ExamCategory>> =
        Response.success(
            listOf(
                ExamCategory(
                    examName = "SSC Exams",
                    icon = "https://ssc.nic.in/Content/library/assets/images/ssc-logo.png"
                ),
                ExamCategory(
                    examName = "CUET",
                    icon = "data:image/jpeg;base64,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"
                ),
                ExamCategory(
                    examName = "Railways",
                    icon = "https://www.careerindia.com/img/2015/08/31-1441017494-indian-railway-logo.jpg"
                ),
                ExamCategory(
                    examName = "Banking & Insurance Exams",
                    icon = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQEazpaqWf8mU5yYPAoBTE8kvXN8CsaraYt83Kpn1_4g2X8zZ2j"
                ),
                ExamCategory(
                    examName = "Defense Exams",
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Emblem_of_India.svg/800px-Emblem_of_India.svg.png"
                ),
                ExamCategory(
                    examName = "Police Exams",
                    icon = "https://s3-alpha-sig.figma.com/img/b2b2/7b1e/5de0d2f2d4e872d3725f19245a68603f?Expires=1693785600&Signature=oa-mwU86P40CU~j6kVCG2jzSvtO9x7iBePsR4ah9XQHvNHn5A-AcXbsllnnGz1Rn5oaHBolbiF5DqQlvidtIVcmCEF~jgFQyPcJsa5XPqELf~HLTAT-Q2Su5FXI4fVQfM5u~aCIsAjCDQEqPnAULopMUZ6W-ehFcqdzTqNHDHfMD8sgMbtgYPtV7ZEruMR~PtycRMMKWS39C-RDsm0e0xfmjzxM69hPNOLEM~Rvn7NrNpI537DNaNeFp3N8BcelXeDImDhhIGpr31HVt34ze~entXeOJbQNzA99xXMaGGSn3Z1VI4tvGGC4im8iMWRfoRv~DEm~LfWHZqCA82zZk1Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                ),
                ExamCategory(
                    examName = "SSC Exams",
                    icon = "https://ssc.nic.in/Content/library/assets/images/ssc-logo.png"
                ),
                ExamCategory(
                    examName = "CUET",
                    icon = "data:image/jpeg;base64,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"
                ),
                ExamCategory(
                    examName = "Railways",
                    icon = "https://www.careerindia.com/img/2015/08/31-1441017494-indian-railway-logo.jpg"
                ),
                ExamCategory(
                    examName = "Banking & Insurance Exams",
                    icon = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQEazpaqWf8mU5yYPAoBTE8kvXN8CsaraYt83Kpn1_4g2X8zZ2j"
                ),
                ExamCategory(
                    examName = "Defense Exams",
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Emblem_of_India.svg/800px-Emblem_of_India.svg.png"
                ),
                ExamCategory(
                    examName = "Police Exams",
                    icon = "https://s3-alpha-sig.figma.com/img/b2b2/7b1e/5de0d2f2d4e872d3725f19245a68603f?Expires=1693785600&Signature=oa-mwU86P40CU~j6kVCG2jzSvtO9x7iBePsR4ah9XQHvNHn5A-AcXbsllnnGz1Rn5oaHBolbiF5DqQlvidtIVcmCEF~jgFQyPcJsa5XPqELf~HLTAT-Q2Su5FXI4fVQfM5u~aCIsAjCDQEqPnAULopMUZ6W-ehFcqdzTqNHDHfMD8sgMbtgYPtV7ZEruMR~PtycRMMKWS39C-RDsm0e0xfmjzxM69hPNOLEM~Rvn7NrNpI537DNaNeFp3N8BcelXeDImDhhIGpr31HVt34ze~entXeOJbQNzA99xXMaGGSn3Z1VI4tvGGC4im8iMWRfoRv~DEm~LfWHZqCA82zZk1Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                ),
            )
        )

    suspend fun fetchTestDetails(testId: String): Response<TestDetailsModule> =
        Response.success(
            TestDetailsModule(
                testId = testId,
                title = "SBI Clerk Test Series",
                icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                fullTests = listOf(
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    )
                ),
                prevPapers = listOf(
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    )
                ),
                chapterTests = listOf(
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    ),
                    TestItem(
                        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                        title = "SBI Clerk Prelims- Mock Test 1",
                        duration = 60,
                        questions = 80,
                        tag = "FREE TEST",
                        marks = 10
                    )
                )
            )
        )

    suspend fun fetchAttemptedTests(userId: String): Response<List<TestAttemptDetails>> =
        Response.success(
            listOf(
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1690655400000L,
                    hasResult = false
                ),
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1690655400000L,
                    hasResult = true
                ),
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1692729000000L,
                    hasResult = false
                ),
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1692729000000L,
                    hasResult = true
                ),
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1690050600000L,
                    hasResult = true
                ),
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1690050600000L,
                    hasResult = true
                ),
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1690050600000L,
                    hasResult = false
                ),
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1690050600000L,
                    hasResult = false
                ),
                TestAttemptDetails(
                    icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    title = "Current Affairs Practice Test",
                    totalMarks = 50,
                    obtainedMarks = 25,
                    date = 1690050600000L,
                    hasResult = false
                )
            )
        )

    suspend fun getSavedCourses(): Response<List<SavedTestItemModel>> =
        Response.success(
            listOf(
                SavedTestItemModel(
                    title = "SBI Clerk",
                    subtitle = "Sectional Tests | English, Hindi",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    totalTests = 170,
                    originalPrice = 320f,
                    discountedPrice = 300f
                ),
                SavedTestItemModel(
                    title = "SBI Clerk",
                    subtitle = "Sectional Tests | English, Hindi",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    totalTests = 170,
                    originalPrice = 320f,
                    discountedPrice = 300f
                ),
                SavedTestItemModel(
                    title = "SBI Clerk",
                    subtitle = "Sectional Tests | English, Hindi",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    totalTests = 170,
                    originalPrice = 320f,
                    discountedPrice = 300f
                ),
                SavedTestItemModel(
                    title = "SBI Clerk",
                    subtitle = "Sectional Tests | English, Hindi",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    totalTests = 170,
                    originalPrice = 320f,
                    discountedPrice = 300f
                ),
                SavedTestItemModel(
                    title = "SBI Clerk",
                    subtitle = "Sectional Tests | English, Hindi",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    totalTests = 170,
                    originalPrice = 320f,
                    discountedPrice = 300f
                ),
                SavedTestItemModel(
                    title = "SBI Clerk",
                    subtitle = "Sectional Tests | English, Hindi",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    totalTests = 170,
                    originalPrice = 320f,
                    discountedPrice = 300f
                ),
                SavedTestItemModel(
                    title = "SBI Clerk",
                    subtitle = "Sectional Tests | English, Hindi",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    totalTests = 170,
                    originalPrice = 320f,
                    discountedPrice = 300f
                ),
                SavedTestItemModel(
                    title = "SBI Clerk",
                    subtitle = "Sectional Tests | English, Hindi",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                    totalTests = 170,
                    originalPrice = 320f,
                    discountedPrice = 300f
                )
            )
        )

    suspend fun addTestToCart(testId: String): Response<TestCardItem> =
        Response.success(
            TestCardItem(
                id = testId,
                icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
                title = "SBI CLERK",
                subtitle = "Sectional Tests",
                languages = listOf("English", "Hindi"),
                totalTests = 170,
                freeTests = 3,
                price = 3000.0f
            )
        )
}