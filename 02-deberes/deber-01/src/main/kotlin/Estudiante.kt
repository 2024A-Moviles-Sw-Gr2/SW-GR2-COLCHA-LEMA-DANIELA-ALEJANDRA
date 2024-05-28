import java.io.File
import kotlin.collections.ArrayList

class Estudiante (
    protected val idEstudiante : Int,
    protected val nombreEstudiante: String,
    protected val promedioGeneral: Double,
    protected val semestre: Int,
    protected val materias: ArrayList<Materia>
) {

    init {
        this.idEstudiante
        this.nombreEstudiante
        this.promedioGeneral
        this.semestre
        this.materias
    }

    companion object{
        //MAC path
        val estudianteFileName ="/Users/colchita/Projects/SW-GR2-COLCHA-LEMA-DANIELA-ALEJANDRA/02-deberes/deber-01/src/main/estudiantes.txt"

        val fileEstudiante= File(estudianteFileName)

        fun crearEstudiante(estudiante: Estudiante): Unit{
            val record =
                "${estudiante.idEstudiante}, ${estudiante.nombreEstudiante}, ${estudiante.promedioGeneral}, "+
                        "${estudiante.semestre}, MATERIAS--${estudiante.materias}--"
            fileEstudiante.appendText("\n"+record)
            println("Estudiante registrado con exito! [${record}]")
        }

        fun verEstudiantes():Unit{
            println("-- ESTUDIANTES REGISTRADOS --")
            println(fileEstudiante.readText())
        }

        fun eliminarEstudiante(idEstudiante: Int): Unit {
            val lines = fileEstudiante.readLines()

            val updatedLines = lines.filter { !it.contains(idEstudiante.toString()) }

            fileEstudiante.writeText(updatedLines.joinToString("\n"))
            println("Estudiante ${idEstudiante} ha sido eliminado")
        }

        fun actualizarEstudiante(
            idEstudiante: Int,
            nombreEstudiante: String? = null,
            promedioGeneral: Double? = null,
            semestre: Int?=null,
            nombreMaterias: ArrayList<String>? = null
        )
                : Unit {

            val lines = fileEstudiante.readLines()
            var lineToUpdate: String = ""

            val updatedLines = lines.filter { estudiante: String ->
                val selected: Boolean = estudiante.contains(idEstudiante.toString())
                if (selected) {
                    lineToUpdate = estudiante
                }
                return@filter !selected
            }.toList()

            val materias = if(nombreMaterias != null) Materia.buscarMaterias(nombreMaterias) else null

            val estudiante = lineToUpdate.split(", ").toList()
            val estudianteUpdated = estudiante.map { atributo: String ->
                when (estudiante.indexOf(atributo)) {
                    (1) -> {
                        nombreEstudiante?: atributo
                    }

                    2 -> {
                        promedioGeneral ?: atributo
                    }

                    3 -> {
                        semestre ?: atributo
                    }

                    4 -> {
                        materias?:atributo
                    }

                    else -> {
                        atributo
                    }
                }

            }

            fileEstudiante.writeText(updatedLines.joinToString("\n"))
            fileEstudiante.appendText("\n${estudianteUpdated.toString().trimStart('[').trimEnd(']')}")
        }
    }
}