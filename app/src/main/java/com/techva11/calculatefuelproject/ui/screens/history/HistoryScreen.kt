package com.techva11.calculatefuelproject.ui.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techva11.calculatefuelproject.R
import com.techva11.calculatefuelproject.data.roundFloat
import com.techva11.calculatefuelproject.data.roundFloatOneDigit
import com.techva11.calculatefuelproject.database.HistoryEntity
import com.techva11.calculatefuelproject.ui.MainViewModel

@Composable
fun HistoryScreen(viewModel: MainViewModel) {
    Card(Modifier.padding(10.dp)) {
        Column(Modifier.padding(9.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.history_title), fontSize = 20.sp, fontWeight = FontWeight.Bold)

            HistoryBlock(viewModel.historyRecords.collectAsState())
        }
    }
}

@Composable
fun HistoryBlock(historyRecords: State<List<HistoryEntity>>) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState)
    {
        items(historyRecords.value.size)
        {
            ItemHistory(historyRecords.value[it])
        }
    }
}

@Composable
fun ItemHistory(historyEntity: HistoryEntity) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Row {
                Text(
                    text = stringResource(R.string.mask_km, historyEntity.distance.toInt()),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Row{
                Text(
                    text = stringResource(
                        R.string.mask_litres_100,
                        historyEntity.averageLitres.roundFloatOneDigit()
                    ),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.uah_mask, historyEntity.priceGas.roundFloat()),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.padding(top = 2.dp))
            Text(text = historyEntity.date.split(" ").last(),
                color = Color.Gray, textAlign = TextAlign.Center)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text =  stringResource(R.string.uah_mask, historyEntity.tripPrice.roundFloat()),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.litres_mask, historyEntity.countGas.roundFloat()),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHistory() {
    ItemHistory(
        historyEntity = HistoryEntity(
            0,
            360f,
            27.3f,
            45f,
            1200f,
            12.5f,
            "20.02.2020 16:30"
        )
    )
    //HistoryScreen(viewModel = viewModel())
}