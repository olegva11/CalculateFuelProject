package com.techva11.calculatefuelproject.bottom_menu

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomMenu(navController: NavController) {
    val menuItems =
        listOf(BottomMenu.CalculateScreen, BottomMenu.HistoryScreen)

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = contentColorFor(MaterialTheme.colorScheme.primaryContainer)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        menuItems.forEach { item ->
            NavigationBarItem(
                label = { Text(LocalContext.current.getString(item.title)) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route, onClick = {
                    navController.navigate(item.route)
                    {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = "Icon bottom") }
            )
        }
    }
}