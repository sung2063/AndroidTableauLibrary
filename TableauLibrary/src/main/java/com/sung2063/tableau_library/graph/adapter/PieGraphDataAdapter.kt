package com.sung2063.tableau_library.graph.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_library.R
import com.sung2063.tableau_library.graph.model.PieGraphModel

class PieGraphDataAdapter(
    private val data: List<PieGraphModel>,
    private val isUsingArcColor: Boolean,
    private val alphaList: List<Int>?
) : RecyclerView.Adapter<PieGraphDataAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivLegendColor: ImageView = view.findViewById(R.id.iv_legend_color)
        val tvLegendTitle: TextView = view.findViewById(R.id.tv_legend_title)
        val tvLegendValue: TextView = view.findViewById(R.id.tv_legend_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_pie_graph_data_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvLegendTitle.text = data[position].name
        holder.tvLegendValue.text = data[position].value.toString()

        val gradientDrawable = holder.ivLegendColor.drawable as GradientDrawable
        var legendColor: String?
        if (isUsingArcColor) {
            legendColor = data[position].color
        } else {
            legendColor = "#023047"
            alphaList.let { gradientDrawable.alpha = it?.get(position)!! }
        }
        gradientDrawable.setColor(Color.parseColor(legendColor))
    }

    override fun getItemCount() = data.size

}