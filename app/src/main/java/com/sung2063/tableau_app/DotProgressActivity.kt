package com.sung2063.tableau_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sung2063.tableau_library.progress.DotProgressView
import com.sung2063.tableau_library.progress.handler.DotProgressHandler
import com.sung2063.tableau_library.progress.model.DotProgressModel

class DotProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dot_progress)

        val dotProgressView: DotProgressView = findViewById(R.id.dot_progress_view)

        // [1] Create the data
        val dataList = ArrayList<DotProgressModel>()
        dataList.add(DotProgressModel("Category 1", 30f, "#395f7c"))
        dataList.add(DotProgressModel("Category 2", 50f, "#8ca8be"))
        dataList.add(DotProgressModel("Category 3", 70f, "#395f7c"))
        dataList.add(DotProgressModel("Category 4", 95f, "#8ca8be"))

        // [2] Set Handler and link with the view
        val handler = DotProgressHandler(dataList)
        dotProgressView.setHandler(handler)

    }
}