package com.example.materia_estudiante_dacl

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class DEditarEstudiante : AppCompatActivity() {

    var id : Int= 1
    var materias : MutableList<Int>? = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deditar_estudiante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_edit_estudiante)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var estudiante = intent.getParcelableExtra<AEstudianteEntity>("estudiante")

        if (estudiante != null) {
            findViewById<EditText>(R.id.input_nombre_est).setText(estudiante.nombre)
            findViewById<EditText>(R.id.input_semestre).setText(estudiante.semestre.toString())
            findViewById<EditText>(R.id.input_promedio).setText(estudiante.promedio.toString())
            id = estudiante.id
            materias = estudiante.materias
        }


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

        val nombre = findViewById<EditText>(R.id.input_nombre_est).text.toString()
        val semestre = findViewById<EditText>(R.id.input_semestre).text.toString().toInt()
        val promedio = findViewById<EditText>(R.id.input_promedio).text.toString().toDouble()

        val estudianteModificado = AEstudianteEntity(id, nombre, semestre, promedio, materias )

        response.putExtra("estudianteModificado", estudianteModificado)

        setResult(RESULT_OK, response)
        finish()

    }

    private fun responseCrear(){

        val response = Intent()

        val nombre = findViewById<EditText>(R.id.input_nombre_est).text.toString()
        val semestre = findViewById<EditText>(R.id.input_semestre).text.toString().toInt()
        val promedio = findViewById<EditText>(R.id.input_promedio).text.toString().toDouble()

        val estudiante = AEstudianteEntity(CMemoria.idNuevoEstudiante(), nombre, semestre, promedio, materias )

        response.putExtra("estudianteNuevo", estudiante)

        setResult(RESULT_OK, response)
        finish()

    }
}