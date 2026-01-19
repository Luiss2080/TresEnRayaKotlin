package com.example.tresenrayakotlin.modelo

/**
 * Gestiona la lógica principal del juego de tres en raya
 */
class Juego {

    private val tablero = Tablero()
    private val validador = ValidadorMovimiento()
    private val detectorVictoria = DetectorVictoria()

    private var jugadorActual: Jugador
    private val jugador1 = Jugador.crearJugadorX()
    private val jugador2 = Jugador.crearJugadorO()

    private var juegoActivo = true
    private var ganador: Jugador? = null

    init {
        jugadorActual = jugador1 // El jugador X siempre empieza
    }

    /**
     * Realiza un movimiento en el tablero
     * @param fila la fila donde hacer el movimiento
     * @param columna la columna donde hacer el movimiento
     * @return true si el movimiento fue exitoso
     */
    fun realizarMovimiento(fila: Int, columna: Int): Boolean {
        if (!juegoActivo) return false

        if (!validador.esMovimientoValido(tablero, fila, columna)) {
            return false
        }

        // Realizar el movimiento
        val casilla = tablero.obtenerCasilla(fila, columna)
        casilla?.asignarValor(jugadorActual.simbolo)

        // Verificar si hay ganador
        val simboloGanador = detectorVictoria.verificarGanador(tablero)
        if (simboloGanador != null) {
            ganador = if (simboloGanador == jugador1.simbolo) jugador1 else jugador2
            juegoActivo = false
        } else if (detectorVictoria.esEmpate(tablero)) {
            juegoActivo = false
        } else {
            // Cambiar de jugador
            cambiarTurno()
        }

        return true
    }

    /**
     * Cambia el turno al siguiente jugador
     */
    private fun cambiarTurno() {
        jugadorActual = if (jugadorActual == jugador1) jugador2 else jugador1
    }

    /**
     * Obtiene el jugador que debe jugar en el turno actual
     * @return el jugador actual
     */
    fun obtenerJugadorActual(): Jugador {
        return jugadorActual
    }

    /**
     * Obtiene el estado actual del tablero
     * @return el tablero del juego
     */
    fun obtenerTablero(): Tablero {
        return tablero
    }

    /**
     * Verifica si el juego está activo
     * @return true si el juego continúa
     */
    fun estaJuegoActivo(): Boolean {
        return juegoActivo
    }

    /**
     * Obtiene el ganador del juego
     * @return el jugador ganador o null si no hay ganador aún
     */
    fun obtenerGanador(): Jugador? {
        return ganador
    }

    /**
     * Verifica si el juego terminó en empate
     * @return true si es empate
     */
    fun esEmpate(): Boolean {
        return !juegoActivo && ganador == null
    }

    /**
     * Reinicia el juego para comenzar una nueva partida
     */
    fun reiniciarJuego() {
        tablero.limpiar()
        jugadorActual = jugador1
        juegoActivo = true
        ganador = null
    }

    /**
     * Obtiene el motivo por el cual un movimiento no es válido
     * @param fila la fila del movimiento
     * @param columna la columna del movimiento
     * @return el mensaje de error o null si el movimiento es válido
     */
    fun obtenerMotivoMovimientoInvalido(fila: Int, columna: Int): String? {
        if (!juegoActivo) {
            return "El juego ya ha terminado"
        }

        return validador.obtenerMotivoMovimientoInvalido(tablero, fila, columna)
    }

    /**
     * Obtiene el primer jugador (X)
     */
    fun obtenerJugador1(): Jugador {
        return jugador1
    }

    /**
     * Obtiene el segundo jugador (O)
     */
    fun obtenerJugador2(): Jugador {
        return jugador2
    }
}
