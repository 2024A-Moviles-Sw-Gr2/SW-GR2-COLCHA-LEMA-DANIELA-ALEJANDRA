package com.example.materia_estudiante_dacl

class FUbicacion (
     val nombre: String,
     val latitud: Double,
     val longitud: Double
) {

    companion object {

        val listUbicaciones =  arrayListOf<FUbicacion>()

        fun getUbicaciones(): ArrayList<FUbicacion>{
            listUbicaciones.add(FUbicacion("EPN",-0.211300, -78.489330 ))
            listUbicaciones.add(FUbicacion("PUCE", -0.209262, -78.491653))
            listUbicaciones.add(FUbicacion("UPS",-0.208012, -78.487914 ))
            listUbicaciones.add(FUbicacion("UCE", -0.201747, -78.501979))
            listUbicaciones.add(FUbicacion("UDLA", -0.168137, -78.472542))
            return listUbicaciones
        }
    }
}