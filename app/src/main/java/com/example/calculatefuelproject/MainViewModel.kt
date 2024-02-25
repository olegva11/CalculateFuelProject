package com.example.calculatefuelproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    //val inputData = MutableStateFlow(InputParameters(0f, 0f, 0f))

    private val _distance = MutableStateFlow(0f)
    val distance: StateFlow<Float>
        get() = _distance

    private val _fuelCount = MutableStateFlow(0f)
    val fuelCount: StateFlow<Float>
        get() = _fuelCount

    private val _fuelPrice = MutableStateFlow(0f)
    val fuelPrice: StateFlow<Float>
        get() = _fuelPrice

    val tripCostCalculated = MutableStateFlow(50f)
    val averageLitresCalculated = MutableStateFlow(160f)

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

    private fun recalculateTripCost() {
        tripCostCalculated.value = fuelPrice.value * fuelCount.value
    }

    private fun recalculateAverageLitres() {
        averageLitresCalculated.value = fuelCount.value / distance.value * 100
    }

}