package com.example.a2024a_swgr2_dacl

class BEntrenador(var id:Int,
    var nombre:String,
    var descripcion:String?) {
    override fun toString(): String {
        return "${nombre} ${descripcion}"
    }
}