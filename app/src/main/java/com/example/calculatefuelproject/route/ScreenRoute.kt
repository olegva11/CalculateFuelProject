package com.example.calculatefuelproject.route

sealed class ScreenRoute(val route: String) {
    object CalculateScreen : ScreenRoute("CalculateScreen")
    object HistoryScreen : ScreenRoute("HistoryScreen")
}