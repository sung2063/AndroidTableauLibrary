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
        val dataList = ArrayList<LinearProgressModel>()
        dataList.add(LinearProgressModel("Travel", 20f, "#395F7C"))
        dataList.add(LinearProgressModel("Housing", 50f, "#7c3961"))
        dataList.add(LinearProgressModel("Utility", 80f, "#397c6f"))

        // [2] Set Handler and link with the view
        val handler = LinearProgressHandler(dataList)
        linearProgressView.setHandler(handler)

    }
}