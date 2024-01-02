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
                    imageUrl = "https://www.ncertbooks.guru/wp-content/uploads/2022/05/Course-details.png",
                    originalPrice = 1000f,
                    discountedPrice = 400f
                ),
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://www.ncertbooks.guru/wp-content/uploads/2022/05/Course-details.png",
                    originalPrice = 320f,
                    discountedPrice = 320f
                ),
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://www.ncertbooks.guru/wp-content/uploads/2022/05/Course-details.png",
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
                    imageUrl = "https://thevarsity.ca/wp-content/uploads/2018/07/Comment_Course-Selection_Troy-Lawrence-scaled.jpg",
                    originalPrice = 1000f,
                    discountedPrice = 400f
                ),
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://thevarsity.ca/wp-content/uploads/2018/07/Comment_Course-Selection_Troy-Lawrence-scaled.jpg",
                    originalPrice = 320f,
                    discountedPrice = 320f
                ),
                CartItemModel(
                    title = "UPSC IAS Live Foundation",
                    imageUrl = "https://thevarsity.ca/wp-content/uploads/2018/07/Comment_Course-Selection_Troy-Lawrence-scaled.jpg",
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