package com.sung2063.tableau_library.progress

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_library.R
import com.sung2063.tableau_library.exception.IllegalArgumentException
import com.sung2063.tableau_library.progress.adapter.LinearProgressItemsAdapter
import com.sung2063.tableau_library.progress.handler.LinearProgressHandler
import com.sung2063.tableau_library.util.RegexTool

class LinearProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    // UI objects
    private var isUsingCommonColor: Boolean
    private var commonFilledColor: String?
    private var commonUnfilledColor: String?
    private var maxValue: Int

    init {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.LinearProgressView, 0, 0)
        isUsingCommonColor =
            typedArray.getBoolean(R.styleable.LinearProgressView_useCommonColor, false)
        commonFilledColor = typedArray.getString(R.styleable.LinearProgressView_filledColor)
        commonUnfilledColor = typedArray.getString(R.styleable.LinearProgressView_unfilledColor)
        maxValue = typedArray.getInteger(R.styleable.LinearProgressView_maxValue, 100)

        // Check conditions & exceptions
        if (commonFilledColor == null) {
            commonFilledColor = "#023047"      // Default color
        } else if (!RegexTool.HEX_COLOR_CODE.toRegex()
                .matches(input = commonFilledColor.toString())
        ) {
            throw IllegalArgumentException(context.getString(R.string.hex_color_code_illegal_error))
        }

        if (commonUnfilledColor == null) {
            commonUnfilledColor = "#fbf8fa"      // Default color
        } else if (!RegexTool.HEX_COLOR_CODE.toRegex()
                .matches(input = commonUnfilledColor.toString())
        ) {
            throw IllegalArgumentException(context.getString(R.string.hex_color_code_illegal_error))
        }
    }

    fun setHandler(_handler: LinearProgressHandler) {

        View.inflate(context, R.layout.linear_progress_layout, this)
        val recyclerView: RecyclerView = findViewById(R.id.rv_linear_progress_list)

        // Create a RecyclerView with user data
        val adapter = LinearProgressItemsAdapter(
            context,
            _handler.dataList,
            isUsingCommonColor,
            commonFilledColor,
            commonUnfilledColor,
            maxValue,
            _handler.space
        )
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
}