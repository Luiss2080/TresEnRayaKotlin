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
 * Versión simplificada y robusta
 */
class MainActivity : AppCompatActivity() {

    // Modelo del juego
    private var juego: Juego? = null

    // Elementos de la interfaz
    private var textoTurnoActual: TextView? = null
    private var textoMensajeEstado: TextView? = null
    private var botonReiniciarJuego: Button? = null
    private var botonesCasillas: Array<Array<Button?>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.actividad_principal)
            initGame()
        } catch (e: Exception) {
            handleError("Error iniciando la aplicación", e)
        }
    }

    private fun initGame() {
        try {
            // Inicializar modelo
            juego = Juego()

            // Inicializar vistas
            initViews()

            // Configurar eventos
            setupEventListeners()

            // Actualizar interfaz
            updateUI()

        } catch (e: Exception) {
            handleError("Error inicializando el juego", e)
        }
    }

    private fun initViews() {
        textoTurnoActual = findViewById(R.id.textoTurnoActual)
        textoMensajeEstado = findViewById(R.id.textoMensajeEstado)
        botonReiniciarJuego = findViewById(R.id.botonReiniciarJuego)

        // Inicializar matriz de botones
        val buttons = Array(3) { Array<Button?>(3) { null } }

        val buttonIds = arrayOf(
            arrayOf(R.id.botonCasilla00, R.id.botonCasilla01, R.id.botonCasilla02),
            arrayOf(R.id.botonCasilla10, R.id.botonCasilla11, R.id.botonCasilla12),
            arrayOf(R.id.botonCasilla20, R.id.botonCasilla21, R.id.botonCasilla22)
        )

        for (row in 0 until 3) {
            for (col in 0 until 3) {
                buttons[row][col] = findViewById(buttonIds[row][col])
            }
        }

        botonesCasillas = buttons
    }

    private fun setupEventListeners() {
        try {
            // Configurar clicks del tablero
            botonesCasillas?.let { buttons ->
                for (row in 0 until 3) {
                    for (col in 0 until 3) {
                        buttons[row][col]?.setOnClickListener {
                            handleCellClick(row, col)
                        }
                    }
                }
            }

            // Configurar botón reiniciar
            botonReiniciarJuego?.setOnClickListener {
                resetGame()
            }
        } catch (e: Exception) {
            handleError("Error configurando eventos", e)
        }
    }

    private fun handleCellClick(row: Int, col: Int) {
        try {
            val game = juego ?: return

            if (game.realizarMovimiento(row, col)) {
                updateUI()

                if (game.hayGanador()) {
                    showWinner()
                } else if (game.esEmpate()) {
                    showDraw()
                }
            } else {
                if (game.esCasillaOcupada(row, col)) {
                    showMessage(getString(R.string.casilla_ocupada))
                } else if (!game.estaJuegoActivo()) {
                    showMessage(getString(R.string.juego_terminado))
                }
            }
        } catch (e: Exception) {
            handleError("Error procesando movimiento", e)
        }
    }

    private fun updateUI() {
        try {
            val game = juego ?: return

            // Actualizar tablero
            botonesCasillas?.let { buttons ->
                for (row in 0 until 3) {
                    for (col in 0 until 3) {
                        val button = buttons[row][col]
                        val symbol = game.obtenerSimboloEnCasilla(row, col)
                        button?.text = symbol
                        button?.isEnabled = symbol.isEmpty() && game.estaJuegoActivo()
                    }
                }
            }

            // Actualizar texto turno
            if (game.estaJuegoActivo()) {
                val currentPlayer = game.obtenerJugadorActual()
                val turnText = if (currentPlayer.esJugadorX()) {
                    getString(R.string.turno_jugador_x)
                } else {
                    getString(R.string.turno_jugador_o)
                }
                textoTurnoActual?.text = turnText
                textoTurnoActual?.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            handleError("Error actualizando interfaz", e)
        }
    }

    private fun showWinner() {
        try {
            val game = juego ?: return
            val winner = game.obtenerGanador()

            val message = if (winner?.esJugadorX() == true) {
                getString(R.string.ganador_x)
            } else {
                getString(R.string.ganador_o)
            }

            textoMensajeEstado?.text = message
            textoMensajeEstado?.visibility = View.VISIBLE
            textoTurnoActual?.visibility = View.GONE

            disableAllButtons()
        } catch (e: Exception) {
            handleError("Error mostrando ganador", e)
        }
    }

    private fun showDraw() {
        try {
            textoMensajeEstado?.text = getString(R.string.empate)
            textoMensajeEstado?.visibility = View.VISIBLE
            textoTurnoActual?.visibility = View.GONE

            disableAllButtons()
        } catch (e: Exception) {
            handleError("Error mostrando empate", e)
        }
    }

    private fun disableAllButtons() {
        botonesCasillas?.let { buttons ->
            for (row in 0 until 3) {
                for (col in 0 until 3) {
                    buttons[row][col]?.isEnabled = false
                }
            }
        }
    }

    private fun resetGame() {
        try {
            juego = Juego()

            textoMensajeEstado?.visibility = View.GONE
            textoTurnoActual?.visibility = View.VISIBLE

            updateUI()
        } catch (e: Exception) {
            handleError("Error reiniciando juego", e)
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(message: String, e: Exception) {
        e.printStackTrace()
        showMessage("$message: ${e.message}")
    }
}
