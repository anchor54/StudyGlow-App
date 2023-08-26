package com.example.studyglows.navigation.navgraphs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.cart.CartScreen
import com.example.studyglows.screens.cart.CartViewModel

fun NavGraphBuilder.cartNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = Screen.Cart.route,
        route = Route.CART_ROUTE.name
    ) {
        composable(route = Screen.Cart.route) {
            CartScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController = navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}