package com.sung2063.tableau_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sung2063.tableau_library.progress.LinearProgressView
import com.sung2063.tableau_library.progress.handler.LinearProgressHandler
import com.sung2063.tableau_library.progress.model.LinearProgressModel

class LinearProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_progress)

        val linearProgressView: LinearProgressView = findViewById(R.id.linear_progress_view)

        // [1] Create the data
        val dataList = mutableListOf<LinearProgressModel>()
        dataList.add(LinearProgressModel("Category 1", 40f, "#395F7C"))
        dataList.add(LinearProgressModel("Category 2", 55f, "#7c3961"))
        dataList.add(LinearProgressModel("Category 3", 100f, "#397c6f"))
        dataList.add(LinearProgressModel("Category 4", 30f, "#7c3958"))
        dataList.add(LinearProgressModel("Category 5", 80f, "#7c7939"))

        // [2] Set Handler and link with the view
        val handler = LinearProgressHandler(dataList)
        linearProgressView.setHandler(handler)

    }
}