package com.sung2063.tableau_library.progress

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_library.R
import com.sung2063.tableau_library.progress.adapter.DotProgressItemsAdapter
import com.sung2063.tableau_library.progress.adapter.LinearProgressItemsAdapter
import com.sung2063.tableau_library.progress.handler.DotProgressHandler
import com.sung2063.tableau_library.progress.handler.LinearProgressHandler

class DotProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    // UI objects
    private var isUsingCommonColor: Boolean
    private var commonFilledColor: String?
    private var maxValue: Int

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.DotProgressView, 0, 0)
        isUsingCommonColor = typedArray.getBoolean(R.styleable.DotProgressView_useCommonColor, false)
        commonFilledColor = typedArray.getString(R.styleable.DotProgressView_filledColor)
        maxValue = typedArray.getInteger(R.styleable.DotProgressView_maxValue, 100)
    }

    fun setHandler(_handler: DotProgressHandler) {

        View.inflate(context, R.layout.dot_progress_layout, this)
        val recyclerView: RecyclerView = findViewById(R.id.rv_dot_progress_list)

        // Get the data
        val adapter = DotProgressItemsAdapter(context, _handler.dataList, isUsingCommonColor, commonFilledColor)

        val gridLayoutManager = GridLayoutManager(context, _handler.dataList.size)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter

    }
}