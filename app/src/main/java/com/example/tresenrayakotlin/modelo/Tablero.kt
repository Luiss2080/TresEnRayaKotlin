package com.example.tresenrayakotlin.modelo

/**
 * Gestiona el tablero de 3x3 del juego de tres en raya
 */
class Tablero {

    companion object {
        const val TAMANO = 3
    }

    private val casillas: Array<Array<Casilla>> = Array(TAMANO) { fila ->
        Array(TAMANO) { columna ->
            Casilla(fila, columna)
        }
    }

    /**
     * Obtiene una casilla específica del tablero
     * @param fila la fila de la casilla (0-2)
     * @param columna la columna de la casilla (0-2)
     * @return la casilla en la posición especificada
     */
    fun obtenerCasilla(fila: Int, columna: Int): Casilla? {
        return if (posicionValida(fila, columna)) {
            casillas[fila][columna]
        } else {
            null
        }
    }

    /**
     * Verifica si una posición es válida en el tablero
     * @param fila la fila a verificar
     * @param columna la columna a verificar
     * @return true si la posición es válida
     */
    fun posicionValida(fila: Int, columna: Int): Boolean {
        return fila in 0 until TAMANO && columna in 0 until TAMANO
    }

    /**
     * Obtiene todas las casillas del tablero
     * @return array de todas las casillas
     */
    fun obtenerTodasLasCasillas(): List<Casilla> {
        val listaCasillas = mutableListOf<Casilla>()
        for (fila in casillas) {
            for (casilla in fila) {
                listaCasillas.add(casilla)
            }
        }
        return listaCasillas
    }

    /**
     * Obtiene una fila específica del tablero
     * @param numeroFila el número de fila (0-2)
     * @return las casillas de la fila
     */
    fun obtenerFila(numeroFila: Int): List<Casilla>? {
        return if (numeroFila in 0 until TAMANO) {
            casillas[numeroFila].toList()
        } else {
            null
        }
    }

    /**
     * Obtiene una columna específica del tablero
     * @param numeroColumna el número de columna (0-2)
     * @return las casillas de la columna
     */
    fun obtenerColumna(numeroColumna: Int): List<Casilla>? {
        return if (numeroColumna in 0 until TAMANO) {
            (0 until TAMANO).map { fila -> casillas[fila][numeroColumna] }
        } else {
            null
        }
    }

    /**
     * Obtiene la diagonal principal del tablero (de arriba-izquierda a abajo-derecha)
     * @return las casillas de la diagonal principal
     */
    fun obtenerDiagonalPrincipal(): List<Casilla> {
        return (0 until TAMANO).map { i -> casillas[i][i] }
    }

    /**
     * Obtiene la diagonal secundaria del tablero (de arriba-derecha a abajo-izquierda)
     * @return las casillas de la diagonal secundaria
     */
    fun obtenerDiagonalSecundaria(): List<Casilla> {
        return (0 until TAMANO).map { i -> casillas[i][TAMANO - 1 - i] }
    }

    /**
     * Verifica si el tablero está lleno
     * @return true si todas las casillas están ocupadas
     */
    fun estaLleno(): Boolean {
        return obtenerTodasLasCasillas().none { it.estaVacia() }
    }

    /**
     * Limpia todo el tablero
     */
    fun limpiar() {
        casillas.forEach { fila ->
            fila.forEach { casilla ->
                casilla.limpiar()
            }
        }
    }
}
