package com.example.tresenrayakotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tresenrayakotlin.modelo.Juego

/**
 * Actividad principal que gestiona la interfaz del juego de Tres en Raya
 */
class MainActivity : AppCompatActivity() {

    // Modelo del juego
    private lateinit var juego: Juego

    // Elementos de la interfaz
    private lateinit var textoTurnoActual: TextView
    private lateinit var textoMensajeEstado: TextView
    private lateinit var botonReiniciarJuego: Button
    private lateinit var botonesCasillas: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        inicializarModelo()
        inicializarVista()
        configurarControladores()
        actualizarVista()
    }

    /**
     * Inicializa el modelo del juego
     */
    private fun inicializarModelo() {
        juego = Juego()
    }

    /**
     * Inicializa los elementos de la vista
     */
    private fun inicializarVista() {
        textoTurnoActual = findViewById(R.id.textoTurnoActual)
        textoMensajeEstado = findViewById(R.id.textoMensajeEstado)
        botonReiniciarJuego = findViewById(R.id.botonReiniciarJuego)

        // Inicializar la matriz de botones del tablero
        botonesCasillas = Array(3) { fila ->
            Array(3) { columna ->
                val nombreBoton = "botonCasilla$fila$columna"
                val idRecurso = resources.getIdentifier(nombreBoton, "id", packageName)
                findViewById<Button>(idRecurso)
            }
        }
    }

    /**
     * Configura los controladores de eventos
     */
    private fun configurarControladores() {
        // Configurar listeners para cada casilla del tablero
        for (fila in 0 until 3) {
            for (columna in 0 until 3) {
                botonesCasillas[fila][columna].setOnClickListener {
                    manejarClickCasilla(fila, columna)
                }
            }
        }

        // Configurar listener para el botón de reiniciar
        botonReiniciarJuego.setOnClickListener {
            manejarClickReiniciar()
        }
    }

    /**
     * Maneja el evento de click en una casilla
     */
    private fun manejarClickCasilla(fila: Int, columna: Int) {
        val movimientoExitoso = juego.realizarMovimiento(fila, columna)

        if (!movimientoExitoso) {
            val motivo = juego.obtenerMotivoMovimientoInvalido(fila, columna)
            mostrarMensajeError(motivo ?: getString(R.string.casilla_ocupada))
            return
        }

        actualizarVista()

        if (!juego.estaJuegoActivo()) {
            manejarFinDelJuego()
        }
    }

    /**
     * Maneja el evento de click en el botón reiniciar
     */
    private fun manejarClickReiniciar() {
        juego.reiniciarJuego()
        actualizarVista()
        textoMensajeEstado.visibility = View.GONE
    }

    /**
     * Actualiza toda la vista basándose en el estado actual del modelo
     */
    private fun actualizarVista() {
        actualizarTableroEnVista()
        actualizarTextoTurno()
    }

    /**
     * Actualiza el tablero en la vista
     */
    private fun actualizarTableroEnVista() {
        val tablero = juego.obtenerTablero()

        for (fila in 0 until 3) {
            for (columna in 0 until 3) {
                val casilla = tablero.obtenerCasilla(fila, columna)
                val boton = botonesCasillas[fila][columna]

                if (casilla != null) {
                    boton.text = casilla.valor
                    boton.isEnabled = casilla.estaVacia() && juego.estaJuegoActivo()
                }
            }
        }
    }

    /**
     * Actualiza el texto del turno actual
     */
    private fun actualizarTextoTurno() {
        if (juego.estaJuegoActivo()) {
            val jugadorActual = juego.obtenerJugadorActual()
            val textoTurno = if (jugadorActual.simbolo == "X") {
                getString(R.string.turno_jugador_x)
            } else {
                getString(R.string.turno_jugador_o)
            }
            textoTurnoActual.text = textoTurno
        }
    }

    /**
     * Maneja el fin del juego
     */
    private fun manejarFinDelJuego() {
        val ganador = juego.obtenerGanador()
        val esEmpate = juego.esEmpate()

        when {
            esEmpate -> {
                mostrarMensajeEstado(getString(R.string.empate))
            }
            ganador?.simbolo == "X" -> {
                mostrarMensajeEstado(getString(R.string.ganador_x))
            }
            ganador?.simbolo == "O" -> {
                mostrarMensajeEstado(getString(R.string.ganador_o))
            }
        }

        deshabilitarTodasLasCasillas()
    }

    /**
     * Muestra un mensaje de estado
     */
    private fun mostrarMensajeEstado(mensaje: String) {
        textoMensajeEstado.text = mensaje
        textoMensajeEstado.visibility = View.VISIBLE
    }

    /**
     * Muestra un mensaje de error temporal
     */
    private fun mostrarMensajeError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    /**
     * Deshabilita todas las casillas del tablero
     */
    private fun deshabilitarTodasLasCasillas() {
        for (fila in 0 until 3) {
            for (columna in 0 until 3) {
                botonesCasillas[fila][columna].isEnabled = false
            }
        }
    }
}