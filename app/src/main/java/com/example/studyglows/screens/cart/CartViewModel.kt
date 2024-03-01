package com.example.studyglows.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.studyglows.repository.CartRepository
import com.example.studyglows.screens.cart.models.CartItemModel
import com.example.studyglows.utils.Utils.asStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
): ViewModel() {
    val cartItems = cartRepository.cart.map {
        it.product_quantity?.map { cartItem ->
            cartItem.course?.let { course ->
                CartItemModel(
                    id = course.id.toString(),
                    title = course.title ?: "",
                    subtitle = course.about ?: "",
                    imageUrl = course.resource?.url ?: "",
                    discountedPrice = course.price?.toFloat() ?: 0f,
                    originalPrice = course.mrp?.toFloat() ?: 0f
                )
            }
        }
    }.asStateFlow(listOf())

    val savedItems = cartRepository.savedCourses.map {
        it.map { course ->
            CartItemModel(
                id = course.courseId,
                title = course.title ?: "",
                subtitle = course.brief ?: "",
                imageUrl = course.imageUrl ?: "",
                discountedPrice = course.discountedPrice ?: 0f,
                originalPrice = course.originalPrice ?: 0f
            )
        }
    }.asStateFlow(listOf())

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    fun getCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            cartRepository.getCartItems()
            _loading.value = false
        }
    }

    fun addCartItem(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            cartRepository.addCourseToCart(courseId.toLong())
            _loading.value = false
        }
    }

    fun removeCartItem(itemId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            cartRepository.removeCartItem(itemId, "COURSE")
            _loading.value = false
        }
    }

    fun getSavedItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            cartRepository.getSavedCourses()
            _loading.value = false
        }
    }

    fun addSavedCourseToCart(courseId: String) {
        removeSavedItem(courseId)
        addCartItem(courseId)
    }

    fun removeSavedItem(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.removeSavedItem(courseId, "COURSE")
        }
    }
}