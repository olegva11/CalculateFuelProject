package com.example.calculatefuelproject.ui.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatefuelproject.database.HistoryEntity
import com.example.calculatefuelproject.ui.MainViewModel

@Composable
fun HistoryScreen(viewModel: MainViewModel) {
    Column {
        Text("History:")
        HistoryBlock(viewModel.historyRecords.collectAsState())
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
    Card(Modifier.padding(start = 10.dp, end = 10.dp)) {
        Row(Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = "Distance: ${historyEntity.distance.toInt()}")
            Text(text = "Count: ${historyEntity.countGas.toInt()}")
            Text(text = "Price: ${historyEntity.priceGas.toInt()}")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHistory() {
    ItemHistory(historyEntity = HistoryEntity(0, 360f, 27.3f, 45f))
}