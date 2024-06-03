package com.techva11.calculatefuelproject.route

sealed class ScreenRoute(val route: String) {
    object CalculateScreen : ScreenRoute("CalculateScreen")
    object HistoryScreen : ScreenRoute("HistoryScreen")
}