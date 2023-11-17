package com.example.studyglows.network.apis

import com.example.studyglows.network.models.PurchasedEntity
import com.example.studyglows.screens.auth.common.models.User
import retrofit2.Response
import java.util.UUID
import javax.inject.Inject

class UserApis @Inject constructor() {
    suspend fun getUserDetails(userId: String): Response<User> =
        Response.success(
            User(
                id = UUID.randomUUID().toString(),
                full_name = "Ankur Mazumder",
                email = "ankur@mail.com",
                phone = "+91-8637053974",
                profile_image = "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            )
        )

    suspend fun getPurchasedCourses(userId: String): Response<List<PurchasedEntity>> =
        Response.success(
            listOf(
                PurchasedEntity(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "UPSC IAS Live Foundation",
                    purchaseDate = 1658966400000,
                    validUntil = 1674864000000
                ),
                PurchasedEntity(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "UPSC IAS Live Foundation",
                    purchaseDate = 1674864000000,
                    validUntil = 1693180800000
                )
            )
        )

    suspend fun getPurchasedTestSeries(userId: String): Response<List<PurchasedEntity>> =
        Response.success(
            listOf(
                PurchasedEntity(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "UPSC IAS Live Foundation",
                    purchaseDate = 1658966400000,
                    validUntil = 1674864000000
                ),
                PurchasedEntity(
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    title = "UPSC IAS Live Foundation",
                    purchaseDate = 1674864000000,
                    validUntil = 1693180800000
                )
            )
        )
}