package com.example.calculatefuelproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.compose.rememberNavController
import com.example.calculatefuelproject.bottom_menu.BottomMenu
import com.example.calculatefuelproject.route.Navigation
import com.example.calculatefuelproject.ui.theme.GradientFinish
import com.example.calculatefuelproject.ui.theme.GradientStart

@Composable
fun CalcApp(viewModel: MainViewModel) {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomMenu(navController) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(GradientStart, GradientFinish)
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Navigation(viewModel = viewModel, navController = navController)
        }
    }
}
