package com.sung2063.tableau_library.progress.handler

import com.sung2063.tableau_library.progress.model.LinearProgressModel

class LinearProgressHandler(
    var dataList: List<LinearProgressModel>,
    filledColor: String = "#395F7C",
    unfilledColor: String = "#fbf8fa",
    var space: Int = 5
) : ProgressHandler(filledColor, unfilledColor)