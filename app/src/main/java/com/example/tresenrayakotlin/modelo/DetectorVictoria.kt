package com.example.tresenrayakotlin.modelo

/**
 * Detecta si hay un ganador en el juego de tres en raya
 */
class DetectorVictoria {

    /**
     * Verifica si hay un ganador en el tablero
     * @param tablero el tablero a verificar
     * @return el símbolo del ganador o null si no hay ganador
     */
    fun verificarGanador(tablero: Tablero): String? {
        // Verificar filas
        for (i in 0 until Tablero.TAMANO) {
            val fila = tablero.obtenerFila(i)
            if (fila != null) {
                val ganador = verificarLineaGanadora(fila)
                if (ganador != null) return ganador
            }
        }

        // Verificar columnas
        for (i in 0 until Tablero.TAMANO) {
            val columna = tablero.obtenerColumna(i)
            if (columna != null) {
                val ganador = verificarLineaGanadora(columna)
                if (ganador != null) return ganador
            }
        }

        // Verificar diagonal principal
        val diagonalPrincipal = tablero.obtenerDiagonalPrincipal()
        val ganadorDiagonal = verificarLineaGanadora(diagonalPrincipal)
        if (ganadorDiagonal != null) return ganadorDiagonal

        // Verificar diagonal secundaria
        val diagonalSecundaria = tablero.obtenerDiagonalSecundaria()
        val ganadorDiagonalSecundaria = verificarLineaGanadora(diagonalSecundaria)
        if (ganadorDiagonalSecundaria != null) return ganadorDiagonalSecundaria

        return null
    }

    /**
     * Verifica si una línea de casillas tiene un ganador
     * @param casillas la lista de casillas a verificar
     * @return el símbolo ganador o null si no hay ganador
     */
    private fun verificarLineaGanadora(casillas: List<Casilla>): String? {
        if (casillas.size != Tablero.TAMANO) return null

        val primerSimbolo = casillas[0].valor

        // Si la primera casilla está vacía, no puede haber ganador
        if (primerSimbolo.isEmpty()) return null

        // Verificar si todas las casillas tienen el mismo símbolo
        val todasIguales = casillas.all { it.valor == primerSimbolo }

        return if (todasIguales) primerSimbolo else null
    }

    /**
     * Verifica si el juego terminó en empate
     * @param tablero el tablero a verificar
     * @return true si es empate (tablero lleno sin ganador)
     */
    fun esEmpate(tablero: Tablero): Boolean {
        return verificarGanador(tablero) == null && tablero.estaLleno()
    }
}
