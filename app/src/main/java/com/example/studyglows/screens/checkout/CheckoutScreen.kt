package com.example.studyglows.screens.checkout

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
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
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun CheckoutScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier
) {
    var coupon by remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFE6F1F8),
        topBar = { CheckoutTitleBar { navHostController.popBackStack() } },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(37.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF025284)
                ),
                onClick = { navHostController.popBackStack() }
            ) {
                Text(
                    text = "COMPLETE PAYMENT",
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
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            CouponInput(coupon = coupon, onCouponChange = { coupon = it })
            Spacer(modifier = Modifier.height(20.dp))
            RecommendedPaymentOptions()
            Spacer(modifier = Modifier.height(32.dp))
            AddCardOptions()
            Spacer(modifier = Modifier.height(32.dp))
            UPIPaymentOptions()
        }
    }
}

@Composable
fun CheckoutTitleBar(
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.expand_more),
                contentDescription = "Go Back",
                modifier = Modifier.rotate(90f)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "CHECKOUT",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF8798AD),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "1 ITEM",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
    }
}

@Composable
fun CouponInput(
    coupon: String,
    onCouponChange: (String) -> Unit
) {
    Text(
        text = "APPLY COUPON",
        style = TextStyle(
            fontSize = 17.sp,
            lineHeight = 20.4.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF8798AD),
            letterSpacing = 0.15.sp,
        )
    )
    Spacer(modifier = Modifier.height(12.dp))
    Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFB1D4EA))
    Spacer(modifier = Modifier.height(2.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_coupon),
                    tint = Color(0xFF4A6572),
                    contentDescription = "Coupon Icon"
                )
                TextField(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth(0.75f),
                    value = coupon,
                    onValueChange = onCouponChange,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = "Enter Your Coupon",
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 15.6.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF8798AD),
                                letterSpacing = 0.4.sp,
                            )
                        )
                    }
                )
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "APPLY",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
        }
    }
}

@Composable
fun RecommendedPaymentOptions() {
    Text(
        text = "RECOMMENDED",
        style = TextStyle(
            fontSize = 17.sp,
            lineHeight = 20.4.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF8798AD),
            letterSpacing = 0.15.sp,
        )
    )
    Spacer(modifier = Modifier.height(12.dp))
    Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFB1D4EA))
    Spacer(modifier = Modifier.height(2.dp))
    Column {
        PaymentOption(
            selected = false,
            icon = ImageVector.vectorResource(id = R.drawable.visa),
            optionText = "Personal- 459200XXXXXX3744",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(4.dp))
        PaymentOption(
            selected = false,
            icon = ImageVector.vectorResource(id = R.drawable.visa),
            optionText = "GOOGLE PAY",
            onClick = {}
        )
    }
}

@Composable
fun AddCardOptions() {
    Text(
        text = "CARD",
        style = TextStyle(
            fontSize = 17.sp,
            lineHeight = 20.4.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF8798AD),
            letterSpacing = 0.15.sp,
        )
    )
    Spacer(modifier = Modifier.height(12.dp))
    Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFB1D4EA))
    Spacer(modifier = Modifier.height(2.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.add_card), contentDescription = "Visa")
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Add Credit, Debit & ATM Cards",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF232F34),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
        }
    }
}

@Composable
fun UPIPaymentOptions() {
    Text(
        text = "UPI",
        style = TextStyle(
            fontSize = 17.sp,
            lineHeight = 20.4.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF8798AD),
            letterSpacing = 0.15.sp,
        )
    )
    Spacer(modifier = Modifier.height(12.dp))
    Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFB1D4EA))
    Spacer(modifier = Modifier.height(2.dp))
    Column {
        PaymentOption(
            selected = false,
            icon = ImageVector.vectorResource(id = R.drawable.visa),
            optionText = "Paytm UPI",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(4.dp))
        PaymentOption(
            selected = false,
            icon = ImageVector.vectorResource(id = R.drawable.visa),
            optionText = "GOOGLE PAY",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(4.dp))
        PaymentOption(
            selected = false,
            icon = ImageVector.vectorResource(id = R.drawable.visa),
            optionText = "PHONEPE",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(4.dp))
        PaymentOption(
            selected = false,
            icon = ImageVector.vectorResource(id = R.drawable.visa),
            optionText = "Personal- 459200XXXXXX3744",
            onClick = {}
        )
    }
}

@Composable
fun PaymentOption(
    selected: Boolean,
    icon: ImageVector,
    optionText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = icon, contentDescription = "Visa")
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = optionText,
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF232F34),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
            RadioButton(selected = selected, onClick = onClick)
        }
    }
}