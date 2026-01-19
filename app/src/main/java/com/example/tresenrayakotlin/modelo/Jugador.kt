package com.example.tresenrayakotlin.modelo

/**
 * Representa a un jugador en el juego de tres en raya
 */
data class Jugador(
    val nombre: String,
    val simbolo: String
) {

    /**
     * Obtiene el símbolo del jugador
     * @return el símbolo del jugador ("X" o "O")
     */
    fun obtenerSimbolo(): String {
        return simbolo
    }

    /**
     * Obtiene el nombre del jugador
     * @return el nombre del jugador
     */
    fun obtenerNombre(): String {
        return nombre
    }

    companion object {
        const val SIMBOLO_X = "X"
        const val SIMBOLO_O = "O"

        /**
         * Crea un jugador con símbolo X
         */
        fun crearJugadorX(nombre: String = "Jugador 1"): Jugador {
            return Jugador(nombre, SIMBOLO_X)
        }

        /**
         * Crea un jugador con símbolo O
         */
        fun crearJugadorO(nombre: String = "Jugador 2"): Jugador {
            return Jugador(nombre, SIMBOLO_O)
        }
    }
}
