package com.example.calculatefuelproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatefuelproject.data.TypeProgressBar
import com.example.calculatefuelproject.ui.theme.BackGround
import com.example.calculatefuelproject.ui.theme.CalculateFuelProjectTheme
import com.example.calculatefuelproject.ui.theme.GradientFinish
import com.example.calculatefuelproject.ui.theme.GradientStart
import com.example.calculatefuelproject.ui.theme.GreyCustom
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateFuelProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalcApp()
                }
            }
        }
    }
}

@Composable
fun CalcApp() {

    val valueTravelPriceMutable by rememberSaveable {
        mutableFloatStateOf(160f)
    }
    val valueTravelPrice = remember { mutableFloatStateOf(valueTravelPriceMutable) }

    val valueAverageLitresMutable by rememberSaveable {
        mutableFloatStateOf(160f)
    }
    val valueAverageLitres = remember { mutableFloatStateOf(valueAverageLitresMutable) }

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
        Header(valueTravelPrice, valueAverageLitres)
        MainBlock(valueTravelPrice, valueAverageLitres)
    }
}

@Composable
fun MainBlock(valueTravelPrice: MutableFloatState, valueAverageLitres: MutableFloatState) {
    val valueDistanceMutable by rememberSaveable {
        mutableFloatStateOf(160f)
    }
    val valueDistance = remember { mutableFloatStateOf(valueDistanceMutable) }

    val valueGasMutable by rememberSaveable {
        mutableFloatStateOf(50f)
    }
    val valueGas = remember { mutableFloatStateOf(valueGasMutable) }

    val valuePriceMutable by rememberSaveable {
        mutableFloatStateOf(50f)
    }
    val valuePrice = remember { mutableFloatStateOf(valuePriceMutable) }

    Card(
        modifier = Modifier.wrapContentHeight(align = Alignment.Bottom),
        shape = RoundedCornerShape(0),
        colors = CardDefaults.cardColors(containerColor = BackGround)
    ) {

        BlockWithProgress(TypeProgressBar.Distance, valueDistance)
        BlockWithProgress(TypeProgressBar.Gas, valueGas)
        BlockWithProgress(TypeProgressBar.Price, valuePrice)

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

                valueTravelPrice.floatValue = valuePrice.floatValue * valueGas.floatValue

                valueAverageLitres.floatValue = valueGas.floatValue / valueDistance.floatValue * 100

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
fun BlockWithProgress(typeProgress: TypeProgressBar, value: MutableFloatState) {


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
                text = "${value.floatValue.roundToInt()} ${typeProgress.unit}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Slider(
            value = value.floatValue, onValueChange = {
                value.floatValue = it
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
fun Header(valueTravelPrice: MutableFloatState, valueAverageLitres: MutableFloatState) {

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
                    text = String.format("%.1f", valueTravelPrice.floatValue),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
                Text(
                    text = stringResource(R.string.travel_price_title),
                    color = GreyCustom,
                )

                Text(
                    text = String.format("%.1f", valueAverageLitres.floatValue),
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

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    CalculateFuelProjectTheme {
        CalcApp()
    }
}