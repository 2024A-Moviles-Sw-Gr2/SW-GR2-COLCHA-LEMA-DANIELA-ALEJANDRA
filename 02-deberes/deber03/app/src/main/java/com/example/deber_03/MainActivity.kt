package com.example.deber_03

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarCategorias()
        inicializarPlaylists()

        val btnBuscar = findViewById<ImageView>(R.id.btn_buscar)
        btnBuscar.setOnClickListener {
            irActividad(CBuscar::class.java)
        }

    }

    fun inicializarCategorias(){
        val categoriesList : ArrayList<String> = arrayListOf("Playlist", "Álbumes", "Podcast", "Artistas", "Descargados")

        val recyclerView = findViewById<RecyclerView>(R.id.rv_playlist_categories)
        val adaptador = AAdapterCategory(categoriesList)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adaptador.notifyDataSetChanged()
    }

    fun inicializarPlaylists(){

        val playlistList: ArrayList<BPlaylist> = arrayListOf(
            BPlaylist(R.drawable.megusta, "Tus me gusta", "Playlist · 637 canciones"),
            BPlaylist(R.drawable.relax, "Relax", "Playlist · Alejandra Colcha"),
            BPlaylist(R.drawable.sisomos, "Si Somos", "Podcast · Si Somos"),
            BPlaylist(R.drawable.runrun, "RunRun", "Playlist · Alejandra Colcha"),
            BPlaylist(R.drawable.guts, "Guts", "Álbum · Olivia Rodrigo"),
            BPlaylist(R.drawable.numeroculto, "Número Oculto", "Podcast · Spotify Studios"),
            BPlaylist(R.drawable.aries, "Aries", "Álbum · Paola Navarrete"),
            BPlaylist(R.drawable.fusion, "David + Alejandra", "Playlist · Spotify")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_playlists)
        val adaptador = BAdapterPlaylist(playlistList)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad( clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}