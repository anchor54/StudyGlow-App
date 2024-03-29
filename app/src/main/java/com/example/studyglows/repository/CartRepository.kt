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
import javax.inject.Singleton

@Singleton
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

    suspend fun addCourseToCart(courseId: Long, token: String) {
        coroutineScope {
            cartApi.addCoursetoCart(CartPostRequestBody("COURSE", courseId), "Bearer $token")
            database.cartDao().addToCart(Cart(courseId, "COURSE"))
            getCartItems(token)
        }
    }

    suspend fun saveCourse(courseId: Long) {
        database.cartDao().save(SavedItem(courseId, "COURSE"))
        getSavedCourses()
    }

    suspend fun getCartItems(token: String) {
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
        if (type == "COURSE" && _savedCourses.value != null) {
            _savedCourses.postValue(
                _savedCourses.value!!.filter { it.courseId != itemId }
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
}