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

    fun setHandler(handler: DotProgressHandler) {

        View.inflate(context, R.layout.linear_progress_layout, this)

        val recyclerView: RecyclerView = findViewById(R.id.rv_linear_progress_list)

        // Get the data
        val dataList = handler.dataList
        val adapter = DotProgressItemsAdapter(context, dataList)
        val gridLayoutManager = GridLayoutManager(context, dataList.size)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter

    }
}