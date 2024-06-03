package com.techva11.calculatefuelproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techva11.calculatefuelproject.data.Repository
import com.techva11.calculatefuelproject.database.HistoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _historyRecords = MutableStateFlow<List<HistoryEntity>>(emptyList())
    val historyRecords: StateFlow<List<HistoryEntity>> = _historyRecords

    private val _lastRecord = MutableStateFlow(HistoryEntity(0, 0f, 0f, 0f, 0f, 0f, ""))
    val lastRecord: StateFlow<HistoryEntity> = _lastRecord

    private val _distance = MutableStateFlow(0f)
    val distance: StateFlow<Float> = _distance

    private val _fuelCount = MutableStateFlow(0f)
    val fuelCount: StateFlow<Float> = _fuelCount

    private val _fuelPrice = MutableStateFlow(0f)
    val fuelPrice: StateFlow<Float> = _fuelPrice

    val tripCostCalculated = MutableStateFlow(50f)
    val averageLitresCalculated = MutableStateFlow(160f)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllHistory().collect {
                _historyRecords.value = it
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            repository.getLast().collect {
                _lastRecord.value = it ?: HistoryEntity(0, 100f, 27f, 10f, 0f, 0f,"")

                _distance.value = _lastRecord.value.distance
                _fuelCount.value = _lastRecord.value.countGas
                _fuelPrice.value = _lastRecord.value.priceGas

                calculate()
            }
        }
    }

    fun setDistance(value: Float) {
        _distance.value = value
    }

    fun setFuelPrice(value: Float) {
        _fuelPrice.value = value
    }

    fun setFuelCount(value: Float) {
        _fuelCount.value = value
    }

    fun calculate() {
        recalculateTripCost()
        recalculateAverageLitres()
    }

    fun writeToDB() {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insertNew(
                HistoryEntity(
                    0,
                    distance.value,
                    fuelPrice.value,
                    fuelCount.value,
                    tripCostCalculated.value,
                    averageLitresCalculated.value,
                    getDateAndHourNow()
                )
            )
        }
    }

    private fun getDateAndHourNow(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = Date()
        return formatter.format(date)
    }

    private fun recalculateTripCost() {
        tripCostCalculated.value = fuelPrice.value * fuelCount.value
    }

    private fun recalculateAverageLitres() {
        averageLitresCalculated.value = fuelCount.value / distance.value * 100
    }

}