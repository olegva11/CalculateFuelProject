package com.example.calculatefuelproject.ui.screens.calculate.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.calculatefuelproject.R
import com.example.calculatefuelproject.models.TypeProgressBar
import com.example.calculatefuelproject.shared.ButtonCustom
import com.example.calculatefuelproject.ui.MainViewModel
import com.example.calculatefuelproject.ui.theme.BackGround

@Composable
fun MainBlock(viewModel: MainViewModel) {
    Card(
        modifier = Modifier.fillMaxHeight(),
        shape = RoundedCornerShape(0),
        colors = CardDefaults.cardColors(containerColor = BackGround)
    ) {
        BlockWithProgress(
            TypeProgressBar.Distance,
            viewModel.distance.collectAsState().value,
            onValueChange = {
                viewModel.setDistance(it)
                viewModel.calculate()
            })
        BlockWithProgress(
            TypeProgressBar.Gas,
            viewModel.fuelCount.collectAsState().value,
            onValueChange = {
                viewModel.setFuelCount(it)
                viewModel.calculate()
            })
        BlockWithProgress(
            TypeProgressBar.Price,
            viewModel.fuelPrice.collectAsState().value,
            onValueChange = {
                viewModel.setFuelPrice(it)
                viewModel.calculate()
            })

        ButtonCustom(buttonText = stringResource(R.string.calculate_button),
            onClickAction = { viewModel.writeToDB() })
    }
}