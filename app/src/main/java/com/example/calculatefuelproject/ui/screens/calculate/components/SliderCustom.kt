package com.example.calculatefuelproject.ui.screens.calculate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatefuelproject.models.TypeProgressBar
import com.example.calculatefuelproject.ui.theme.GradientStart
import kotlin.math.roundToInt

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