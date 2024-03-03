package com.example.calculatefuelproject.ui.screens.calculate.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatefuelproject.R
import com.example.calculatefuelproject.ui.MainViewModel
import com.example.calculatefuelproject.ui.theme.BackGround
import com.example.calculatefuelproject.ui.theme.GreyCustom


@Composable
fun Header(viewModel: MainViewModel) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Min)
                .padding(40.dp),
            colors = CardDefaults.cardColors(containerColor = BackGround)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = String.format(
                        "%.1f",
                        viewModel.tripCostCalculated.collectAsState().value
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
                Text(
                    text = stringResource(R.string.travel_price_title),
                    color = GreyCustom,
                )

                Text(
                    text = String.format(
                        "%.1f",
                        viewModel.averageLitresCalculated.collectAsState().value
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
                Text(
                    text = stringResource(R.string.average_litres_title),
                    color = GreyCustom,
                )
            }
        }
    }
}