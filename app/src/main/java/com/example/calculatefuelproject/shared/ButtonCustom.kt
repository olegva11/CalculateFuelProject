package com.example.calculatefuelproject.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatefuelproject.ui.theme.GradientFinish
import com.example.calculatefuelproject.ui.theme.GradientStart

@Composable
fun ButtonCustom(buttonText: String, onClickAction: () -> Unit = {}) {
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
            onClickAction()
        }) {

        Text(
            textAlign = TextAlign.Center,
            text = buttonText,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}