package com.example.studyglows.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.home.common.models.PurchasedItem
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.UIUtils.endBorder
import com.example.studyglows.utils.UIUtils.topBorder
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PurchasedItem(
    modifier: Modifier = Modifier,
    item: PurchasedItem,
    invoiceClicked: () -> Unit,
    purchaseClicked: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp, 0.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .topBorder(
                strokeWidth = 1.dp,
                color = Color(0xFFB1D4EA)
            )
            .bottomBorder(
                strokeWidth = 1.dp,
                color = Color(0xFFB1D4EA)
            )
            .padding(10.dp, 10.dp)
        ) {
            GlideImage(
                imageModel = { item.imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                modifier = Modifier.weight(0.5f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 25.5.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.5.sp,
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = item.subtitle,
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF8798AD),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { invoiceClicked() },
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
                    text = "INVOICE",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
            Button(
                onClick = { purchaseClicked() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "Purchase Again",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.25.sp,
                    )
                )
            }
        }
    }
}