package com.example.studyglows.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileSection(
    imageUrl: String = "",
    userName: String = "",
    phoneNumber: String = "",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = { imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit
            ),
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(100.dp))
        )
        Spacer(modifier = Modifier.width(13.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = userName,
                style = TextStyle(
                    fontSize = 21.sp,
                    lineHeight = 25.2.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.15.sp,
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = phoneNumber,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.15.sp,
                )
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.icon_edit),
            contentDescription = "Edit Profile"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileSection() {
    ProfileSection(
        imageUrl = "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        userName = "Ankur Mazumder",
        phoneNumber = "+91-8637053974"
    )
}