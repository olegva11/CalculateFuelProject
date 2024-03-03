package com.example.calculatefuelproject.bottom_menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.calculatefuelproject.route.ScreenRoute

sealed class BottomMenu(val route: String, val icon: ImageVector, val title: String)
{
    object CalculateScreen : BottomMenu(ScreenRoute.CalculateScreen.route, Icons.Default.Add, "Calculate")
    object HistoryScreen : BottomMenu(ScreenRoute.HistoryScreen.route, Icons.Default.List, "History")
}