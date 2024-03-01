package com.example.studyglows.network.apis

import com.example.studyglows.network.models.Cart
import com.example.studyglows.network.models.CartPostRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CartApiService {
    @GET("/cart")
    suspend fun getCart(@Header("Authorization") auth: String): Response<Cart>

    @POST("/cart")
    suspend fun addCoursetoCart(@Body body: CartPostRequestBody, @Header("Authorization") auth: String)
}