package com.example.quizmaster

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth

class BienvenidaActivity : AppCompatActivity() {

    private lateinit var autenticacion: FirebaseAuth
    private lateinit var cardIngles: MaterialCardView
    private lateinit var cardMatematicas: MaterialCardView
    private lateinit var cardEntretenimiento: MaterialCardView
    private lateinit var cardCultura: MaterialCardView
    private lateinit var grupoDificultad: RadioGroup
    private lateinit var botonComenzarQuiz: MaterialButton
    private lateinit var botonCerrarSesion: MaterialButton

    private var tipoQuizSeleccionado: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.colorGeneral1)

        setContentView(R.layout.activity_bienvenida)

        autenticacion = FirebaseAuth.getInstance()

        verificarSesion()
        inicializarComponentes()
        configurarCategorias()
        configurarBotones()
    }

    private fun verificarSesion() {
        if (autenticacion.currentUser == null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun inicializarComponentes() {
        cardIngles = findViewById(R.id.cardIngles)
        cardMatematicas = findViewById(R.id.cardMatematicas)
        cardEntretenimiento = findViewById(R.id.cardEntretenimiento)
        cardCultura = findViewById(R.id.cardCultura)

        grupoDificultad = findViewById(R.id.grupoDificultad)

        botonComenzarQuiz = findViewById(R.id.botonComenzarQuiz)
        botonCerrarSesion = findViewById(R.id.botonCerrarSesion)
    }

    private fun configurarCategorias() {
        cardIngles.setOnClickListener {
            tipoQuizSeleccionado = "Inglés"
            resaltarCategoriaSeleccionada(cardIngles, R.color.colorIngles1, R.color.colorIngles4)
        }

        cardMatematicas.setOnClickListener {
            tipoQuizSeleccionado = "Matemáticas"
            resaltarCategoriaSeleccionada(cardMatematicas, R.color.colorMatematicas1, R.color.colorMatematicas4)
        }

        cardEntretenimiento.setOnClickListener {
            tipoQuizSeleccionado = "Entretenimiento"
            resaltarCategoriaSeleccionada(cardEntretenimiento, R.color.colorEntretenimiento1, R.color.colorEntretenimiento4)
        }

        cardCultura.setOnClickListener {
            tipoQuizSeleccionado = "Cultura"
            resaltarCategoriaSeleccionada(cardCultura, R.color.colorCultura1, R.color.colorCultura4)
        }
    }

    private fun resaltarCategoriaSeleccionada(
        tarjetaSeleccionada: MaterialCardView,
        colorBordeRes: Int,
        colorFondoRes: Int
    ) {
        val colorBlanco = ContextCompat.getColor(this, R.color.colorBlanco)
        val colorGrisBorde = ContextCompat.getColor(this, android.R.color.transparent)

        val tarjetas = listOf(cardIngles, cardMatematicas, cardEntretenimiento, cardCultura)
        for (card in tarjetas) {
            card.setCardBackgroundColor(colorBlanco)
            card.strokeWidth = 0
            card.strokeColor = colorGrisBorde
        }

        tarjetaSeleccionada.setCardBackgroundColor(ContextCompat.getColor(this, colorFondoRes))
        tarjetaSeleccionada.strokeColor = ContextCompat.getColor(this, colorBordeRes)
        tarjetaSeleccionada.strokeWidth = 6 // Grosor del borde al seleccionar (en pixeles)

        Toast.makeText(this, "Seleccionaste: $tipoQuizSeleccionado", Toast.LENGTH_SHORT).show()
    }

    private fun configurarBotones() {
        botonComenzarQuiz.setOnClickListener {
            comenzarQuiz()
        }

        botonCerrarSesion.setOnClickListener {
            cerrarSesion()
        }
    }

    private fun comenzarQuiz() {
        if (tipoQuizSeleccionado.isEmpty()) {
            Toast.makeText(
                this,
                "Selecciona una categoría.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val dificultad = when (grupoDificultad.checkedRadioButtonId) {
            R.id.opcionFacil -> "Fácil"
            R.id.opcionDificil -> "Difícil"
            else -> ""
        }

        if (dificultad.isEmpty()) {
            Toast.makeText(
                this,
                "Selecciona una dificultad.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        when (tipoQuizSeleccionado) {
            "Inglés" -> {
                val intent = Intent(this, InglesActivity::class.java)
                intent.putExtra("tipoQuiz", "Inglés")
                intent.putExtra("dificultad", dificultad)
                startActivity(intent)
            }

            "Matemáticas" -> {
                val intent = Intent(this, MatematicasActivity::class.java)
                intent.putExtra("tipoQuiz", "Matemáticas")
                intent.putExtra("dificultad", dificultad)
                startActivity(intent)
            }

            "Entretenimiento" -> {
                val intent = Intent(this, EntretenimientoActivity::class.java)
                intent.putExtra("tipoQuiz", "Entretenimiento")
                intent.putExtra("dificultad", dificultad)
                startActivity(intent)
            }

            "Cultura" -> {
                val intent = Intent(this, CulturaActivity::class.java)
                intent.putExtra("tipoQuiz", "Cultura")
                intent.putExtra("dificultad", dificultad)
                startActivity(intent)
            }

            else -> {
                Toast.makeText(
                    this,
                    "Categoría no válida.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun cerrarSesion() {
        autenticacion.signOut()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}