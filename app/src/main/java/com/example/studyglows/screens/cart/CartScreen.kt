package com.example.studyglows.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.cart.models.CartItemModel
import com.example.studyglows.screens.cart.component.CartPriceCard
import com.example.studyglows.shared.components.CartItem
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun CartScreen(
    navHostController: NavHostController,
    viewModel: CartViewModel,
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val savedItems by viewModel.savedItems.collectAsState()
    val loadingState by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getCartItems()
        viewModel.getSavedItems()
    }

    LaunchedEffect(key1 = loadingState) {
        sharedViewModel.isLoading(loadingState)
    }

    LaunchedEffect(key1 = error) {
        sharedViewModel.showError(error)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFE6F1F8),
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF025284)
                ),
                onClick = { navHostController.navigate(Screen.Checkout.route) }
            ) {
                Text(
                    text = "CONFIRM",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val originalTotalPrice = cartItems?.fold(0f) { acc, cartItem -> acc + (cartItem?.originalPrice ?: 0f) } ?: 0f
            val discountedTotalPrice = cartItems?.fold(0f) { acc, cartItem -> acc + (cartItem?.discountedPrice ?: 0f) } ?: 0f
            val totalDiscount = originalTotalPrice - discountedTotalPrice

            CartItemList(
                title = "CART",
                items = cartItems ?: listOf(),
                shouldCollapse = true,
                onCollapse = { navHostController.popBackStack() }
            ) { cart ->
                cart?.let { cartItem ->
                    RowItem(
                        item = cartItem,
                        rowIcon = R.drawable.remove,
                        onRowIconClicked = { viewModel.removeCartItem(cartItem.id) }
                    )
                }
            }

            CartPriceCard(
                discountedTotal = discountedTotalPrice,
                originalTotal = originalTotalPrice,
                discount = totalDiscount,
                modifier = Modifier.padding(17.dp, 12.dp, 17.dp, 8.dp)
            )

            Divider(
                color = Color(0xFFB1D4EA),
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(40.dp))

            CartItemList(
                title= "SAVED",
                items = savedItems,
                shouldCollapse = false,
            ) { cart ->
                RowItem(
                    item = cart,
                    rowIcon = R.drawable.add,
                    onRowIconClicked = {
                        viewModel.addCartItem(cart.id)
                        viewModel.removeSavedItem(cart.id)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun<T> CartItemList(
    title: String,
    items: List<T>,
    shouldCollapse: Boolean = true,
    onCollapse: (() -> Unit)? = null,
    content: @Composable (item: T) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (shouldCollapse)
            IconButton(onClick = { onCollapse?.invoke() }) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.expand_more),
                    contentDescription = "Expand Cart Items",
                )
            }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF8798AD),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "${items.size} ITEM",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
    }

    items.map { content(it) }
}

@Composable
fun RowItem(
    modifier: Modifier = Modifier,
    item: CartItemModel,
    rowIcon: Int,
    onRowIconClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        Image(
            imageVector = ImageVector.vectorResource(id = rowIcon),
            contentDescription = "Remove item from cart",
            modifier = Modifier.clickable{ onRowIconClicked() }
        )
        Spacer(modifier = Modifier.width(4.dp))
        CartItem(
            cartItem = item
        )
        Spacer(modifier = Modifier.width(20.dp))
    }
}