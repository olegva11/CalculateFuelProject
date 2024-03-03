package com.example.calculatefuelproject.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calculatefuelproject.ui.MainViewModel
import com.example.calculatefuelproject.ui.screens.calculate.CalculateScreen
import com.example.calculatefuelproject.ui.screens.history.HistoryScreen

@Composable
fun Navigation(viewModel: MainViewModel, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.CalculateScreen.route
    )
    {
       bottomNavigation(viewModel)
    }
}

fun NavGraphBuilder.bottomNavigation(
    viewModel: MainViewModel
) {
    composable(route = ScreenRoute.CalculateScreen.route) {
        CalculateScreen(viewModel = viewModel)
    }
    composable(route = ScreenRoute.HistoryScreen.route){
        HistoryScreen(viewModel = viewModel)
    }
}