package com.example.tresenrayakotlin.modelo

/**
 * Valida si un movimiento es válido en el juego de tres en raya
 */
class ValidadorMovimiento {

    /**
     * Valida si un movimiento es válido
     * @param tablero el tablero actual
     * @param fila la fila donde se quiere hacer el movimiento
     * @param columna la columna donde se quiere hacer el movimiento
     * @return true si el movimiento es válido
     */
    fun esMovimientoValido(tablero: Tablero, fila: Int, columna: Int): Boolean {
        // Verificar que la posición sea válida en el tablero
        if (!tablero.posicionValida(fila, columna)) {
            return false
        }

        // Verificar que la casilla esté vacía
        val casilla = tablero.obtenerCasilla(fila, columna)
        return casilla?.estaVacia() ?: false
    }

    /**
     * Obtiene el motivo por el cual un movimiento no es válido
     * @param tablero el tablero actual
     * @param fila la fila del movimiento
     * @param columna la columna del movimiento
     * @return el mensaje de error o null si el movimiento es válido
     */
    fun obtenerMotivoMovimientoInvalido(tablero: Tablero, fila: Int, columna: Int): String? {
        if (!tablero.posicionValida(fila, columna)) {
            return "La posición está fuera del tablero"
        }

        val casilla = tablero.obtenerCasilla(fila, columna)
        if (casilla != null && !casilla.estaVacia()) {
            return "La casilla ya está ocupada"
        }

        return null
    }
}
