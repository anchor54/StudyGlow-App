//package com.example.studyglows.screens.testseries
//
//import android.util.Base64
//import androidx.activity.compose.BackHandler
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.example.studyglows.navigation.TestSeriesPathCreator
//import com.example.studyglows.navigation.Route
//import com.example.studyglows.screens.auth.common.models.TestSeriesUIEvent
//import com.example.studyglows.screens.testseries.model.testNavDrawerContent
//import com.example.studyglows.screens.testseries.subscreens.testscreens.TestFlowContainer
//import com.example.studyglows.screens.testseries.subscreens.testseriesscreens.TestSeriesFlowContainer
//import com.example.studyglows.shared.components.drawermenu.BaseDrawerNavigation
//import com.example.studyglows.shared.model.NavItem
//import com.example.studyglows.shared.viewmodels.SharedViewModel
//import com.google.gson.reflect.TypeToken
//
//@Composable
//fun BaseTestScreen(
//    modifier: Modifier = Modifier,
//    navHostController: NavHostController,
//    viewModel: TestSeriesViewModel,
//    sharedViewModel: SharedViewModel
//) {
//    val nestedNavHost = rememberNavController()
//    val loading by viewModel.loading.collectAsState()
//    val gson = sharedViewModel.gson
//
//    val screenParams = gson.fromJson<List<NavItem>>(
//        String(
//            Base64.decode(
//                navHostController.currentBackStackEntry?.arguments?.getString("screen_params") ?: "",
//                Base64.DEFAULT
//            ),
//            Charsets.UTF_8
//        ),
//        object : TypeToken<List<NavItem>>() {}.type
//    )?.toMutableList()
//
//    LaunchedEffect(key1 = loading) {
//        sharedViewModel.isLoading(loading)
//    }
//    LaunchedEffect(key1 = screenParams) {
//        if (!screenParams.isNullOrEmpty())  {
//            val firstScreenParams = screenParams.removeAt(0)
//            when (firstScreenParams.screen) {
//                Route.TEST_FLOW.name -> {
//                    val screenParams = String(
//                        Base64.encode(
//                            gson.toJson(screenParams).toByteArray(),
//                            Base64.DEFAULT
//                        ),
//                        Charsets.UTF_8
//                    ).trim()
//                    nestedNavHost
//                        .navigate("${Route.TEST_FLOW.name}/$screenParams")
//                }
//
//                Route.TEST_SERIES_FLOW.name -> {
//                    val screenParams = String(
//                        Base64.encode(
//                            gson.toJson(screenParams).toByteArray(),
//                            Base64.DEFAULT
//                        ),
//                        Charsets.UTF_8
//                    ).trim()
//                    nestedNavHost
//                        .navigate("${Route.TEST_SERIES_FLOW.name}?params=$screenParams")
//                }
//
//                else -> {}
//            }
//        } else {
//            nestedNavHost.navigate("${Route.TEST_SERIES_FLOW.name}")
//        }
//    }
//
//    BackHandler {
//        if (!nestedNavHost.popBackStack()) {
//            navHostController.popBackStack()
//        }
//    }
//
//    LaunchedEffect(key1 = Unit) {
//        sharedViewModel.setDrawerMidOptions(
//            options = testNavDrawerContent(),
//            handler = object : BaseDrawerNavigation(navHostController) {
//                override fun handleDrawerNavigation(itemId: String) {
//                    when (itemId) {
//                        "my_tests" -> { nestedNavHost.navigate(TestSeriesPathCreator().addTestDashboard().build(Route.TEST_SERIES_FLOW.name, gson)) }
//                        "explore_tests" -> { nestedNavHost.navigate(TestSeriesPathCreator().addExploreTest().build(Route.TEST_SERIES_FLOW.name, gson)) }
//                        "evaluation" -> { nestedNavHost.navigate(TestSeriesPathCreator().addTestAttempted().build(Route.TEST_SERIES_FLOW.name, gson)) }
//                        "saved_tests" -> { nestedNavHost.navigate(TestSeriesPathCreator().addSavedTest().build(Route.TEST_SERIES_FLOW.name, gson)) }
//                        else -> super.handleDrawerNavigation(itemId)
//                    }
//                }
//            }
//        )
//        viewModel.uiEvent.collect {
//            when(it) {
//                is TestSeriesUIEvent.OpenTestScreen -> {
//                    nestedNavHost.navigate(TestSeriesPathCreator().addTestWelcome(it.testId).build(Route.TEST_FLOW.name, gson))
//                }
//                is TestSeriesUIEvent.OpenTestResultScreen -> {
//                    nestedNavHost.navigate(TestSeriesPathCreator().addTestResult(it.testId).build(Route.TEST_FLOW.name, gson))
//                }
//                else -> {}
//            }
//        }
//    }
//
//    LaunchedEffect(key1 = Unit) {
//        viewModel.error.collect { sharedViewModel.showError(it) }
//    }
//
//    NavHost(
//        navController = nestedNavHost,
//        startDestination = Route.TEST_SERIES_FLOW.name + "?params={screen_params}",
//        route = Route.TEST_SERIES_ROOT.name
//    ) {
//        composable(
//            route = Route.TEST_FLOW.name + "?params={screen_params}",
//            arguments = listOf(
//                navArgument(name = "screen_params") {
//                    type = NavType.StringType
//                    defaultValue = null
//                    nullable = true
//                }
//            )
//        ) {
//            TestFlowContainer(
//                viewModel = viewModel,
//                appVm = sharedViewModel,
//                navHostController = nestedNavHost,
//                modifier = modifier
//            )
//        }
//        composable(
//            route = Route.TEST_SERIES_FLOW.name + "?params={screen_params}",
//            arguments = listOf(
//                navArgument(name = "screen_params") {
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                }
//            )
//        ) {
//            TestSeriesFlowContainer(
//                sharedViewModel = sharedViewModel,
//                viewModel = viewModel,
//                navHostController = nestedNavHost,
//                modifier = modifier
//            )
//        }
//    }
//}