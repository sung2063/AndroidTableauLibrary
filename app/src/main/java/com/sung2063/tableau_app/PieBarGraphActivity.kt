package com.sung2063.tableau_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sung2063.tableau_library.graph.PieGraphView
import com.sung2063.tableau_library.graph.handler.PieGraphHandler
import com.sung2063.tableau_library.graph.model.PieGraphModel

class PieBarGraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_bar)

        val pieGraphView : PieGraphView = findViewById(R.id.pie_graph_view)

        // [1] Create the data
        val dataList = ArrayList<PieGraphModel>()
        dataList.add(PieGraphModel("Travel", 20f, "#ed6234"))
        dataList.add(PieGraphModel("Housing", 40f, "#ebdf38"))
        dataList.add(PieGraphModel("Utility", 60f, "#81e82c"))
        dataList.add(PieGraphModel("Utility", 35f, "#2784d6"))
        dataList.add(PieGraphModel("Utility", 15f, "#7225c4"))
        dataList.add(PieGraphModel("Utility", 5f, "#cf1da5"))

        // [2] Set Handler and link with the view
        val handler = PieGraphHandler(dataList)
        pieGraphView.setHandler(dataList)

    }

}