package com.example.tresenrayakotlin

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tresenrayakotlin.modelo.Juego
import com.example.tresenrayakotlin.modelo.EstadisticasJuego

/**
 * Actividad principal que gestiona la interfaz del juego de Tres en Raya
 * Versión moderna con estadísticas y mensajes dinámicos
 */
class MainActivity : AppCompatActivity() {

    // Modelo del juego y estadísticas
    private var juego: Juego? = null
    private var estadisticas: EstadisticasJuego? = null

    // Elementos de la interfaz
    private var textoTurnoActual: TextView? = null
    private var textoMensajeEstado: TextView? = null
    private var textoMensajeSecundario: TextView? = null
    private var cardMensajeEstado: View? = null
    private var botonReiniciarJuego: Button? = null
    private var botonReiniciarStats: Button? = null
    private var botonesCasillas: Array<Array<Button?>>? = null
    private var progressBarTurno: ProgressBar? = null

    // Elementos de estadísticas
    private var textoVictoriasX: TextView? = null
    private var textoVictoriasO: TextView? = null
    private var textoEmpates: TextView? = null
    private var textoPartidasTotales: TextView? = null

    // Mensajes dinámicos
    private val mensajesVictoriaX = listOf(
        "¡JUGADOR X DOMINA EL TABLERO!",
        "¡X STRIKES AGAIN!",
        "¡VICTORIA ÉPICA DE X!",
        "¡X ES IMPARABLE!",
        "¡MAESTRÍA TOTAL DE X!"
    )

    private val mensajesVictoriaO = listOf(
        "¡JUGADOR O CONQUISTA!",
        "¡O RULES THE GAME!",
        "¡TRIUNFO ESPECTACULAR DE O!",
        "¡O ES INVENCIBLE!",
        "¡DOMINIO ABSOLUTO DE O!"
    )

