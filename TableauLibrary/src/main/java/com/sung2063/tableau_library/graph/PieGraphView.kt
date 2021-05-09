package com.sung2063.tableau_library.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout
import com.sung2063.tableau_library.R
import com.sung2063.tableau_library.graph.model.PieGraphModel
import com.sung2063.tableau_library.graph.util.GraphUtil

class PieGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    // UI objects
    private var isUsingArcColor: Boolean
    private var graphColor: String?

    // Data variables
    private lateinit var dataList: ArrayList<PieGraphModel>
    private lateinit var bounceAnim: Animation

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PieGraphView, 0, 0)
        isUsingArcColor = typedArray.getBoolean(R.styleable.PieGraphView_useArcColor, false)
        graphColor = typedArray.getString(R.styleable.PieGraphView_graphColor)
        setWillNotDraw(false)
    }

    fun setHandler(_dataList: ArrayList<PieGraphModel>) {
        View.inflate(context, R.layout.pie_graph_layout, this)
        dataList = _dataList
        //bounceAnim = AnimationUtils.loadAnimation(context, R.anim.zoom_out_in)
        //startAnimation(bounceAnim)
    }

    override fun onDraw(_canvas: Canvas?) {
        super.onDraw(_canvas)

        // Rectangle object
        var rectF = RectF(150f, 150f, 900f, 900f)

        dataList.sortByDescending { it.value }      // Sort by highest first
        val sum = dataList.sumByDouble { it.value.toDouble() }       // Get sum

        var alphaList = getColorTransparentList(dataList.size)
        var arcStartAngle = 270
        for (i in 0 until dataList.size) {

            val percentage = (dataList[i].value.div(sum)).times(GraphUtil.maxDegree).toInt()
            var color = if (isUsingArcColor) Color.parseColor(dataList[i].color) else Color.parseColor(graphColor)
            val paint = createPaint(color, alphaList[i])
            _canvas?.drawArc(
                rectF,
                arcStartAngle.toFloat(),
                percentage.toFloat(),
                true,
                paint
            )     // Create arc

            // Update start angle
            arcStartAngle = arcStartAngle.plus(percentage)
            if (arcStartAngle >= GraphUtil.maxDegree) {
                arcStartAngle = arcStartAngle.minus(GraphUtil.maxDegree)
            }
        }
    }

    private fun createPaint(_arcColor: Int, _alpha: Int): Paint {
        val paint = Paint()
        if (isUsingArcColor) {
            paint.color = _arcColor
        } else {
            paint.color = Color.parseColor(graphColor)
            paint.alpha = _alpha
        }
        paint.strokeWidth = 3f
        return paint
    }

    companion object {

        private const val maxAlpha = 255
        private const val minAlpha = 20

        fun getColorTransparentList(n: Int): List<Int> {
            val alphaList = ArrayList<Int>()

            // Calculate color prefixes and add into the list
            val period = maxAlpha.minus(minAlpha).div(n)
            var trackingValue = maxAlpha
            for (i in 0 until n) {
                alphaList.add(trackingValue)
                trackingValue = trackingValue.minus(period)
            }
            return alphaList
        }
    }
}