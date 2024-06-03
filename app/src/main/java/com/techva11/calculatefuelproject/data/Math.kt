package com.techva11.calculatefuelproject.data

import kotlin.math.roundToInt

fun Float.roundFloat(): Float {
    return (this * 100f).roundToInt() / 100f
}

fun Float.roundFloatOneDigit(): Float {
    return (this * 10f).roundToInt() / 10f
}