package com.sung2063.tableau_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_library.graph.PieGraphView
import com.sung2063.tableau_library.graph.adapter.PieGraphDataAdapter
import com.sung2063.tableau_library.graph.handler.PieGraphHandler
import com.sung2063.tableau_library.graph.model.PieGraphModel

class PieGraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_graph)

        val pieGraphView: PieGraphView = findViewById(R.id.pie_graph_view)

        // [1] Create the data
        val dataList = arrayListOf(
            PieGraphModel("Category 1", 20f, "#ed6234"),
            PieGraphModel("Category 2", 40f, "#ebdf38"),
            PieGraphModel("Category 3", 60f, "#81e82c"),
            PieGraphModel("Category 4", 35f, "#2784d6"),
            PieGraphModel("Category 5", 15f, "#7225c4")
        )

        // [2] Set Handler and link with the view
        val handler = PieGraphHandler(dataList)
        pieGraphView.setHandler(handler)

    }

}