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

    private lateinit var botonIngles: Button
    private lateinit var botonMatematicas: Button
    private lateinit var botonEntretenimiento: Button
    private lateinit var botonCultura: Button

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

            return
        }
    }

    private fun inicializarComponentes() {

        textoSaludo =
            findViewById(R.id.textoSaludo)

        botonIngles =
            findViewById(R.id.botonIngles)

        botonMatematicas =
            findViewById(R.id.botonMatematicas)

        botonEntretenimiento =
            findViewById(R.id.botonEntretenimiento)

        botonCultura =
            findViewById(R.id.botonCultura)

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

        botonIngles.setOnClickListener {

            seleccionarCategoria("Inglés")
        }

        botonMatematicas.setOnClickListener {

            seleccionarCategoria("Matemáticas")
        }

        botonEntretenimiento.setOnClickListener {

            seleccionarCategoria("Entretenimiento")
        }

        botonCultura.setOnClickListener {

            seleccionarCategoria("Cultura")
        }
    }

    private fun seleccionarCategoria(
        categoria: String
    ) {

        tipoQuizSeleccionado = categoria

        Toast.makeText(
            this,
            "Seleccionaste: $categoria",
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

        // Verificar que se haya seleccionado una categoría

        if (tipoQuizSeleccionado.isEmpty()) {

            Toast.makeText(
                this,
                "Selecciona un tipo de quiz.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Obtener la dificultad seleccionada

        val dificultad = when (
            grupoDificultad.checkedRadioButtonId
        ) {

            R.id.opcionFacil ->
                "Fácil"

            R.id.opcionDificil ->
                "Difícil"

            else ->
                ""
        }

        // Verificar que se haya seleccionado una dificultad

        if (dificultad.isEmpty()) {

            Toast.makeText(
                this,
                "Selecciona una dificultad.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        /*
         * Por ahora solamente mostramos la selección.
         *
         * Las Activities de cada quiz se conectarán
         * posteriormente cuando implementemos el
         * motor del quiz.
         */

        Toast.makeText(
            this,
            "$tipoQuizSeleccionado - $dificultad",
            Toast.LENGTH_SHORT
        ).show()
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