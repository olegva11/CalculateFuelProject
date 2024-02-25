package com.example.calculatefuelproject.data

enum class TypeProgressBar(val title: String, val valueRange: ClosedFloatingPointRange<Float>, val unit: String, val labels: List<Int>) {
    Distance("Distance", 0f..600f,"km", listOf(0, 150, 300, 450, 600)),
    Gas("Gas", 0f..100f, "l", listOf(0, 25, 50, 75, 100)),
    Price("Price of litre", 0f..100f, "UAH", listOf(0, 25, 50, 75, 100))

}