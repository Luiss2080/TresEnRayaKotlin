package com.example.tresenrayakotlin.modelo

/**
 * Representa una casilla individual del tablero de tres en raya
 */
data class Casilla(
    val fila: Int,
    val columna: Int,
    var valor: String = ""
) {

    /**
     * Verifica si la casilla está vacía
     * @return true si la casilla no tiene valor asignado
     */
    fun estaVacia(): Boolean {
        return valor.isEmpty()
    }

    /**
     * Asigna un valor a la casilla
     * @param nuevoValor el valor a asignar ("X" o "O")
     */
    fun asignarValor(nuevoValor: String) {
        valor = nuevoValor
    }

    /**
     * Limpia el contenido de la casilla
     */
    fun limpiar() {
        valor = ""
    }
}
