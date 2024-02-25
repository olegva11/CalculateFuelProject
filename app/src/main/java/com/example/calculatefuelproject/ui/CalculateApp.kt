package com.example.calculatefuelproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatefuelproject.MainViewModel
import com.example.calculatefuelproject.R
import com.example.calculatefuelproject.models.TypeProgressBar
import com.example.calculatefuelproject.ui.theme.BackGround
import com.example.calculatefuelproject.ui.theme.GradientFinish
import com.example.calculatefuelproject.ui.theme.GradientStart
import com.example.calculatefuelproject.ui.theme.GreyCustom
import kotlin.math.roundToInt

@Composable
fun CalcApp(viewModel: MainViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(GradientStart, GradientFinish)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header(viewModel)
        MainBlock(viewModel)
    }
}

@Composable
fun MainBlock(viewModel: MainViewModel) {


    Card(
        modifier = Modifier.wrapContentHeight(align = Alignment.Bottom),
        shape = RoundedCornerShape(0),
        colors = CardDefaults.cardColors(containerColor = BackGround)
    ) {

        BlockWithProgress(TypeProgressBar.Distance, viewModel.distance.collectAsState().value, onValueChange = { viewModel.setDistance(it) })
        BlockWithProgress(TypeProgressBar.Gas, viewModel.fuelCount.collectAsState().value, onValueChange = { viewModel.setFuelCount(it) })
        BlockWithProgress(TypeProgressBar.Price, viewModel.fuelPrice.collectAsState().value, onValueChange = { viewModel.setFuelPrice(it) })

        Button(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
            .background(
                Brush.horizontalGradient(
                    colors = listOf(GradientStart, GradientFinish)
                ),
                shape = ButtonDefaults.shape
            ),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            onClick = {
                viewModel.calculate()
            }) {

            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.calculate_button),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun BlockWithProgress(typeProgress: TypeProgressBar, value: Float, onValueChange: (Float) -> Unit = {}) {


    Column(modifier = Modifier.padding(15.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start,
                text = typeProgress.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                text = "${value.roundToInt()} ${typeProgress.unit}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Slider(
            value = value, onValueChange = {
                onValueChange(it)
            },
            valueRange = typeProgress.valueRange,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = GradientStart
            )
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            for (textPoint in typeProgress.labels) {
                Text(
                    modifier = Modifier.width(30.dp),
                    text = "$textPoint",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun Header(viewModel: MainViewModel) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(R.string.fuel_calculator_title),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

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
                    text = String.format("%.1f", viewModel.tripCostCalculated.collectAsState().value),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
                Text(
                    text = stringResource(R.string.travel_price_title),
                    color = GreyCustom,
                )

                Text(
                    text = String.format("%.1f", viewModel.averageLitresCalculated.collectAsState().value),
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