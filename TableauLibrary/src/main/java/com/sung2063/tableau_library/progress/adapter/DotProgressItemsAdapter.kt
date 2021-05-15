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
    private val dataList: List<DotProgressModel>,
    private val isUsingCommonColor: Boolean,
    private var commonFilledColor: String?
) : RecyclerView.Adapter<DotProgressItemsAdapter.ViewHolder>() {

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

        viewHolder.tvPercentage.text = "0%"
        viewHolder.tvTitle.text = dataList[position].name

        for (i in 0 until maxNumScale) {
            viewHolder.llDotContainer.addView(createScale())
        }

        commonFilledColor.let { commonFilledColor = "#395f7c" }
        val filledColor =
            if (isUsingCommonColor) commonFilledColor else dataList[position].progressColor

        val scope = CoroutineScope(Dispatchers.Main + CoroutineName("ProgressCounter"))
        scope.launch {
            val numOfFillingBox = dataList[position].value.div(5).toInt()
            for (tracking in 0 until numOfFillingBox) {
                val fillingBoxIndex = maxNumScale.minus(tracking)

                // Update filling box
                val fillingBox = viewHolder.llDotContainer.getChildAt(fillingBoxIndex)
                val childView = fillingBox as? ImageView
                childView?.setImageResource(R.drawable.dot_progress_filled)
                val activeImage = childView?.drawable
                val gradientActiveImage = activeImage as? GradientDrawable
                gradientActiveImage?.setColor(Color.parseColor(filledColor))

                // Update text
                viewHolder.tvPercentage.text = "${tracking}%"

                delay(50L)
            }
        }
    }

    override fun getItemCount() = dataList.size

    private fun createScale(): ImageView {
        var scaleUnit = ImageView(context)
        val unfilledViewParam = LinearLayout.LayoutParams(150, 25)
        unfilledViewParam.setMargins(0, 10, 0, 10)
        scaleUnit.layoutParams = unfilledViewParam
        scaleUnit.setImageResource(R.drawable.dot_progress_unfilled)
        return scaleUnit
    }

    companion object {
        private const val maxNumScale = 20
    }
}