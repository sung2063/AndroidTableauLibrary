package com.sung2063.tableau_library.progress.model

data class LinearProgressModel(
    var name: String,
    var value: Float = 0f,
    var filledColor: String = "#395F7C",
    var unfilledColor: String = "#fbf8fa"
)