    private val mensajesEmpate = listOf(
        "¡BATALLA ÉPICA TERMINADA EN EMPATE!",
        "¡NADIE PUEDE VENCER AL OTRO!",
        "¡EQUILIBRIO PERFECTO!",
        "¡EMPATE DE CAMPEONES!",
        "¡FUERZA EQUIVALENTE!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.actividad_principal)
            initGame()
        } catch (e: Exception) {
            handleError("Error iniciando la aplicación", e)
            finish()
        }
    }

    private fun initGame() {
        try {
            // Inicializar modelo y estadísticas
            juego = Juego()
            estadisticas = EstadisticasJuego(this)

            // Inicializar vistas
            initViews()

            // Configurar eventos
            setupEventListeners()

            // Actualizar interfaz
            updateUI()
            updateStats()

        } catch (e: Exception) {
            handleError("Error inicializando el juego", e)
        }
    }

    private fun initViews() {
        // Vistas principales
        textoTurnoActual = findViewById(R.id.textoTurnoActual)
        textoMensajeEstado = findViewById(R.id.textoMensajeEstado)
        textoMensajeSecundario = findViewById(R.id.textoMensajeSecundario)
        cardMensajeEstado = findViewById(R.id.cardMensajeEstado)
        botonReiniciarJuego = findViewById(R.id.botonReiniciarJuego)
        botonReiniciarStats = findViewById(R.id.botonReiniciarStats)
        progressBarTurno = findViewById(R.id.progressBarTurno)

        // Vistas de estadísticas
        textoVictoriasX = findViewById(R.id.textoVictoriasX)
        textoVictoriasO = findViewById(R.id.textoVictoriasO)
        textoEmpates = findViewById(R.id.textoEmpates)
        textoPartidasTotales = findViewById(R.id.textoPartidasTotales)

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

            // Configurar botón reiniciar partida
            botonReiniciarJuego?.setOnClickListener {
                resetGame()
            }

            // Configurar botón reiniciar estadísticas
            botonReiniciarStats?.setOnClickListener {
                showResetStatsDialog()
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
                animateProgressBar()

                when {
                    game.hayGanador() -> {
                        val winner = game.obtenerGanador()
                        if (winner?.esJugadorX() == true) {
                            estadisticas?.registrarVictoriaX()
                            showWinnerWithAnimation(true)
                        } else {
                            estadisticas?.registrarVictoriaO()
                            showWinnerWithAnimation(false)
                        }
                        updateStats()
                    }
                    game.esEmpate() -> {
                        estadisticas?.registrarEmpate()
                        showDrawWithAnimation()
                        updateStats()
                    }
                }
            } else {
                if (game.esCasillaOcupada(row, col)) {
                    showMessage("Esta casilla ya está ocupada")
                } else if (!game.estaJuegoActivo()) {
                    showMessage("El juego ya ha terminado")
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

                        // Colorear símbolos
                        when (symbol) {
                            "X" -> button?.setTextColor(getColor(R.color.player_x_color))
                            "O" -> button?.setTextColor(getColor(R.color.player_o_color))
                            else -> button?.setTextColor(getColor(R.color.primary_text))
                        }
                    }
                }
            }

            // Actualizar texto turno y barra de progreso
            if (game.estaJuegoActivo()) {
                val currentPlayer = game.obtenerJugadorActual()
                if (currentPlayer.esJugadorX()) {
                    textoTurnoActual?.text = getString(R.string.turno_jugador_x)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        progressBarTurno?.progressTintList = getColorStateList(R.color.progress_x)
                    }
                } else {
                    textoTurnoActual?.text = getString(R.string.turno_jugador_o)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        progressBarTurno?.progressTintList = getColorStateList(R.color.progress_o)
                    }
                }
                textoTurnoActual?.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            handleError("Error actualizando interfaz", e)
        }
    }

    private fun showWinnerWithAnimation(isPlayerX: Boolean) {
        try {
            val message = if (isPlayerX) {
                mensajesVictoriaX.random()
            } else {
                mensajesVictoriaO.random()
            }

            val stats = estadisticas!!
            val secondaryMessage = if (isPlayerX) {
                getString(R.string.victorias_x_total, stats.obtenerVictoriasX())
            } else {
                getString(R.string.victorias_o_total, stats.obtenerVictoriasO())
            }

            showAnimatedMessage(message, secondaryMessage, R.color.victory_glow)
            disableAllButtons()
        } catch (e: Exception) {
            handleError("Error mostrando ganador", e)
        }
    }

    private fun showDrawWithAnimation() {
        try {
            val message = mensajesEmpate.random()
            val stats = estadisticas!!
            val secondaryMessage = getString(R.string.empates_total, stats.obtenerEmpates())

            showAnimatedMessage(message, secondaryMessage, R.color.draw_glow)
            disableAllButtons()
        } catch (e: Exception) {
            handleError("Error mostrando empate", e)
        }
    }

    private fun showAnimatedMessage(message: String, secondary: String, colorRes: Int) {
        textoMensajeEstado?.text = message
        textoMensajeSecundario?.text = secondary
        textoMensajeEstado?.setTextColor(getColor(colorRes))
        cardMensajeEstado?.visibility = View.VISIBLE
        textoTurnoActual?.visibility = View.GONE

        // Animación de entrada
        cardMensajeEstado?.alpha = 0f
        cardMensajeEstado?.animate()?.alpha(1f)?.duration = 500
    }

    private fun animateProgressBar() {
        progressBarTurno?.let { bar ->
            val animator = ValueAnimator.ofInt(bar.progress, if (bar.progress == 25) 75 else 25)
            animator.duration = 300
            animator.addUpdateListener { animation ->
                bar.progress = animation.animatedValue as Int
            }
            animator.start()
        }
    }

    private fun updateStats() {
        try {
            val stats = estadisticas ?: return

            textoVictoriasX?.text = stats.obtenerVictoriasX().toString()
            textoVictoriasO?.text = stats.obtenerVictoriasO().toString()
            textoEmpates?.text = stats.obtenerEmpates().toString()
            textoPartidasTotales?.text = getString(R.string.partidas_jugadas, stats.obtenerPartidasTotales())

        } catch (e: Exception) {
            handleError("Error actualizando estadísticas", e)
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

            cardMensajeEstado?.visibility = View.GONE
            textoTurnoActual?.visibility = View.VISIBLE
            progressBarTurno?.progress = 50

            updateUI()
        } catch (e: Exception) {
            handleError("Error reiniciando juego", e)
        }
    }

    private fun showResetStatsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Reiniciar Estadísticas")
            .setMessage("¿Estás seguro de que quieres reiniciar todas las estadísticas?")
            .setPositiveButton("Sí") { _, _ ->
                estadisticas?.reiniciarEstadisticas()
                updateStats()
                showMessage("Estadísticas reiniciadas")
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(message: String, e: Exception) {
        e.printStackTrace()
        showMessage("$message: ${e.message}")
    }
}
