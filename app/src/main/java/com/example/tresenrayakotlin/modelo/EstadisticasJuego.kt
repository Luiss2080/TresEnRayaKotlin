package com.example.tresenrayakotlin.modelo

import android.content.Context
import android.content.SharedPreferences

/**
 * Gestiona las estadísticas del juego (victorias, empates, partidas)
 */
class EstadisticasJuego(context: Context) {

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences("tres_en_raya_stats", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_VICTORIAS_X = "victorias_x"
        private const val KEY_VICTORIAS_O = "victorias_o"
        private const val KEY_EMPATES = "empates"
        private const val KEY_PARTIDAS_TOTALES = "partidas_totales"
    }

    /**
     * Registra una victoria del jugador X
     */
    fun registrarVictoriaX() {
        val victorias = obtenerVictoriasX() + 1
        sharedPrefs.edit().putInt(KEY_VICTORIAS_X, victorias).apply()
        incrementarPartidaTotal()
    }

    /**
     * Registra una victoria del jugador O
     */
    fun registrarVictoriaO() {
        val victorias = obtenerVictoriasO() + 1
        sharedPrefs.edit().putInt(KEY_VICTORIAS_O, victorias).apply()
        incrementarPartidaTotal()
    }

    /**
     * Registra un empate
     */
    fun registrarEmpate() {
        val empates = obtenerEmpates() + 1
        sharedPrefs.edit().putInt(KEY_EMPATES, empates).apply()
        incrementarPartidaTotal()
    }

    /**
     * Obtiene las victorias del jugador X
     */
    fun obtenerVictoriasX(): Int = sharedPrefs.getInt(KEY_VICTORIAS_X, 0)

    /**
     * Obtiene las victorias del jugador O
     */
    fun obtenerVictoriasO(): Int = sharedPrefs.getInt(KEY_VICTORIAS_O, 0)

    /**
     * Obtiene el número de empates
     */
    fun obtenerEmpates(): Int = sharedPrefs.getInt(KEY_EMPATES, 0)

    /**
     * Obtiene el total de partidas jugadas
     */
    fun obtenerPartidasTotales(): Int = sharedPrefs.getInt(KEY_PARTIDAS_TOTALES, 0)

    /**
     * Calcula el porcentaje de victorias de X
     */
    fun porcentajeVictoriasX(): Float {
        val total = obtenerPartidasTotales()
        return if (total > 0) (obtenerVictoriasX().toFloat() / total * 100) else 0f
    }

    /**
     * Calcula el porcentaje de victorias de O
     */
    fun porcentajeVictoriasO(): Float {
        val total = obtenerPartidasTotales()
        return if (total > 0) (obtenerVictoriasO().toFloat() / total * 100) else 0f
    }

    /**
     * Obtiene el jugador con más victorias
     */
    fun obtenerJugadorLider(): String {
        val victoriasX = obtenerVictoriasX()
        val victoriasO = obtenerVictoriasO()

        return when {
            victoriasX > victoriasO -> "Jugador X"
            victoriasO > victoriasX -> "Jugador O"
            else -> "Empate"
        }
    }

    /**
     * Reinicia todas las estadísticas
     */
    fun reiniciarEstadisticas() {
        sharedPrefs.edit()
            .putInt(KEY_VICTORIAS_X, 0)
            .putInt(KEY_VICTORIAS_O, 0)
            .putInt(KEY_EMPATES, 0)
            .putInt(KEY_PARTIDAS_TOTALES, 0)
            .apply()
    }

    private fun incrementarPartidaTotal() {
        val total = obtenerPartidasTotales() + 1
        sharedPrefs.edit().putInt(KEY_PARTIDAS_TOTALES, total).apply()
    }
}
