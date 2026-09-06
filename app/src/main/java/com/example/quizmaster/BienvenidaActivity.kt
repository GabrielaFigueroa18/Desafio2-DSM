package com.example.quizmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class BienvenidaActivity : AppCompatActivity() {

    private lateinit var autenticacion: FirebaseAuth

    private lateinit var textoSaludo: TextView
    private lateinit var botonCulturaGeneral: Button
    private lateinit var botonCiencia: Button
    private lateinit var botonDeportes: Button
    private lateinit var botonHistoria: Button
    private lateinit var grupoDificultad: RadioGroup
    private lateinit var botonComenzarQuiz: Button
    private lateinit var botonCerrarSesion: Button

    private var tipoQuizSeleccionado: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bienvenida)

        autenticacion = FirebaseAuth.getInstance()

        verificarSesion()
        inicializarComponentes()
        mostrarSaludo()
        configurarCategorias()
        configurarBotones()
    }

    private fun verificarSesion() {

        if (autenticacion.currentUser == null) {

            val intent = Intent(
                this,
                MainActivity::class.java
            )

            startActivity(intent)
            finish()
        }
    }

    private fun inicializarComponentes() {

        textoSaludo =
            findViewById(R.id.textoSaludo)

        botonCulturaGeneral =
            findViewById(R.id.botonCulturaGeneral)

        botonCiencia =
            findViewById(R.id.botonCiencia)

        botonDeportes =
            findViewById(R.id.botonDeportes)

        botonHistoria =
            findViewById(R.id.botonHistoria)

        grupoDificultad =
            findViewById(R.id.grupoDificultad)

        botonComenzarQuiz =
            findViewById(R.id.botonComenzarQuiz)

        botonCerrarSesion =
            findViewById(R.id.botonCerrarSesion)
    }

    private fun mostrarSaludo() {

        val usuarioActual =
            autenticacion.currentUser

        val nombre =
            usuarioActual?.displayName

        if (!nombre.isNullOrEmpty()) {

            textoSaludo.text =
                "¡Hola, $nombre!"

        } else {

            textoSaludo.text =
                "¡Hola!"
        }
    }

    private fun configurarCategorias() {

        botonCulturaGeneral.setOnClickListener {

            tipoQuizSeleccionado =
                "Cultura General"

            mostrarSeleccion()
        }

        botonCiencia.setOnClickListener {

            tipoQuizSeleccionado =
                "Ciencia"

            mostrarSeleccion()
        }

        botonDeportes.setOnClickListener {

            tipoQuizSeleccionado =
                "Deportes"

            mostrarSeleccion()
        }

        botonHistoria.setOnClickListener {

            tipoQuizSeleccionado =
                "Historia"

            mostrarSeleccion()
        }
    }

    private fun mostrarSeleccion() {

        Toast.makeText(
            this,
            "Seleccionaste: $tipoQuizSeleccionado",
            Toast.LENGTH_SHORT
        ).show()
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
                "Selecciona un tipo de quiz.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val dificultad =
            when (grupoDificultad.checkedRadioButtonId) {

                R.id.opcionFacil ->
                    "Fácil"

                R.id.opcionDificil ->
                    "Difícil"

                else ->
                    ""
            }

        if (dificultad.isEmpty()) {

            Toast.makeText(
                this,
                "Selecciona una dificultad.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        Toast.makeText(
            this,
            "$tipoQuizSeleccionado - $dificultad",
            Toast.LENGTH_SHORT
        ).show()

        /*
        Cuando hagamos QuizActivity pondremos aquí:

        val intent = Intent(
            this,
            QuizActivity::class.java
        )

        intent.putExtra(
            "tipoQuiz",
            tipoQuizSeleccionado
        )

        intent.putExtra(
            "dificultad",
            dificultad
        )

        startActivity(intent)
        */
    }

    private fun cerrarSesion() {

        autenticacion.signOut()

        val intent = Intent(
            this,
            MainActivity::class.java
        )

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
        finish()
    }
}