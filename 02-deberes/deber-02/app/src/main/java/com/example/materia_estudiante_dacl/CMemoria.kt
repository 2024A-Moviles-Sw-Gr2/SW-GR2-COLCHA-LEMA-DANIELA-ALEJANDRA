package com.example.materia_estudiante_dacl

class CMemoria {

    companion object{
        val estudiantes = arrayListOf<AEstudianteEntity>()
        val materias = arrayListOf<BMateriasEntity>()

        init {
                initMaterias()
                initEstudiantes()
        }

        fun initMaterias(){
            materias.add(BMateriasEntity(1, "Física","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tortor nibh, congue vitae mauris et." ))
            materias.add(BMateriasEntity(2, "Química","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tortor nibh, congue vitae mauris et." ))
            materias.add(BMateriasEntity(3, "Álgebra","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tortor nibh, congue vitae mauris et." ))
            materias.add(BMateriasEntity(4, "Comunicación","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tortor nibh, congue vitae mauris et." ))
            materias.add(BMateriasEntity(5, "Cálculo","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tortor nibh, congue vitae mauris et." ))
        }


        fun initEstudiantes(){
            estudiantes.add(AEstudianteEntity(1, "Alejandra Colcha", 7, 8.92, arrayListOf(1, 3)))
            estudiantes.add(AEstudianteEntity(2, "David Alvarado", 5, 7.58, arrayListOf(2, 3)))
            estudiantes.add(AEstudianteEntity(3, "Alejandro Perez", 3, 6.92, arrayListOf(2,4)))
            estudiantes.add(AEstudianteEntity(4, "Melisa Jimenez", 4, 9.2, arrayListOf(4,5)))
            estudiantes.add(AEstudianteEntity(5, "Paula Cuji", 7, 8.5, arrayListOf(5)))
            estudiantes.add(AEstudianteEntity(6, "Paul Chiriboga", 6, 7.9, arrayListOf(1, 2)))
        }


        fun agregarMateriaEstudiante(id:Int, materia:BMateriasEntity): Unit{
            estudiantes.map { estudiante ->
                if (estudiante.id == id){
                    estudiante.materias!!.add(materia.id)
                }
            }
        }
    }
}