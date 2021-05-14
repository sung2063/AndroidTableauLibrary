package com.sung2063.tableau_library.progress.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
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
    private val maxValue: Int,
    private val space: Int = 0
) : RecyclerView.Adapter<LinearProgressItemsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val ivFilledBar: ImageView = view.findViewById(R.id.iv_filled_bar)
        val ivUnfilledBar: ImageView = view.findViewById(R.id.iv_unfilled_bar)
        val tvMiddleValue: TextView = view.findViewById(R.id.tv_middle_value)
        val tvMaxValue: TextView = view.findViewById(R.id.tv_max_value)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.linear_progress_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.tvTitle.text = dataList[position].name

        // Determine filled and unfilled colors
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

        // Set middle and max value
        val middleValue = maxValue.div(2)
        viewHolder.tvMiddleValue.text = middleValue.toString()
        viewHolder.tvMaxValue.text = maxValue.toString()

        // Calculate the screen size before drawn
        viewHolder.ivUnfilledBar.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewHolder.ivUnfilledBar.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val unitScale = viewHolder.ivUnfilledBar.width.toFloat().div(maxValue.minus(1))
                val activeHeight = viewHolder.ivUnfilledBar.height.toFloat()

                val scope = CoroutineScope(Dispatchers.Main + CoroutineName("ProgressCounter"))
                scope.launch {
                    var tracking = 0
                    while (tracking < dataList[position].value) {
                        tracking++
                        val activeWidth = tracking.times(unitScale)

                        // Set new param
                        val newParam = RelativeLayout.LayoutParams(activeWidth.toInt(), activeHeight.toInt())
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