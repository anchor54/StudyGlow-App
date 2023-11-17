package com.example.studyglows.screens.setting

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.UIUtils.topBorder

class SettingEntity(
    val title: String,
    val route: String
)

class SettingGroupEntity(
    val title: String,
    val settings: List<SettingEntity>
)

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: SettingsViewModel,
    sharedViewModel: SharedViewModel
) {
    val settingGroups = listOf(
        SettingGroupEntity(
            title = "Account",
            settings = listOf(
                SettingEntity(
                    title = "Profile Settings",
                    route = ""
                )
            )
        ),
        SettingGroupEntity(
            title = "Help & Support",
            settings = listOf(
                SettingEntity(
                    title = "About Us",
                    route = ""
                ),
                SettingEntity(
                    title = "Terms & Conditions",
                    route = ""
                ),
                SettingEntity(
                    title = "Private Policy",
                    route = ""
                )
            )
        )
    )
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDF5FA))
                .padding(16.dp, 8.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                contentDescription = "back"
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical
            )
            .padding(16.dp)
        ) {
            Text(
                text = "Settings",
                style = TextStyle(
                    fontSize = 21.sp,
                    lineHeight = 25.2.sp,
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.15.sp,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                thickness = 1.dp,
                color = Color(0xFFB1D4EA),
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(34.dp)
            ) {
                settingGroups.map {
                    SettingsGroup(
                        settingGroup = it,
                        onSettingClicked = {
                            navHostController.navigate(it)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Text(
                text = "SIGN OUT",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF025284),
                    letterSpacing = 1.25.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .topBorder(strokeWidth = 1.dp, color = Color(0xFFB1D4EA))
                    .bottomBorder(strokeWidth = 1.dp, color = Color(0xFFB1D4EA))
                    .clickable {  }
                    .padding(0.dp, 8.dp)
            )
        }
    }
}

@Composable
fun SettingsGroup(
    modifier: Modifier = Modifier,
    settingGroup: SettingGroupEntity,
    onSettingClicked: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = settingGroup.title,
            style = TextStyle(
                fontSize = 13.sp,
                color = Color(0xFF8798AD),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .bottomBorder(1.dp, Color(0xFFB1D4EA))
                .padding(0.dp, 8.dp)
        )
        settingGroup.settings.map { setting ->
            SettingItem(
                setting = setting,
                modifier = Modifier.fillMaxWidth()
            ) { onSettingClicked(setting.route) }
        }
    }
}

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    setting: SettingEntity,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onClick }
            .bottomBorder(
                strokeWidth = 1.dp,
                color = Color(0xFFB1D4EA)
            )
            .padding(0.dp, 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = setting.title,
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.arrow_right),
            contentDescription = "navigate"
        )
    }
}