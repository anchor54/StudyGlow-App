package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.cart.models.CartItemModel
import com.example.studyglows.screens.testseries.model.SavedTestItemModel
import com.example.studyglows.shared.components.CartItem
import com.example.studyglows.utils.UIUtils.endBorder

@Composable
fun SavedTestItem(
    modifier: Modifier = Modifier,
    savedTest: SavedTestItemModel,
    showDetailsClicked: () -> Unit,
    addToCartClicked: () -> Unit
) {
    Column(modifier = modifier) {
        CartItem(
            cartItem = CartItemModel(
                id = savedTest.id,
                title = "${savedTest.title} | ${savedTest.totalTests} Total Tests",
                subtitle = savedTest.subtitle,
                imageUrl = savedTest.imageUrl,
                originalPrice = savedTest.originalPrice,
                discountedPrice = savedTest.discountedPrice
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = showDetailsClicked,
                modifier = Modifier
                    .weight(1f)
                    .endBorder(
                        strokeWidth = 1.dp,
                        color = Color(0xFFB1D4EA)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "VIEW DETAILS",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    ),
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = addToCartClicked,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "ADD TO CART",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    ),
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}