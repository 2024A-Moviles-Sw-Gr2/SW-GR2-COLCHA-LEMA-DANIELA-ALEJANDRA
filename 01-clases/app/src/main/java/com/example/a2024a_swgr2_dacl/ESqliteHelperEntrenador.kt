package com.example.a2024a_swgr2_dacl

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context? // this
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }
    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}
    fun crearEntrenador(
        nombre: String,
        descripcion:String
    ):Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "ENTRENADOR", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarEntrenadorFormulario(id:Int): Boolean{
        val conexionEscritura = writableDatabase

        //Consulta SQL para eliminar
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminar = conexionEscritura.delete(
            "ENTRENADOR",
            "id=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return if(resultadoEliminar.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String, descripcion: String, id: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("descripcion", descripcion)

        val parametrosActualizar = arrayOf(id.toString())
        val resultadosAtualizacion = conexionEscritura.update(
            "ENTRENADOR",
            valoresActualizar,
            "id=?",
            parametrosActualizar
        )
        conexionEscritura.close()
        return if(resultadosAtualizacion.toInt()==-1) false else true
    }
}