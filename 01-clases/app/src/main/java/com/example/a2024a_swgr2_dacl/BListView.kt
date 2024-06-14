package com.example.a2024a_swgr2_dacl

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class BListView : AppCompatActivity() {

    val arreglo = BBaseDatosMemoria.arregloEntrenador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_blist_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_list_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged() //es lo más importante para actualizar la UI - no olvidar
        val botonAnadirListView = findViewById<Button>(R.id.btn_add_list_view)

        botonAnadirListView.setOnClickListener{
            addEntrenador(adaptador)
        }
    }

    var posicionItemSeleccionado = 1
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        //llenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        //Obtener id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar -> {
                mostrarSnackbar("Editar $posicionItemSeleccionado")
                return true
            }
            R.id.mi_eliminar ->{
                mostrarSnackbar("Eliminar $posicionItemSeleccionado")
                abrirDialogo() //Nueva linea
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){}

    fun addEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(
            BEntrenador(4, "Wendy", "d@d.com")
        )
        adaptador.notifyDataSetChanged()
    }

    fun mostrarSnackbar(texto: String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            texto,
            Snackbar.LENGTH_INDEFINITE)
        snack.show()
    }
}