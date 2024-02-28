package com.example.studyglows

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.navigation.DeepLink
import com.example.studyglows.navigation.NavGraph
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.AuthViewModel
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.shared.components.drawermenu.BaseDrawer
import com.example.studyglows.shared.components.drawermenu.NavDrawerContent
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.ui.theme.StudyGlowsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var sharedVMFactory: SharedViewModel.SharedVMAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext.deleteDatabase("StudyGlowsDB")
        setContent {
            Log.d("onCreate:", intent?.data?.toString() ?: "null")
            StudyGlowsTheme(darkTheme = false) {
                val navController = rememberNavController()
                val sharedViewModel: SharedViewModel by viewModels {
                    SharedViewModel.providesSharedViewModelFactory(
                        factory = sharedVMFactory,
                        navHostController = navController
                    )
                }
                Root(viewModel = sharedViewModel, navHostController = navController)
//                intent?.data?.let { navController.navigate(it) }
//                navController.navigate("study.glows://test/123")
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
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    suspend fun showSnackbar(message: String) {
        snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short,
        )
    }

    fun hideSnackbar() {
        snackbarHostState.currentSnackbarData?.dismiss()
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect {
            when(it) {
                is AppUIEvent.ShowDrawer -> drawerState.open()
                is AppUIEvent.HideDrawer -> drawerState.close()
                is AppUIEvent.ShowError -> showSnackbar(it.error)
                is AppUIEvent.HideError -> hideSnackbar()
            }
        }
    }

    BaseDrawer(
        drawerInteractions = {
            drawerNavigation.handleDrawerNavigation(it)
            coroutineScope.launch { drawerState.close() }
            viewModel.setSelectedDrawerOption(it)
        },
        drawerMidOptions = drawerMidOptions,
        drawerState = drawerState,
        selectedOption = selectedDrawerOption
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { padding ->
            Box(modifier = Modifier
                .padding(padding)
                .fillMaxSize()) {
                NavGraph(navHostController = navHostController, appVM = viewModel)
                if (isLoading) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x80000000))) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}