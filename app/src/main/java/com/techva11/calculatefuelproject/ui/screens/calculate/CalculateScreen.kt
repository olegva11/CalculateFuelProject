package com.techva11.calculatefuelproject.ui.screens.calculate

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.techva11.calculatefuelproject.ui.MainViewModel
import com.techva11.calculatefuelproject.ui.screens.calculate.components.Header
import com.techva11.calculatefuelproject.ui.screens.calculate.components.MainBlock

@Composable
fun CalculateScreen(viewModel: MainViewModel) {
    Column {
        Header(viewModel)
        MainBlock(viewModel)
    }
}


