package com.example.quizmaster

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizmaster.Modelos.BancoPreguntas
import com.example.quizmaster.Modelos.Pregunta

class MatematicasActivity : AppCompatActivity() {

    private lateinit var contenedorPreguntas: LinearLayout
    private lateinit var botonReiniciar: Button
    private lateinit var botonEnviar: Button
    private lateinit var textoDificultad: TextView

    private val gruposOpciones =
        mutableListOf<RadioGroup>()

    private var preguntas =
        ArrayList<Pregunta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_matematicas
        )

        inicializarComponentes()

        val dificultad =
            intent.getStringExtra("dificultad")
                ?: "Fácil"

        textoDificultad.text =
            "Dificultad: $dificultad"

        cargarPreguntas(dificultad)

        botonReiniciar.setOnClickListener {
            reiniciarQuiz()
        }

        botonEnviar.setOnClickListener {
            validarQuiz(dificultad)
        }
    }

    private fun inicializarComponentes() {

        contenedorPreguntas =
            findViewById(
                R.id.contenedorPreguntas
            )

        botonReiniciar =
            findViewById(
                R.id.botonReiniciar
            )

        botonEnviar =
            findViewById(
                R.id.botonEnviar
            )

        textoDificultad =
            findViewById(
                R.id.textoDificultad
            )
    }

    private fun cargarPreguntas(
        dificultad: String
    ) {

        preguntas =
            BancoPreguntas.obtenerPreguntas(
                "Matemáticas",
                dificultad
            ) as ArrayList<Pregunta>

        contenedorPreguntas.removeAllViews()

        gruposOpciones.clear()

        preguntas.forEachIndexed {
                indice,
                pregunta ->

            val textoPregunta =
                TextView(this)

            textoPregunta.text =
                "${indice + 1}. ${pregunta.enunciado}"

            textoPregunta.textSize =
                18f

            textoPregunta.setTypeface(
                null,
                Typeface.BOLD
            )

            textoPregunta.setPadding(
                0,
                16,
                0,
                8
            )

            contenedorPreguntas.addView(
                textoPregunta
            )

            val grupoOpciones =
                RadioGroup(this)

            grupoOpciones.orientation =
                RadioGroup.VERTICAL

            pregunta.opciones.forEach {
                    opcion ->

                val radioButton =
                    RadioButton(this)

                radioButton.text =
                    opcion

                radioButton.textSize =
                    16f

                radioButton.setPadding(
                    8,
                    6,
                    8,
                    6
                )

                grupoOpciones.addView(
                    radioButton
                )
            }

            gruposOpciones.add(
                grupoOpciones
            )

            contenedorPreguntas.addView(
                grupoOpciones
            )
        }
    }

    private fun reiniciarQuiz() {

        gruposOpciones.forEach {
                grupo ->

            grupo.clearCheck()
        }

        Toast.makeText(
            this,
            "Se borraron todas las respuestas.",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun validarQuiz(
        dificultad: String
    ) {

        val preguntasFaltantes =
            mutableListOf<Int>()

        gruposOpciones.forEachIndexed {
                indice,
                grupo ->

            if (
                grupo.checkedRadioButtonId == -1
            ) {

                preguntasFaltantes.add(
                    indice + 1
                )
            }
        }

        if (
            preguntasFaltantes.isNotEmpty()
        ) {

            Toast.makeText(
                this,
                "Faltan responder las preguntas: ${preguntasFaltantes.joinToString(", ")}",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        enviarResultado(dificultad)
    }

    private fun enviarResultado(
        dificultad: String
    ) {

        val preguntasTexto =
            ArrayList<String>()

        val respuestasElegidas =
            ArrayList<String>()

        val respuestasCorrectas =
            ArrayList<String>()

        preguntas.forEachIndexed {
                indice,
                pregunta ->

            preguntasTexto.add(
                pregunta.enunciado
            )

            respuestasCorrectas.add(
                pregunta.respuestaCorrecta
            )

            val grupo =
                gruposOpciones[indice]

            val idSeleccionado =
                grupo.checkedRadioButtonId

            val radioSeleccionado =
                grupo.findViewById<RadioButton>(
                    idSeleccionado
                )

            respuestasElegidas.add(
                radioSeleccionado.text.toString()
            )
        }

        val intent =
            Intent(
                this,
                ResultadoActivity::class.java
            )

        intent.putExtra(
            "tipoQuiz",
            "Matemáticas"
        )

        intent.putExtra(
            "dificultad",
            dificultad
        )

        intent.putStringArrayListExtra(
            "preguntas",
            preguntasTexto
        )

        intent.putStringArrayListExtra(
            "respuestasElegidas",
            respuestasElegidas
        )

        intent.putStringArrayListExtra(
            "respuestasCorrectas",
            respuestasCorrectas
        )

        startActivity(intent)

        finish()
    }
}