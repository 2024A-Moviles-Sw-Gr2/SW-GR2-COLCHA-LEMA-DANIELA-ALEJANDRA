package com.example.deber_03

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class CBuscar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cbuscar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.buscar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val exploreItems = arrayListOf(
            CExploreItem(R.drawable.musica, R.drawable.podcasts),
            CExploreItem(R.drawable.envivo, R.drawable.parati),
            CExploreItem(R.drawable.lanzamientos, R.drawable.rankings),
            CExploreItem(R.drawable.pop, R.drawable.hiphop ),
            CExploreItem(R.drawable.rock, R.drawable.ranking_podcasts )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_explore_items)
        val adaptador = CAdapterBuscar(exploreItems)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()


        val btnLibrary= findViewById<ImageView>(R.id.btn_library)
        btnLibrary.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}