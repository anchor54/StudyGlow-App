package com.example.studyglows.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.cart.models.CartItemModel
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.UIUtils.topBorder
import com.example.studyglows.utils.Utils
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CartItem(
    cartItem: CartItemModel,
    modifier: Modifier = Modifier
) {
    val discountPercent =
        if (cartItem.originalPrice == 0f) 0f
        else (cartItem.originalPrice - cartItem.discountedPrice) / cartItem.originalPrice

    Row(modifier = modifier
        .background(color = Color(0x66FFFFFF))
        .topBorder(strokeWidth = 1.dp, color = Color(0xFFB1D4EA), horizontalOffset = 10.dp)
        .bottomBorder(strokeWidth = 1.dp, color = Color(0xFFB1D4EA), horizontalOffset = 10.dp)
        .padding(15.dp)
    ) {
        GlideImage(
            imageModel = { cartItem.imageUrl },
            modifier = Modifier.width(82.dp).height(52.dp),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            if (discountPercent > 0) {
                val coursePriceText = Utils.getDiscountedPriceText(
                    originalPrice = Utils.amountWithRupeeSymbol(cartItem.originalPrice),
                    discountedPrice = Utils.amountWithRupeeSymbol(cartItem.discountedPrice)
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "$discountPercent% OFF",
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 22.5.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF025284),
                            letterSpacing = 0.25.sp,
                        )
                    )
                    Text(text = coursePriceText)
                }
            } else {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "₹${cartItem.originalPrice}")
                }
            }
            Text(
                text = cartItem.title,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 25.5.sp,
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.5.sp,
                )
            )
            cartItem.subtitle?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF8798AD),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewCartItem() {
    CartItem(
        cartItem = CartItemModel(
            imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20220805/ir-thumblin_RYUQVut.webp",
            title = "UPSC IAS Live Foundation",
            subtitle = "Sectional Tests | English, Hindi",
            originalPrice = 320f,
            discountedPrice = 300f,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}