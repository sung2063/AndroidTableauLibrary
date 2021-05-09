package com.sung2063.tableau_library.progress.handler

import com.sung2063.tableau_library.progress.model.DotProgressModel

class DotProgressHandler(
    var dataList: List<DotProgressModel>,
    filledColor: String = "#395F7C",
    unfilledColor: String = "#fbf8fa"
) : ProgressHandler(filledColor, unfilledColor)