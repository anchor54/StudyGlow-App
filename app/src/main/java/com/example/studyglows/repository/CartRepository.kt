package com.example.studyglows.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.studyglows.db.StudyGlowsDatabase
import com.example.studyglows.db.models.Cart
import com.example.studyglows.db.models.CourseWithResourceModel
import com.example.studyglows.db.models.SavedItem
import com.example.studyglows.network.models.Cart as CartResponse
import com.example.studyglows.network.apis.CartApiService
import com.example.studyglows.network.models.CartPostRequestBody
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartApi: CartApiService,
    private val database: StudyGlowsDatabase
) {
    private val _cart = MutableLiveData<CartResponse>()
    val cart: LiveData<CartResponse>
        get() = _cart

    private val _savedCourses = MutableLiveData<List<CourseWithResourceModel>>()
    val savedCourses: LiveData<List<CourseWithResourceModel>>
        get() = _savedCourses

    private val _courseInCart = MutableLiveData<Boolean>()
    val courseInCart: LiveData<Boolean>
        get() = _courseInCart

    private val _courseSaved = MutableLiveData<Boolean>()
    val courseSaved: LiveData<Boolean>
        get() = _courseSaved

    suspend fun addCourseToCart(courseId: Long) {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzA5MTgxODY4LCJpYXQiOjE3MDkwOTU0NjgsImp0aSI6IjdhM2YxOTIzMzRhMjQxZmU5YWJlMDBmMjNkMGQwMTllIiwidXNlcl9pZCI6MjV9.lTAxCkc6rndh3V_qo7wAT6RtT-JIvcJFFpfgayGngDg"
        coroutineScope {
            cartApi.addCoursetoCart(CartPostRequestBody("COURSE", courseId), "Bearer $token")
            database.cartDao().addToCart(Cart(courseId, "COURSE"))
        }
    }

    suspend fun saveCourse(courseId: Long) {
        coroutineScope {
            database.cartDao().save(SavedItem(courseId, "COURSE"))
        }
    }

    suspend fun getCartItems() {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzA5MTgxODY4LCJpYXQiOjE3MDkwOTU0NjgsImp0aSI6IjdhM2YxOTIzMzRhMjQxZmU5YWJlMDBmMjNkMGQwMTllIiwidXNlcl9pZCI6MjV9.lTAxCkc6rndh3V_qo7wAT6RtT-JIvcJFFpfgayGngDg"
        val response = cartApi.getCart("Bearer $token")
        if (response.isSuccessful) {
            response.body()?.let {
                _cart.postValue(it)
                if (!it.is_checkout)
                    it.product_quantity?.map { cartItem ->
                        cartItem.course?.let { course ->
                            coroutineScope {
                                database.cartDao().addToCart(
                                    Cart(course.id, cartItem.type ?: "")
                                )
                            }
                        }
                    }
            }
        }
    }

    suspend fun getSavedCourses() {
        val courses = database.cartDao().getSavedCourses()
        _savedCourses.postValue(courses)
    }

    suspend fun removeSavedItem(itemId: String, type: String) {
        database.cartDao().deleteSavedItem(itemId.toLong(), type)
        if (type == "COURSE") {
            _savedCourses.postValue(
                _savedCourses.value?.filter { it.courseId != itemId }
            )
        }
    }

    suspend fun removeCartItem(itemId: String, type: String) {
        database.cartDao().deleteCartItem(itemId.toLong(), type)
        if (type == "COURSE") {
            val newCart = _cart.value?.let {
                CartResponse(
                    id = it.id,
                    is_checkout = it.is_checkout,
                    product_quantity = it.product_quantity?.filter { it.course?.id.toString() != itemId }
                )
            }
            _cart.postValue(newCart)
        }
    }

    suspend fun getCartItem(courseId: String, type: String) {
        val data = database.cartDao().getCartItem(courseId.toLong(), type)
        _courseInCart.postValue(data != null)
    }

    suspend fun getSavedItem(courseId: String, type: String) {
        val data = database.cartDao().getSavedItem(courseId.toLong(), type)
        _courseSaved.postValue(data != null)
    }
}