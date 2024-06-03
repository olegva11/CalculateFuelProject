package com.techva11.calculatefuelproject.ui.screens.calculate.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.techva11.calculatefuelproject.R
import com.techva11.calculatefuelproject.models.TypeProgressBar
import com.techva11.calculatefuelproject.shared.ButtonCustom
import com.techva11.calculatefuelproject.ui.MainViewModel
import com.techva11.calculatefuelproject.ui.theme.BackGround

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
            painterResource(id = R.drawable.distance_icon_custom),
            onValueChange = {
                viewModel.setDistance(it)
                viewModel.calculate()
            })
        BlockWithProgress(
            TypeProgressBar.Gas,
            viewModel.fuelCount.collectAsState().value,
            rememberVectorPainter(Icons.Default.LocalGasStation),
            onValueChange = {
                viewModel.setFuelCount(it)
                viewModel.calculate()
            })
        BlockWithProgress(
            TypeProgressBar.Price,
            viewModel.fuelPrice.collectAsState().value,
            rememberVectorPainter(Icons.Default.AttachMoney),
            onValueChange = {
                viewModel.setFuelPrice(it)
                viewModel.calculate()
            })

        ButtonCustom(buttonText = stringResource(R.string.calculate_button),
            onClickAction = { viewModel.writeToDB() })
    }
}