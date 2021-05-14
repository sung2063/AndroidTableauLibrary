package com.sung2063.tableau_library.progress

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_library.R
import com.sung2063.tableau_library.progress.adapter.LinearProgressItemsAdapter
import com.sung2063.tableau_library.progress.handler.LinearProgressHandler

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
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.LinearProgressView, 0, 0)
        isUsingCommonColor = typedArray.getBoolean(R.styleable.LinearProgressView_useCommonColor, false)
        commonFilledColor = typedArray.getString(R.styleable.LinearProgressView_filledColor)
        commonUnfilledColor = typedArray.getString(R.styleable.LinearProgressView_unfilledColor)
        maxValue = typedArray.getInteger(R.styleable.LinearProgressView_maxValue, 100)
    }

    fun setHandler(_handler: LinearProgressHandler) {

        View.inflate(context, R.layout.linear_progress_layout, this)
        val recyclerView: RecyclerView = findViewById(R.id.rv_linear_progress_list)

        // Create a RecyclerView with user data
        val adapter = LinearProgressItemsAdapter(context, _handler.dataList, isUsingCommonColor, commonFilledColor, commonUnfilledColor, maxValue, _handler.space)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
}