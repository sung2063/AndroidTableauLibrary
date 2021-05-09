package com.sung2063.tableau_library.progress.model

data class DotProgressModel(
    var name: String,
    var value: Float = 0f,
    var progressColor: String? = "#395F7C",
    var backStackColor: String? = "#fbf8fa"
)