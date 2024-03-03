package com.example.calculatefuelproject.ui.screens.calculate

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.calculatefuelproject.ui.MainViewModel
import com.example.calculatefuelproject.ui.screens.calculate.components.Header
import com.example.calculatefuelproject.ui.screens.calculate.components.MainBlock

@Composable
fun CalculateScreen(viewModel: MainViewModel) {
    Column {
        Header(viewModel)
        MainBlock(viewModel)
    }
}


