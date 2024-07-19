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
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class AEstudiantes : AppCompatActivity() {


    //DEFINICIÓN INTENT
    val callbackFormularioEstudiante=
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    val estudianteModificado = result.data!!.getParcelableExtra<AEstudianteEntity>("estudianteModificado")
                    val estudianteNuevo = result.data!!.getParcelableExtra<AEstudianteEntity>("estudianteNuevo")

                    if (estudianteModificado!= null){

                        CMemoria.estudiantes.removeAt(index)
                        CMemoria.estudiantes.add(index, estudianteModificado)

                    }else if(estudianteNuevo!= null){
                        CMemoria.estudiantes.add(estudianteNuevo)
                    }

                    val listView = findViewById<ListView>(R.id.list_est)
                    val adaptador = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        CMemoria.estudiantes
                    )
                    listView.adapter = adaptador
                    adaptador.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_estudiantes)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Colocar datos en Lista
        val listView = findViewById<ListView>(R.id.list_est)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            CMemoria.estudiantes
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

                intentEditar.putExtra("estudiante", CMemoria.estudiantes.get(index))
                callbackFormularioEstudiante.launch(intentEditar)


                return true
            }
            R.id.id_mi_eliminar_est->{

                val listView = findViewById<ListView>(R.id.list_est)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    CMemoria.estudiantes
                )
                listView.adapter = adaptador
                abrirDialogo(index, adaptador)
                return true
            }
            R.id.id_mi_ver_materias->{
                val intent = Intent(this,BMaterias::class.java)
                intent.putExtra("estudiante", CMemoria.estudiantes.get(index))
                startActivity(intent)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogo(index:Int,
                             adapter: ArrayAdapter<AEstudianteEntity>){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                CMemoria.estudiantes.removeAt(index)
                adapter.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)

        builder.create().show()
    }


}