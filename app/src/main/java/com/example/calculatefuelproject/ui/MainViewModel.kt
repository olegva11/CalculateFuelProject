package com.example.calculatefuelproject.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculatefuelproject.MainApp
import com.example.calculatefuelproject.database.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = (application as MainApp).appContainer.repository

    private val _historyRecords = MutableStateFlow<List<HistoryEntity>>(emptyList())
    val historyRecords: StateFlow<List<HistoryEntity>> = _historyRecords

    private val _lastRecord = MutableStateFlow<HistoryEntity>(HistoryEntity(0, 0f, 0f, 0f))
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
                _lastRecord.value = it ?: HistoryEntity(0,0f,0f,0f)

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
            repository.insertNew(HistoryEntity(0, distance.value, fuelPrice.value, fuelCount.value))
        }
    }

    private fun recalculateTripCost() {
        tripCostCalculated.value = fuelPrice.value * fuelCount.value
    }

    private fun recalculateAverageLitres() {
        averageLitresCalculated.value = fuelCount.value / distance.value * 100
    }

}