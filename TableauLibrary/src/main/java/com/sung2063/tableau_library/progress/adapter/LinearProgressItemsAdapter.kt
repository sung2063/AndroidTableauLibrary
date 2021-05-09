package com.sung2063.tableau_library.progress.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_library.R
import com.sung2063.tableau_library.progress.model.LinearProgressModel
import kotlinx.coroutines.*

class LinearProgressItemsAdapter(
    private val context: Context,
    private val dataList: List<LinearProgressModel>,
    private val isUsingCommonColor: Boolean,
    private val commonFilledColor: String?,
    private val commonUnfilledColor: String?,
    private val maxValue: Integer,
    private val space: Int = 0
) : RecyclerView.Adapter<LinearProgressItemsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val ivFilledBar: ImageView = view.findViewById(R.id.iv_filled_bar)
        val ivUnfilledBar: ImageView = view.findViewById(R.id.iv_unfilled_bar)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.linear_progress_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.tvTitle.text = dataList[position].name

        var filledColor: String
        var unfilledColor: String
        if (isUsingCommonColor) {
            filledColor = commonFilledColor!!
            unfilledColor = commonUnfilledColor!!
        } else {
            filledColor = dataList[position].filledColor
            unfilledColor = dataList[position].unfilledColor
        }

        val filledImage = viewHolder.ivFilledBar.drawable
        val gradientFilledImage = filledImage as GradientDrawable
        gradientFilledImage.setColor(Color.parseColor(filledColor))

        val unfilledImage = viewHolder.ivUnfilledBar.drawable
        val gradientUnfilledImage = unfilledImage as GradientDrawable
        gradientUnfilledImage.setColor(Color.parseColor(unfilledColor))

        viewHolder.ivUnfilledBar.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewHolder.ivUnfilledBar.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val unitScale = viewHolder.ivUnfilledBar.width / 100
                val activeHeight = viewHolder.ivUnfilledBar.height

                val scope = CoroutineScope(Dispatchers.Main + CoroutineName("ProgressCounter"))
                scope.launch {
                    var i = 0
                    while (i < dataList[position].value.toInt()) {
                        i++
                        val activeWidth = i.times(unitScale)

                        // Set new param
                        val newParam = RelativeLayout.LayoutParams(activeWidth, activeHeight)
                        newParam.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.iv_unfilled_bar)
                        newParam.setMargins(0, 0, 0, space.times(5))
                        viewHolder.ivFilledBar.layoutParams = newParam

                        delay(10L)
                    }
                }
            }
        })
    }

    override fun getItemCount() = dataList.size

}