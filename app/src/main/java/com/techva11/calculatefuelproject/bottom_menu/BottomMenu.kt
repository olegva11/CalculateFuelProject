package com.techva11.calculatefuelproject.bottom_menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.techva11.calculatefuelproject.R
import com.techva11.calculatefuelproject.route.ScreenRoute

sealed class BottomMenu(val route: String, val icon: ImageVector, val title: Int) {
    object CalculateScreen : BottomMenu(
        ScreenRoute.CalculateScreen.route, Icons.Default.Add,
        R.string.calculate_bottom_menu
    )

    object HistoryScreen : BottomMenu(
        ScreenRoute.HistoryScreen.route, Icons.Default.List,
        R.string.history_bottom_menu
    )
}