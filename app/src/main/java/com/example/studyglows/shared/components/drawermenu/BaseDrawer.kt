package com.example.studyglows.shared.components.drawermenu

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun BaseDrawer(
    drawerInteractions: IDrawerNavigation,
    drawerMidOptions: List<MenuItemModel>,
    drawerState: DrawerState? = null,
    selectedOption: String = "",
    content: @Composable () -> Unit,
) {
    val drawerState = drawerState ?: rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = Color.White) {
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
                        onItemClicked = {
                            drawerInteractions.handleDrawerNavigation(it)
                            coroutineScope.launch { drawerState.close() }
                        }
                    )
                }
            }
        },
        content = content
    )
}