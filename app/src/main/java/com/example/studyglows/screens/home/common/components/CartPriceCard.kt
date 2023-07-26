package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.utils.Utils

@Composable
fun CartPriceCard(
    discountedTotal: Float = 0f,
    originalTotal: Float = 0f,
    discount: Float = 0f,
    tax: Float = 0f,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(modifier = modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "TOTAL",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        color = Color(0xFF8798AD),
                        letterSpacing = 0.25.sp
                    )
                )
                Text(
                    text = Utils.amountWithRupeeSymbol(discountedTotal),
                    style = TextStyle(
                        fontSize = 25.sp,
                        lineHeight = 30.sp,
                        color = Color(0xFF2E384D),
                    )
                )
            }
            Spacer(modifier = Modifier.height(13.dp))
            CartPriceRow(
                modifier = Modifier.fillMaxWidth(),
                key = "Subtotal",
                value = Utils.amountWithRupeeSymbol(originalTotal)
            )
            CartPriceRow(
                modifier = Modifier.fillMaxWidth(),
                key = "Discount",
                value = Utils.amountWithRupeeSymbol(discount)
            )
            CartPriceRow(
                modifier = Modifier.fillMaxWidth(),
                key = "Tax",
                value = Utils.amountWithRupeeSymbol(tax)
            )
        }
    }
}

@Composable
fun CartPriceRow(
    modifier: Modifier = Modifier,
    key: String = "",
    value: String = ""
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = key,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,
                color = Color(0xFF8798AD),
                letterSpacing = 0.1.sp,
            )
        )
        Text(
            text = value,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.1.sp,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCartPrice() {
    CartPriceCard(
        discountedTotal = 640f,
        originalTotal = 320f,
    )
}