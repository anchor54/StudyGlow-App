package com.example.studyglows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.navigation.NavGraph
import com.example.studyglows.screens.auth.AuthViewModel
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.shared.components.drawermenu.BaseDrawer
import com.example.studyglows.shared.components.drawermenu.NavDrawerContent
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.ui.theme.StudyGlowsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var sharedVMFactory: SharedViewModel.SharedVMAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyGlowsTheme {
                val navController = rememberNavController()
                val sharedViewModel: SharedViewModel by viewModels {
                    SharedViewModel.providesSharedViewModelFactory(
                        factory = sharedVMFactory,
                        navHostController = navController
                    )
                }
                Root(viewModel = sharedViewModel, navHostController = navController)
            }
        }
    }
}

@Composable
fun Root(
    viewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val drawerNavigation by viewModel.drawerNavigation.collectAsState()
    val drawerMidOptions by viewModel.drawerMidOptions.collectAsState()
    val selectedDrawerOption by viewModel.selectedDrawerOption.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect {
            when(it) {
                is AppUIEvent.ShowDrawer -> drawerState.open()
                is AppUIEvent.HideDrawer -> drawerState.close()
                else -> {}
            }
        }
    }

    BaseDrawer(
        drawerInteractions = drawerNavigation,
        drawerMidOptions = drawerMidOptions,
        drawerState = drawerState,
        selectedOption = selectedDrawerOption
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            NavGraph(navHostController = navHostController, appVM = viewModel)
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize().background(Color(0x80000000))) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}