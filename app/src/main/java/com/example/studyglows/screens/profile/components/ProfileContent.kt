package com.example.studyglows.screens.profile.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studyglows.screens.auth.common.models.User
import com.example.studyglows.screens.home.common.models.PurchasedItem
import com.example.studyglows.screens.profile.TabEntity
import com.example.studyglows.shared.components.TabLayout
import com.example.studyglows.shared.components.TabItem

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    userDetails: User,
    purchasedCourses: List<PurchasedItem> = listOf(),
    purchasedTestSeries: List<PurchasedItem> = listOf()
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 33.dp,
            topEnd = 33.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        ProfileSection(
            imageUrl = userDetails.resource ?: "",
            userName = userDetails.first_name ?: "",
            phoneNumber = userDetails.phone ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp, 20.dp)
        )
        Spacer(modifier = Modifier.height(11.dp))
        Divider(modifier = Modifier.fillMaxWidth(), color = Color(0xFFB1D4EA), thickness = 1.dp)
        Spacer(modifier = Modifier.height(11.dp))
        TabLayout(
            tabNames = listOf(
                "Purchased Courses",
                "Purchased Test Series",
            ),
            modifier = Modifier.weight(1f),
            tabContent = {
                val listContent = when (it) {
                    0 -> purchasedCourses
                    1 -> purchasedTestSeries
                    else -> listOf()
                }
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(listContent.size) {
                        PurchasedItem(
                            item = listContent[it],
                            invoiceClicked = { /*TODO*/ }
                        ) {}
                    }
                }
            }
        )
    }
}