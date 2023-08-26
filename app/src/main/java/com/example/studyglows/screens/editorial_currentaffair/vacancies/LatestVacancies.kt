package com.example.studyglows.screens.editorial_currentaffair.vacancies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.editorial_currentaffair.vacancies.components.VacancyListItem
import com.example.studyglows.screens.profile.TabEntity
import com.example.studyglows.screens.profile.components.PurchasedItem
import com.example.studyglows.shared.components.HomeAppBar
import com.example.studyglows.shared.components.TabItem
import com.example.studyglows.shared.components.TabLayout
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun LatestVacancies(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: LatestVacancyViewModel,
    sharedViewModel: SharedViewModel
) {
    val jobs by viewModel.jobNotifications.collectAsState()
    val admitCards by viewModel.admitNotifications.collectAsState()
    val results by viewModel.resultNotifications.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchJobNotifications()
        viewModel.fetchAdmitCardNotifications()
        viewModel.fetchResultNotifications()
    }
    Column(modifier = modifier) {
        HomeAppBar(onNavIconClicked = { sharedViewModel.sendUIEvent(AppUIEvent.ShowDrawer()) }) {}
        TabLayout(
            modifier = Modifier.fillMaxWidth(),
            tabNames = listOf(
                jobs.title,
                admitCards.title,
                results.title,
            )
        ) {
            val listContent = when (it) {
                0 -> jobs.notifications
                1 -> admitCards.notifications
                2 -> results.notifications
                else -> listOf()
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listContent.size) {
                    VacancyListItem(
                        itemDetails = listContent[it],
                        modifier = Modifier.fillMaxWidth().padding(start = 18.dp)
                    )
                }
            }
        }
    }
}