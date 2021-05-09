package com.sung2063.tableau_library.progress.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_library.R
import com.sung2063.tableau_library.progress.model.DotProgressModel
import kotlinx.coroutines.*

class DotProgressItemsAdapter(
    private val context: Context,
    private val dataList: List<DotProgressModel>
) : RecyclerView.Adapter<DotProgressItemsAdapter.ViewHolder>() {

    private val maxNumOfScale = 20

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llDotContainer: LinearLayout = view.findViewById(R.id.ll_dot_container)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvPercentage: TextView = view.findViewById(R.id.tv_percentage)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.dot_progress_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.tvPercentage.text = "${dataList[position].value?.toInt()}%"
        viewHolder.tvTitle.text = dataList[position].name

        for (i in 0 until maxNumOfScale) {
            viewHolder.llDotContainer.addView(createScale())
        }

        val scope = CoroutineScope(Dispatchers.Main + CoroutineName("ProgressCounter"))
        scope.launch {
            val numOfFillingBox = dataList[position].value?.div(5).toInt()
            for (index in 0 until numOfFillingBox) {
                val fillingBoxIndex = maxNumOfScale - index
                val fillingBox = viewHolder.llDotContainer.getChildAt(fillingBoxIndex)
                val childView = fillingBox as? ImageView
                childView?.setImageResource(R.drawable.dot_progress_filled)
                val activeImage = childView?.drawable
                val gradientActiveImage = activeImage as? GradientDrawable
                gradientActiveImage?.setColor(Color.parseColor(dataList[position].progressColor))
                delay(50L)
            }
        }
    }

    override fun getItemCount() = dataList.size

    private fun createScale(): ImageView {
        var scaleUnit = ImageView(context)
        val unfilledViewParam = LinearLayout.LayoutParams(150, 25)
        unfilledViewParam.setMargins(0, 15, 0, 15)
        scaleUnit.layoutParams = unfilledViewParam
        scaleUnit.setImageResource(R.drawable.dot_progress_unfilled)
        return scaleUnit
    }

}