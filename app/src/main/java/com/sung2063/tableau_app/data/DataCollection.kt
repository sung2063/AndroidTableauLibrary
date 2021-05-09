package com.sung2063.tableau_app.data

import android.content.Context
import android.graphics.Color
import com.sung2063.tableau_app.R
import com.sung2063.tableau_app.model.MainOptionModel

class DataCollection {

    companion object {

        fun getMainOptionData(context: Context): List<MainOptionModel> {
            val mainOptionList = ArrayList<MainOptionModel>()
            mainOptionList.add(
                MainOptionModel(
                    -1,
                    R.drawable.ic_graph,
                    R.color.menuColor1,
                    context.getString(R.string.app_menu1),
                    context.getString(R.string.app_menu1_description),
                    Color.BLACK
                )
            )
            mainOptionList.add(
                MainOptionModel(
                    -1,
                    R.drawable.ic_progress_bar,
                    R.color.menuColor2,
                    context.getString(R.string.app_menu2),
                    context.getString(R.string.app_menu2_description),
                    Color.BLACK
                )
            )
            mainOptionList.add(
                MainOptionModel(
                    -1,
                    R.drawable.ic_progress_bar,
                    R.color.menuColor3,
                    context.getString(R.string.app_menu3),
                    context.getString(R.string.app_menu3_description),
                    Color.BLACK
                )
            )
            mainOptionList.add(
                MainOptionModel(
                    Color.BLACK,
                    R.drawable.ic_github,
                    R.color.menuColor5,
                    context.getString(R.string.app_menu4),
                    context.getString(R.string.app_menu4_description),
                    Color.WHITE
                )
            )
            return mainOptionList
        }
    }
}