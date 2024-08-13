package com.example.materia_estudiante_dacl

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelper (
    contexto: Context?
): SQLiteOpenHelper (
    contexto,
    "app",
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val crearTablaMaterias= """
            CREATE TABLE MATERIA(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(100),
                descripcion VARCHAR(100)
            );
        """.trimIndent()

        val crearTablaEstudiantes = """
            CREATE TABLE ESTUDIANTE(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(100),
                semestre INTEGER,
                materia INTEGER,
                promedio DOUBLE,
                ubicacion VARCHAR(50),
                FOREIGN KEY (materia) REFERENCES MATERIA(id) ON DELETE SET NULL

            );
        """.trimIndent()
        db?.execSQL(crearTablaMaterias)
        db?.execSQL(crearTablaEstudiantes)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearEstudiante(
        nombre: String,
        semestre: Int,
        promedio: Double,
        materia: Int,
        ubicacion: String
    ):Boolean {
        val writeDB = writableDatabase

        val parametros = ContentValues()
        parametros.put("nombre", nombre)
        parametros.put("semestre", semestre)
        parametros.put("materia", materia)
        parametros.put("promedio", promedio)
        parametros.put("ubicacion", ubicacion)

        val resultadoGuardar = writeDB
            .insert(
                "ESTUDIANTE",
                null,
                parametros
            )
        writeDB.close()
        return resultadoGuardar.toInt() != -1
    }

    fun crearMateria(
        nombre: String,
        descripcion: String
    ): Boolean {
        val writeDB = writableDatabase

        val parametros = ContentValues()
        parametros.put("nombre", nombre)
        parametros.put("descripcion", descripcion)

        val resultadoGuardar = writeDB
            .insert(
                "MATERIA",
                null,
                parametros
            )
        writeDB.close()
        return resultadoGuardar.toInt() != -1
    }


    fun getEstudiantes(materia: Int): ArrayList<AEstudianteEntity> {
        val lectureDB = readableDatabase

        val queryScript = """
            SELECT * FROM ESTUDIANTE
            WHERE materia = ?
        """.trimIndent()

        val queryResult = lectureDB.rawQuery(
            queryScript,
            arrayOf(materia.toString())
        )

        val response = arrayListOf<AEstudianteEntity>()

        if(queryResult.moveToFirst()) {
            do {
                response.add(
                    AEstudianteEntity(
                        queryResult.getInt(0),
                        queryResult.getString(1),
                        queryResult.getInt(2),
                        queryResult.getDouble(3),
                        queryResult.getInt(4),
                        queryResult.getString(5)
                    )
                )
            } while(queryResult.moveToNext())
        }
        queryResult.close()
        lectureDB.close()

        return response
    }

    fun getMaterias(): ArrayList<BMateriasEntity> {
        val lectureDB = readableDatabase

        val queryScript = """
            SELECT * FROM MATERIA
        """.trimIndent()

        val queryResult = lectureDB.rawQuery(
            queryScript,
            emptyArray()
        )
        val response = arrayListOf<BMateriasEntity>()

        if(queryResult.moveToFirst()) {
            do {
                response.add(
                    BMateriasEntity(
                        queryResult.getInt(0),
                        queryResult.getString(1),
                        queryResult.getString(2)
                    )
                )
            } while(queryResult.moveToNext())
        }
        queryResult.close()
        lectureDB.close()

        return response
    }


    fun eliminarEstudiante(id:Int): Boolean{
        val conexionEscritura = writableDatabase

        //Consulta SQL para eliminar
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminar = conexionEscritura.delete(
            "ESTUDIANTE",
            "id=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return resultadoEliminar.toInt() != -1
    }

    fun eliminarMateria(id:Int): Boolean{
        val conexionEscritura = writableDatabase

        //Consulta SQL para eliminar
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminar = conexionEscritura.delete(
            "MATERIA",
            "id=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return resultadoEliminar.toInt() != -1
    }

    fun actualizarMateria(
        id: Int,
        nombre: String,
        descripcion: String
    ):Boolean{
        val writeDB = writableDatabase

        val parametros = ContentValues()
        parametros.put("nombre", nombre)
        parametros.put("descripcion", descripcion)

        val id_query = arrayOf(id.toString())
        val resultadosAtualizacion = writeDB.update(
            "MATERIA",
            parametros,
            "id=?",
            id_query
        )
        writeDB.close()
        return resultadosAtualizacion.toInt() != -1
    }

    fun actualizarEstudiante(
        id: Int,
        nombre: String,
        semestre: Int,
        promedio: Double,
        materia: Int,
        ubicacion: String
    ):Boolean{
        val writeDB = writableDatabase

        val parametros = ContentValues()
        parametros.put("nombre", nombre)
        parametros.put("semestre", semestre)
        parametros.put("materia", materia)
        parametros.put("promedio", promedio)
        parametros.put("ubicacion", ubicacion)

        val id_query = arrayOf(id.toString())
        val resultadosAtualizacion = writeDB.update(
            "ESTUDIANTE",
            parametros,
            "id=?",
            id_query
        )
        writeDB.close()
        return resultadosAtualizacion.toInt() != -1
    }

}