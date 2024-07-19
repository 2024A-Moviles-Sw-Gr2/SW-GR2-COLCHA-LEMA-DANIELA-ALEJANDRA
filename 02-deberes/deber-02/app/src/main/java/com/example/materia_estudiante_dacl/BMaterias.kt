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

    var materias = arrayListOf<BMateriasEntity>()
    var idEst = 1

    //DEFINICIÓN INTENT
    val callbackFormularioMateria=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    var msg = ""
                    val materiaModificada = result.data!!.getParcelableExtra<BMateriasEntity>("materiaModificada")
                    val materiaNueva = result.data!!.getParcelableExtra<BMateriasEntity>("materiaNueva")

                    if (materiaModificada!=null){
                        materias.removeAt(index)
                        materias.add(index, materiaModificada)
                        CMemoria.actualizarMateria(materiaModificada)

                    }else if(materiaNueva!= null){
                        materias.add(materiaNueva)
                        CMemoria.materias.add(materiaNueva)
                        CMemoria.agregarMateriaEstudiante(idEst, materiaNueva)
                    }

                    val listView = findViewById<ListView>(R.id.list_materias)
                    val adaptador = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        materias
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

        val estudiante = intent.getParcelableExtra<AEstudianteEntity>("estudiante")

        if (estudiante != null) {
            findViewById<TextView>(R.id.id_estudiante_nom).setText(estudiante.nombre)
            val materiasEst = estudiante.materias

            materias = CMemoria.materias.filter { materiasEst!!.contains(it.id) } as ArrayList<BMateriasEntity>

            idEst = estudiante.id
        }

        //Colocar datos en Lista
        val listView = findViewById<ListView>(R.id.list_materias)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            materias
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

        val btnVolver = findViewById<Button>(
            R.id.btn_back
        )

        btnVolver.setOnClickListener{
            val intent = Intent(this,AEstudiantes::class.java)
            startActivity(intent)
        }

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

                intentEditar.putExtra("materia", materias.get(index))
                callbackFormularioMateria.launch(intentEditar)

                return true
            }
            R.id.id_mi_eliminar_materia->{

                val listView = findViewById<ListView>(R.id.list_materias)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    materias
                )
                listView.adapter = adaptador
                abrirDialogo(index, adaptador)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogo(index:Int,
                             adapter: ArrayAdapter<BMateriasEntity>
    ){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar materia?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                materias.removeAt(index)
                val materiaEliminada = materias[index].id
                CMemoria.eliminarMateriaEst(idEst, materiaEliminada)
                adapter.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)

        builder.create().show()
    }

}