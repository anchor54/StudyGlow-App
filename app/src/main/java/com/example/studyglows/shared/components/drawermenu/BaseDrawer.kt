package com.example.studyglows.shared.components.drawermenu

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun BaseDrawer(
    drawerInteractions: (String) -> Unit,
    drawerMidOptions: List<MenuItemModel>,
    drawerState: DrawerState,
    selectedOption: String = "",
    content: @Composable () -> Unit,
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.7f),
                drawerShape = RoundedCornerShape(0.dp),
                drawerContainerColor = Color.White
            ) {
                Column(modifier = Modifier
                    .scrollable(
                        rememberScrollState(),
                        orientation = Orientation.Vertical
                    )
                ) {
                    NavDrawerHeader("Suvineet")
                    NavDrawerContent(
                        midMenuItems = drawerMidOptions,
                        selectedItem = selectedOption,
                        onItemClicked = drawerInteractions
                    )
                }
            }
        },
        content = content
    )
}