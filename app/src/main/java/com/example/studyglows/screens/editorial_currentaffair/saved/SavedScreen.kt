package com.example.studyglows.screens.editorial_currentaffair.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.editorial_currentaffair.editorial.component.EditorialListItem
import com.example.studyglows.screens.editorial_currentaffair.vacancies.components.VacancyListItem
import com.example.studyglows.shared.components.HomeAppBar
import com.example.studyglows.shared.components.TabLayout
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: SavedViewModel
) {
    val savedJobs by viewModel.savedJobs.collectAsState()
    val savedEditorials by viewModel.savedEditorials.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getSavedEditorials()
        viewModel.getSavedJobs()
    }
    LaunchedEffect(key1 = loading) {
        sharedViewModel.isLoading(loading)
    }
    LaunchedEffect(key1 = error) {
        sharedViewModel.showError(error)
    }

    Column(modifier = modifier) {
        HomeAppBar(onNavIconClicked = { sharedViewModel.sendUIEvent(AppUIEvent.ShowDrawer) }) {}
        TabLayout(
            modifier = Modifier.fillMaxWidth(),
            tabNames = listOf(
                "Editorials",
                "Jobs"
            )
        ) {
            LazyColumn {
                if (it == 0) {
                    items(savedEditorials.size) {
                        EditorialListItem(item = savedEditorials[it])
                    }
                } else if (it == 1) {
                    items(savedJobs.size) {
                        VacancyListItem(itemDetails = savedJobs[it])
                    }
                }
            }
        }
    }
}