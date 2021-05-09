package com.sung2063.tableau_app

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sung2063.tableau_app.adapter.MenuOptionRecyclerViewAdapter
import com.sung2063.tableau_app.data.DataCollection

class MainActivity : AppCompatActivity(), MenuOptionRecyclerViewAdapter.EventListener {

    private lateinit var btSponsorship: Button
    private lateinit var rvOptions: RecyclerView
    private lateinit var menuOptionAdapter: MenuOptionRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSponsorship = findViewById(R.id.bt_sponsorship)
        rvOptions = findViewById(R.id.rv_options)

        // Setup Main Menu
        val mainOptionList = DataCollection.getMainOptionData(applicationContext)
        rvOptions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        menuOptionAdapter = MenuOptionRecyclerViewAdapter(this, mainOptionList)
        menuOptionAdapter.setListener(this)
        rvOptions.adapter = menuOptionAdapter

        btSponsorship.paintFlags = btSponsorship.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        // Responds
        btSponsorship.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/sponsors/sung2063")
            startActivity(intent)
        }
    }

    override fun onMenuClicked(position: Int) {
        var intent: Intent? = null
        when (position) {
            NavigateConstant.GRAPH_PIE_CHART -> intent = Intent(this, PieBarGraphActivity::class.java)
            NavigateConstant.DOT_PROGRESS -> intent = Intent(this, DotProgressActivity::class.java)
            NavigateConstant.LINEAR_PROGRESS -> intent = Intent(this, LinearProgressActivity::class.java)
            NavigateConstant.GIT_HUB_WEB_PAGE -> {
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://github.com/sung2063")
            }
        }

        intent?.let {
            startActivity(intent)
        }
    }

    object NavigateConstant {
        const val GRAPH_PIE_CHART = 0
        const val DOT_PROGRESS = 1
        const val LINEAR_PROGRESS = 2
        const val GIT_HUB_WEB_PAGE = 3
    }
}