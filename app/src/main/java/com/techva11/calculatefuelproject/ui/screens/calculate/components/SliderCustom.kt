package com.techva11.calculatefuelproject.ui.screens.calculate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techva11.calculatefuelproject.data.roundFloat
import com.techva11.calculatefuelproject.models.TypeProgressBar
import com.techva11.calculatefuelproject.ui.theme.GradientStart

@Composable
fun BlockWithProgress(
    typeProgress: TypeProgressBar,
    value: Float,
    painter: Painter,
    onValueChange: (Float) -> Unit = {}
) {

    Column(modifier = Modifier.padding(15.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.weight(1f)) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painter,
                    contentDescription = "slider icon",
                    tint = Color.White
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    textAlign = TextAlign.Start,
                    text = LocalContext.current.getString(typeProgress.title),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

            }

            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                text = if (value % 1 == 0.0f) {
                    String.format("%.0f ${typeProgress.unit}", value)
                } else {
                    String.format("%.0f ${typeProgress.unit}", value)
                },
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Slider(
            value = value, onValueChange = {
                onValueChange(it.roundFloat())
            },
            valueRange = typeProgress.valueRange,
            steps = typeProgress.valueRange.endInclusive.minus(1).toInt(),
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