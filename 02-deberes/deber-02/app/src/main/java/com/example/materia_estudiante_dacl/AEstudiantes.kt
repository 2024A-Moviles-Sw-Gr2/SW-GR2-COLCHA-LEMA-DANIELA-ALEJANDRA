package com.example.materia_estudiante_dacl

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.E

class AEstudiantes : AppCompatActivity() {

    var id_materia = 0

    //DEFINICIÓN INTENT
    val callbackFormularioEstudiante=
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){

                    val listView = findViewById<ListView>(R.id.list_est)
                    val adaptador = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        EDatabase.tables!!.getEstudiantes(id_materia)
                    )
                    listView.adapter = adaptador
                    adaptador.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estudiantes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_estudiantes)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val materia = intent.getStringExtra("materia")
        id_materia = intent.getIntExtra("id", 0)

        if (materia != null) {
            findViewById<TextView>(R.id.id_nombre_materia).setText(materia)
        }


        //Colocar datos en Lista
        val listView = findViewById<ListView>(R.id.list_est)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EDatabase.tables!!.getEstudiantes(id_materia)
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        //Uso de Botones
        val btnCrearEstudiante = findViewById<Button>(
            R.id.id_btn_crear_est
        )
        btnCrearEstudiante.setOnClickListener {
            crearEstudiante()
        }
        registerForContextMenu(listView)

    }

    private fun crearEstudiante(){
        val intentCrear = Intent(
            this,
            DEditarEstudiante::class.java
        )

        intent.putExtra("id_materia", id_materia)
        callbackFormularioEstudiante.launch(intentCrear)
    }

    //Funciones para el menu
    var index = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //opciones menu
        menuInflater.inflate(R.menu.menu_est, menu)

        //opcion seleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        index = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.id_mi_editar_est->{

                val intentEditar = Intent(
                    this,
                    DEditarEstudiante::class.java
                )
                val estudiantes = EDatabase.tables!!.getEstudiantes(id_materia)
                intentEditar.putExtra("estudiante", estudiantes[index])
                callbackFormularioEstudiante.launch(intentEditar)

                return true
            }
            R.id.id_mi_eliminar_est->{
                abrirDialogo(index)
                return true
            }
            R.id.id_mi_est_ubicacion->{
                val ubicacion = EDatabase.tables!!.getEstudiantes(id_materia)[index].ubicacion

                val intent = Intent(this,FMapActivity::class.java)
                intent.putExtra("ubicacion", ubicacion)
                startActivity(intent)

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogo(index:Int ){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                EDatabase.tables!!.eliminarEstudiante(index+1)
                val listView = findViewById<ListView>(R.id.list_est)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    EDatabase.tables!!.getEstudiantes(id_materia)
                )
                listView.adapter = adaptador

                adaptador.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)

        builder.create().show()
    }


}