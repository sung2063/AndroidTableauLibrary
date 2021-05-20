package com.sung2063.tableau_library.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_library.R
import com.sung2063.tableau_library.graph.adapter.PieGraphDataAdapter
import com.sung2063.tableau_library.graph.handler.PieGraphHandler
import com.sung2063.tableau_library.graph.model.PieGraphModel
import com.sung2063.tableau_library.graph.util.GraphUtil

class PieGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    // UI objects
    private lateinit var rvDataList: RecyclerView

    // Data variables
    private var isUsingArcColor: Boolean
    private var graphColor: String? = "#023047"
    private lateinit var dataList: ArrayList<PieGraphModel>
    private var alphaList: List<Int>? = null

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PieGraphView, 0, 0)
        isUsingArcColor = typedArray.getBoolean(R.styleable.PieGraphView_useArcColor, false)
        graphColor = typedArray.getString(R.styleable.PieGraphView_graphColor)
        setWillNotDraw(false)
    }

    fun setHandler(_handler: PieGraphHandler) {
        val view = View.inflate(context, R.layout.pie_graph_layout, this)
        dataList = _handler.dataList
        dataList.sortByDescending { it.value }      // Sort by highest first
        alphaList = if (!isUsingArcColor) getColorTransparentList(dataList.size) else null

        // Setup Data List
        rvDataList = view.findViewById(R.id.rv_pie_graph_data_list)
        val pieGraphDataAdapter = PieGraphDataAdapter(dataList, isUsingArcColor, alphaList)
        rvDataList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvDataList.adapter = pieGraphDataAdapter
    }

    override fun dispatchDraw(_canvas: Canvas?) {
        super.dispatchDraw(_canvas)

        // Rectangle object
        val pieGraphRectF = RectF(200f, 200f, 850f, 850f)

        val sum = dataList.sumByDouble { it.value.toDouble() }       // Get sum

        var arcStartAngle = 270.0
        for (i in 0 until dataList.size) {

            val percentage = (dataList[i].value.div(sum)).times(GraphUtil.maxDegree)
            val paint = if (isUsingArcColor) {
                createArcStyleByColor(Color.parseColor(dataList[i].color))
            } else {
                createStyleArc(alphaList?.get(i))
            }
            _canvas?.drawArc(
                pieGraphRectF,
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

        rvDataList.y = pieGraphRectF.bottom + 100
        invalidate()

    }

    private fun createStyleArc(_alpha: Int?): Paint {
        val paint = Paint()
        paint.color = Color.parseColor(graphColor)
        _alpha.let { paint.alpha = it!! }
        paint.strokeWidth = 3f
        return paint
    }

    private fun createArcStyleByColor(_arcColor: Int): Paint {
        val paint = Paint()
        paint.color = _arcColor
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