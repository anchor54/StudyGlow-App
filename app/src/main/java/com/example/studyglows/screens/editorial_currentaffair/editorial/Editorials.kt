package com.example.studyglows.screens.editorial_currentaffair.editorial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.editorial_currentaffair.component.editorialNavDrawerContent
import com.example.studyglows.screens.editorial_currentaffair.editorial.component.EditorialContent
import com.example.studyglows.screens.editorial_currentaffair.editorial.component.EditorialListItem
import com.example.studyglows.screens.editorial_currentaffair.editorial.component.TopEditorialItem
import com.example.studyglows.shared.components.HomeAppBar
import com.example.studyglows.shared.components.drawermenu.BaseDrawerNavigation
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun Editorials(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedVM: SharedViewModel,
    viewModel: EditorialViewModel
) {
    val editorialList by viewModel.editorials.collectAsState()
    val topElement by remember(editorialList) {
        derivedStateOf {
            if (editorialList.isNotEmpty()) {
                val item = editorialList[0]
                editorialList.drop(1)
                item
            } else null
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getEditorialList()
        sharedVM.setDrawerMidOptions(
            options = editorialNavDrawerContent(),
            handler = object : BaseDrawerNavigation(navHostController) {
                override fun handleDrawerNavigation(itemId: String) {
                    when (itemId) {
                        "editorials" -> { navHostController.navigate(Route.EDITORIAL_ROUTE.name) }
                        "current_affairs" -> { navHostController.navigate(Route.CURRENT_AFFAIRS_ROUTE.name) }
                        "latest_vacancies" -> { navHostController.navigate(Screen.Vacancies.route) }
                        "saved_editorials" -> { navHostController.navigate(Screen.SavedVacancies.route) }
                        else -> super.handleDrawerNavigation(itemId)
                    }
                }
            }
        )
    }

    fun navigateToDetails(id: String) {
        navHostController.navigate("${Screen.EditorialDetails.route}/$id")
    }

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        HomeAppBar(onNavIconClicked = { sharedVM.sendUIEvent(AppUIEvent.ShowDrawer()) }) {}
        EditorialContent(
            modifier = Modifier.fillMaxWidth(),
            headerItem = topElement,
            onHeaderClicked = { navigateToDetails(topElement?.id ?: "") }
        ) {
            editorialList.map {
                EditorialListItem(
                    item = it,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { navigateToDetails(it.id) }
                )
            }
        }
    }
}