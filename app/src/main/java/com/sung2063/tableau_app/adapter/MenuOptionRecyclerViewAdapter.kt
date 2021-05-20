package com.sung2063.tableau_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_app.R
import com.sung2063.tableau_app.model.MainOptionModel

class MenuOptionRecyclerViewAdapter(
    private val context: Context,
    private val optionList: List<MainOptionModel>
) : RecyclerView.Adapter<MenuOptionRecyclerViewAdapter.ViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var menuOptionListener: EventListener

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var clContainer: ConstraintLayout = itemView.findViewById(R.id.cl_container)
        var ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_description)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuOptionListener.onMenuClicked(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.menu_option_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mainOptionData = optionList[position]
        val backgroundId = mainOptionData.backgroundId
        if (backgroundId != -1) {
            holder.clContainer.setBackgroundColor(backgroundId)
        }
        holder.ivIcon.setImageResource(mainOptionData.iconId)
        DrawableCompat.setTint(
            holder.ivIcon.drawable,
            ContextCompat.getColor(context, mainOptionData.iconColor)
        )
        holder.tvTitle.text = mainOptionData.title
        holder.tvTitle.setTextColor(mainOptionData.textColorId)
        holder.tvDescription.text = mainOptionData.description
        holder.tvDescription.setTextColor(mainOptionData.textColorId)
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    fun setListener(listener: EventListener) {
        this.menuOptionListener = listener
    }

    interface EventListener {
        fun onMenuClicked(position: Int)
    }

}