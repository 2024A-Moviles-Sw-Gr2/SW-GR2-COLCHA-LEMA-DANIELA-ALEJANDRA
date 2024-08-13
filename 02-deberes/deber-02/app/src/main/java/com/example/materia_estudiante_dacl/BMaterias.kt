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
import com.google.android.material.snackbar.Snackbar

class BMaterias : AppCompatActivity() {

    //DEFINICIÓN INTENT
    val callbackFormularioMateria=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){

                    val listView = findViewById<ListView>(R.id.list_materias)
                    val adaptador = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        EDatabase.tables!!.getMaterias()
                    )
                    listView.adapter = adaptador
                    adaptador.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmaterias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_materias)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Inicializar base de datos
        EDatabase.tables = ESqliteHelper(
            this
        )

        //Colocar datos en Lista
        val listView = findViewById<ListView>(R.id.list_materias)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EDatabase.tables?.getMaterias() ?: emptyList()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        //Uso de Botones
        val btnCrearMateria = findViewById<Button>(
            R.id.id_btn_crear_materia
        )
        btnCrearMateria.setOnClickListener {
            crearMateria(adaptador)
        }
        registerForContextMenu(listView)

    }

    private fun crearMateria(
        adapter: ArrayAdapter<BMateriasEntity>
    ){
        val intentCrear = Intent(
            this,
            DEditarMaterias::class.java
        )

        callbackFormularioMateria.launch(intentCrear)

        adapter.notifyDataSetChanged()
    }

    //Funciones para el menu
    var index = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //opciones menu
        menuInflater.inflate(R.menu.menu_materia, menu)

        //opcion seleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        index = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.id_mi_editar_materia->{

                val intentEditar = Intent(
                    this,
                    DEditarMaterias::class.java
                )
                val materias = EDatabase.tables!!.getMaterias()

                intentEditar.putExtra("materia", materias[index])
                callbackFormularioMateria.launch(intentEditar)

                return true
            }
            R.id.id_mi_eliminar_materia->{
                abrirDialogo(index)
                return true
            }
            R.id.id_ver_estudiantes->{
                val materias = EDatabase.tables!!.getMaterias()
                val intent = Intent(this,AEstudiantes::class.java)
                intent.putExtra("materia", materias[index].nombre)
                intent.putExtra("id", materias[index].id)
                startActivity(intent)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogo(index:Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar materia?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                EDatabase.tables!!.eliminarMateria(index+1)

                val listView = findViewById<ListView>(R.id.list_materias)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    EDatabase.tables!!.getMaterias()
                )
                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)

        builder.create().show()
    }

}