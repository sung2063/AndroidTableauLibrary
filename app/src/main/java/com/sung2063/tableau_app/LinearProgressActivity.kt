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
        val dataList = mutableListOf(
            LinearProgressModel("Category 1", 40f, "#395F7C"),
            LinearProgressModel("Category 2", 55f, "#7c3961"),
            LinearProgressModel("Category 3", 100f, "#397c6f"),
            LinearProgressModel("Category 4", 30f, "#7c3958"),
            LinearProgressModel("Category 5", 80f, "#7c7939")
        )

        // [2] Set Handler and link with the view
        linearProgressView.setHandler(LinearProgressHandler(dataList))

    }
}