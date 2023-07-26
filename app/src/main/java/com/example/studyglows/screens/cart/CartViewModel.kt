package com.example.studyglows.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.CartApis
import com.example.studyglows.screens.cart.models.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel: ViewModel() {
    private val cartRepository = CartApis()

    private val _cartItems = MutableStateFlow(listOf<CartItem>())
    val cartItems = _cartItems.asStateFlow()

    private val _savedItems = MutableStateFlow(listOf<CartItem>())
    val savedItems = _savedItems.asStateFlow()

    fun getCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository.getCartItems()
            if (response.isSuccessful) {
                response.body()?.let {
                    _cartItems.value = it
                }
            }
        }
    }

    fun addCartItem(item: CartItem) {
        _cartItems.value += item
    }

    fun removeCartItem(item: CartItem) {
        _cartItems.value -= item
    }

    fun getSavedItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository.getSavedItems()
            if (response.isSuccessful) {
                response.body()?.let {
                    _savedItems.value = it
                }
            }
        }
    }

    fun addSavedItem(item: CartItem) {
        _savedItems.value += item
    }

    fun removeSavedItem(item: CartItem) {
        _savedItems.value -= item
    }
}