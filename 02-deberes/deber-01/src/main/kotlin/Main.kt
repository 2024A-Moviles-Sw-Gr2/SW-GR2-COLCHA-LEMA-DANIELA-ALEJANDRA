
fun main() {

    // MATERIA ---------
    //Crear materia
    Materia.crearMateria(Materia(idMateria = 1001,
                            nombreMateria = "Fisica",
                            obligatoria = true,
                            promedio =  6.1))

    //Visualizar Materias
    Materia.verMaterias()

    //Eliminar Materia
    Materia.eliminarMateria("Fisica")

    //Actualizar materia
    Materia.actualizarMateria(idMateria = 1005, promedio = 8.5)

    // ESTUDIANTE--------------

    //Crear Estudiante
    val materiasEstudiante = arrayListOf("Algebra","Quimica")

    Estudiante.crearEstudiante(Estudiante(2005,
                            "Miguel Santos",
                                8.5, 5,
                            Materia.buscarMaterias(materiasEstudiante))
    )

    //Ver Estudiantes
    Estudiante.verEstudiantes()

    //Eliminar Estudiante
    Estudiante.eliminarEstudiante(2003)

    //Actualizar Estudiante
    Estudiante.actualizarEstudiante(2002, nombreEstudiante = "Abigail Herrera")
    Estudiante.actualizarEstudiante(2004, promedioGeneral = 6.2)
    Estudiante.verEstudiantes()
}


