package com.example.studyglows.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.CartApis
import com.example.studyglows.screens.cart.models.CartItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartApis
): ViewModel() {
    private val _cartItemsModule = MutableStateFlow(listOf<CartItemModel>())
    val cartItems = _cartItemsModule.asStateFlow()

    private val _savedItems = MutableStateFlow(listOf<CartItemModel>())
    val savedItems = _savedItems.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    fun getCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository.getCartItems()
            if (response.isSuccessful) {
                response.body()?.let {
                    _cartItemsModule.value = it
                }
            }
        }
    }

    fun addCartItem(item: CartItemModel) {
        _cartItemsModule.value += item
    }

    fun removeCartItem(item: CartItemModel) {
        _cartItemsModule.value -= item
    }

    fun getSavedItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository.getSavedCourses()
            if (response.isSuccessful) {
                response.body()?.let {
                    _savedItems.value = it
                }
            }
        }
    }

    fun addSavedItem(item: CartItemModel) {
        _savedItems.value += item
    }

    fun removeSavedItem(item: CartItemModel) {
        _savedItems.value -= item
    }
}