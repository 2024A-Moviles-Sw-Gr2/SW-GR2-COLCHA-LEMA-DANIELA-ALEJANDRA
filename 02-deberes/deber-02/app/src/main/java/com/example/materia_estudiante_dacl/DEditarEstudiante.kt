package com.example.materia_estudiante_dacl

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class DEditarEstudiante : AppCompatActivity() {

    var id : Int= 1
    var ubicacionSpinner = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deditar_estudiante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_edit_estudiante)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.sp_ubiaciones)
        ArrayAdapter.createFromResource(
            this,
            R.array.ubicaciones,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                ubicacionSpinner = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada
            }
        }

        var estudiante = intent.getParcelableExtra<AEstudianteEntity>("estudiante")

        if (estudiante != null) {
            findViewById<EditText>(R.id.input_nombre_est).setText(estudiante.nombre)
            findViewById<EditText>(R.id.input_semestre).setText(estudiante.semestre.toString())
            findViewById<EditText>(R.id.input_promedio).setText(estudiante.promedio.toString())
            findViewById<EditText>(R.id.input_materia).setText(estudiante.materia.toString())
            id = estudiante.id

            val ubicacionesArray = resources.getStringArray(R.array.ubicaciones)
            val posicionInicial = ubicacionesArray.indexOf(estudiante.ubicacion)
            spinner.setSelection(posicionInicial)
            ubicacionSpinner = estudiante.ubicacion
        }

        val materia = intent.getIntExtra("id_materia", 1)
        findViewById<EditText>(R.id.input_materia).setText(materia.toString())

        val guardarBtn = findViewById<Button>(R.id.btn_save)
        guardarBtn.setOnClickListener{
            if(estudiante!= null){
                responseEditar()
            }else{
                responseCrear()
            }
        }
    }

    private fun responseEditar(){

        val response = Intent()

        EDatabase.tables!!.actualizarEstudiante(
            id,
            findViewById<EditText>(R.id.input_nombre_est).text.toString(),
            findViewById<EditText>(R.id.input_semestre).text.toString().toInt(),
            findViewById<EditText>(R.id.input_promedio).text.toString().toDouble(),
            findViewById<EditText>(R.id.input_materia).text.toString().toInt(),
            ubicacionSpinner
        )

        setResult(RESULT_OK, response)
        finish()

    }

    private fun responseCrear(){

        val response = Intent()

        EDatabase.tables!!.crearEstudiante(
            findViewById<EditText>(R.id.input_nombre_est).text.toString(),
            findViewById<EditText>(R.id.input_semestre).text.toString().toInt(),
            findViewById<EditText>(R.id.input_promedio).text.toString().toDouble(),
            findViewById<EditText>(R.id.input_materia).text.toString().toInt(),
            ubicacionSpinner
        )

        setResult(RESULT_OK, response)
        finish()

    }
}