import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Materia(
    protected val fechaCreacion: Date = Date(),
    protected val idMateria: Int,
    protected val nombreMateria: String,
    protected val obligatoria: Boolean,
    protected val promedio: Double
) {
    init {
        this.fechaCreacion
        this.idMateria
        this.nombreMateria
        this.obligatoria
        this.promedio
    }


    companion object {

        //MAC path
        val materiaFileName ="/Users/colchita/Projects/SW-GR2-COLCHA-LEMA-DANIELA-ALEJANDRA/02-deberes/deber-01/src/main/materias.txt"

        val fileMateria = File(materiaFileName)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        fun crearMateria(materia: Materia): Unit {
            val record =
                "${dateFormat.format(materia.fechaCreacion)}, ${materia.idMateria}, ${materia.nombreMateria}, " +
                        " ${materia.obligatoria}, ${materia.promedio}"
            fileMateria.appendText("\n" + record)
            println("Registrado con éxito! [${record}]")
        }

        fun verMaterias(): Unit {
            println("-- MATERIAS REGISTRADAS --")
            println(fileMateria.readText())
        }

        fun eliminarMateria(nombreMateria: String): Unit {
            val lines = fileMateria.readLines()

            val updatedLines = lines.filter { !it.contains(nombreMateria) }

            fileMateria.writeText(updatedLines.joinToString("\n"))
            println("La materia ${nombreMateria} ha sido eliminada")
        }

        fun actualizarMateria(
            nombreMateria: String? = null,
            idMateria: Int,
            obligatoria: Boolean? = null,
            promedio: Double? = null
        )
                : Unit {
            val lines = fileMateria.readLines()
            var lineToUpdate: String = ""

            val updatedLines = lines.filter { materia: String ->
                val selected: Boolean = materia.contains(idMateria.toString())
                if (selected) {
                    lineToUpdate = materia
                }
                return@filter !selected
            }.toList()

            val materia = lineToUpdate.split(", ").toList()
            val materiaUpdated = materia.map { atributo: String ->
                when (materia.indexOf(atributo)) {
                    (0) -> {
                        dateFormat.format(Date())
                    }

                    2 -> {
                        nombreMateria ?: atributo
                    }

                    3 -> {
                        obligatoria ?: atributo
                    }

                    4 -> {
                        promedio ?: atributo
                    }

                    else -> {
                        atributo
                    }
                }

            }

            fileMateria.writeText(updatedLines.joinToString("\n"))
            fileMateria.appendText("\n${materiaUpdated.toString().trimStart('[').trimEnd(']')}")
        }

        fun buscarMaterias(nombres:ArrayList<String>):ArrayList<Materia>{
            val materias =  ArrayList<Materia>()
            val lineas = fileMateria.readLines()
            lineas.forEach { linea: String ->
                val materia = linea.split(", ").toList()
                nombres.forEach { nombre: String ->
                    if (nombre == materia[2]){
                        materias.add(
                            Materia(
                                idMateria = materia[1].toInt(),
                                nombreMateria = materia[2],
                                obligatoria = materia[3].toBoolean(),
                                promedio = materia[4].toDouble()
                            )
                        )
                    }
                }
            }

            return materias
        }
    }

    override fun toString(): String {
        return "{ id: $idMateria," +
                " nombre: $nombreMateria', obligatoria:$obligatoria, " +
                "promedio:$promedio}"
    }
}