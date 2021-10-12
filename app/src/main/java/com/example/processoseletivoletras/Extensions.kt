package com.example.processoseletivoletras


/**
 * Retorna a string sem acentos
 */
fun String.removeAccents(): String {
    val original = "ÁÍÓÚÉÄÏÖÜËÀÌÒÙÈÃÕÂÎÔÛÊáíóúéäïöüëàìòùèãõâîôûêÇç"
    val normalized = "AIOUEAIOUEAIOUEAOAIOUEaioueaioueaioueaoaioueCc"

    return this.map {
        val index = original.indexOf(it)
        if (index >= 0) normalized[index] else it
    }.joinToString("")
}