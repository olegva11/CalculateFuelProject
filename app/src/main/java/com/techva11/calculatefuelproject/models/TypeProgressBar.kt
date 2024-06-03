package com.techva11.calculatefuelproject.models

import com.techva11.calculatefuelproject.R

enum class TypeProgressBar(
    val title: Int,
    val valueRange: ClosedFloatingPointRange<Float>,
    val unit: String,
    val labels: List<Int>
) {
    Distance(R.string.distance_progress_bar, 0f..600f, "km", listOf(0, 150, 300, 450, 600)),
    Gas(R.string.gas_progress_bar, 0f..100f, "l", listOf(0, 25, 50, 75, 100)),
    Price(R.string.price_of_litre_progress_bar, 0f..100f, "UAH", listOf(0, 25, 50, 75, 100));
}