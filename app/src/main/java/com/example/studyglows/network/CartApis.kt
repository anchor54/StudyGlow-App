package com.example.studyglows.network

import com.example.studyglows.screens.cart.models.CartItem
import retrofit2.Response

class CartApis {
    suspend fun getCartItems(): Response<List<CartItem>> =
        Response.success(
            listOf(
                CartItem(
                    courseName = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 1000f,
                    discountedPrice = 400f
                ),
                CartItem(
                    courseName = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 320f,
                    discountedPrice = 320f
                ),
                CartItem(
                    courseName = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 500f,
                    discountedPrice = 400f
                )
            )
        )

    suspend fun getSavedItems(): Response<List<CartItem>> =
        Response.success(
            listOf(
                CartItem(
                    courseName = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 1000f,
                    discountedPrice = 400f
                ),
                CartItem(
                    courseName = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 320f,
                    discountedPrice = 320f
                ),
                CartItem(
                    courseName = "UPSC IAS Live Foundation",
                    imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
                    originalPrice = 500f,
                    discountedPrice = 400f
                )
            )
        )
}