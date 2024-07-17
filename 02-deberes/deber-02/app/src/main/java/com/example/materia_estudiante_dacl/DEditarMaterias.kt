package com.example.materia_estudiante_dacl

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DEditarMaterias : AppCompatActivity() {

    var id : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deditar_materias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_editar_materia)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var materia = intent.getParcelableExtra<BMateriasEntity>("materia")

        if (materia != null) {
            findViewById<EditText>(R.id.input_nombre_mat).setText(materia!!.nombre)
            findViewById<EditText>(R.id.input_descripcion).setText(materia!!.descripcion)
            id = materia.id
        }

        val guardarBtn = findViewById<Button>(R.id.btn_save_mat)
        guardarBtn.setOnClickListener{response()}
    }

    fun response(){

        val response = Intent()

        val nombre = findViewById<EditText>(R.id.input_nombre_mat).text.toString()
        val descripcion = findViewById<EditText>(R.id.input_descripcion).text.toString()

        val materiaModificada = BMateriasEntity(id, nombre, descripcion)

        response.putExtra("materiaModificada", materiaModificada)

        setResult(RESULT_OK, response)
        finish()

    }
}