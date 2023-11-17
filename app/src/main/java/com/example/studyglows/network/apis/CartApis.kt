package com.example.studyglows.network.apis

import com.example.studyglows.screens.cart.models.CartItemModel
import com.example.studyglows.screens.home.common.models.Course
import retrofit2.Response
import javax.inject.Inject

class CartApis @Inject constructor() {
    suspend fun getCartItems(): Response<List<CartItemModel>> =
        Response.success(
            listOf(
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 1000f,
                    discountedPrice = 400f
                ),
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 320f,
                    discountedPrice = 320f
                ),
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 500f,
                    discountedPrice = 400f
                )
            )
        )

    suspend fun getSavedCourses(): Response<List<CartItemModel>> =
        Response.success(
            listOf(
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 1000f,
                    discountedPrice = 400f
                ),
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 320f,
                    discountedPrice = 320f
                ),
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 500f,
                    discountedPrice = 400f
                )
            )
        )

    suspend fun addCourseToCart(courseId: String): Response<Course> =
        Response.success(
            Course()
        )
}