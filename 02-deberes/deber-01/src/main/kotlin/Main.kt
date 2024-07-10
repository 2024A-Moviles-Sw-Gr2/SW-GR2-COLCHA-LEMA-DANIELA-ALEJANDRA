
fun main() {

    // MATERIA ---------
    //Crear materia
    Materia.run {
        crearMateria(Materia(idMateria = 1001,
                            nombreMateria = "Fisica",
                            obligatoria = true,
                            promedio =  6.1))

        //Visualizar Materias
        verMaterias()
    }

    println("<<<<<<<<<<<<<<<< Eliminar Materia >>>>>>>>>>>>>>>>")
    //Eliminar Materia
    Materia.run {
        eliminarMateria("Fisica")
        verMaterias()
    }

    println("<<<<<<<<<<<<<<<<<Actulizar Materia>>>>>>>>>>>>>>>>>")
    //Actualizar materia
    Materia.run {
        actualizarMateria(idMateria = 1005, promedio = 5.1)
        verMaterias()
    }

    // ESTUDIANTE--------------
    //Crear Estudiante
    val materiasEstudiante = arrayListOf("Quimica")

    Estudiante.run {
        crearEstudiante(Estudiante(2006,
                            "Catalina Perez",
                                9.2, 6,
                            Materia.buscarMaterias(materiasEstudiante)))
        //Ver Estudiantes
        verEstudiantes()
    }

    println("<<<<<<<<<<<<<<<< Eliminar Estudiante >>>>>>>>>>>>>>>>")
    //Eliminar Estudiante
    Estudiante.run {
        eliminarEstudiante(2006)
        verEstudiantes()
    }

    println("<<<<<<<<<<<<<<<< Actualizar Estudiante >>>>>>>>>>>>>>>>")
    //Actualizar Estudiante
    Estudiante.run {
        actualizarEstudiante(2002, nombreEstudiante = "Martina Revelo")
        actualizarEstudiante(2004, promedioGeneral = 10.0)
        verEstudiantes()
    }

}


