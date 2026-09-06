package com.example.quizmaster

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

class EntretenimientoActivity : AppCompatActivity() {

    private lateinit var contenedorPreguntas: LinearLayout
    private lateinit var botonReiniciar: Button
    private lateinit var botonEnviar: Button

    private val gruposOpciones =
        mutableListOf<RadioGroup>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_entretenimiento
        )

        contenedorPreguntas =
            findViewById(R.id.contenedorPreguntas)

        botonReiniciar =
            findViewById(R.id.botonReiniciar)

        botonEnviar =
            findViewById(R.id.botonEnviar)

        val dificultad =
            intent.getStringExtra("dificultad") ?: "Fácil"

        val textoDificultad =
            findViewById<TextView>(R.id.textoDificultad)

        textoDificultad.text =
            "Dificultad: $dificultad"

        cargarPreguntas(dificultad)

        botonReiniciar.setOnClickListener {
            reiniciarQuiz()
        }

        botonEnviar.setOnClickListener {
            validarQuiz()
        }
    }

    private fun cargarPreguntas(
        dificultad: String
    ) {

        val preguntas =
            BancoPreguntas.obtenerPreguntas(
                "Entretenimiento",
                dificultad
            )

        contenedorPreguntas.removeAllViews()
        gruposOpciones.clear()

        preguntas.forEachIndexed { indice, pregunta ->

            val textoPregunta =
                TextView(this)

            textoPregunta.text =
                "${indice + 1}. ${pregunta.enunciado}"

            textoPregunta.textSize = 18f

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

            pregunta.opciones.forEach { opcion ->

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

        gruposOpciones.forEach { grupo ->
            grupo.clearCheck()
        }

        Toast.makeText(
            this,
            "Se borraron todas las respuestas.",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun validarQuiz() {

        val preguntasFaltantes =
            mutableListOf<Int>()

        gruposOpciones.forEachIndexed {
                indice,
                grupo ->

            if (grupo.checkedRadioButtonId == -1) {

                preguntasFaltantes.add(
                    indice + 1
                )
            }
        }

        if (preguntasFaltantes.isNotEmpty()) {

            Toast.makeText(
                this,
                "Faltan responder las preguntas: ${preguntasFaltantes.joinToString(", ")}",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        Toast.makeText(
            this,
            "Todas las preguntas fueron respondidas.",
            Toast.LENGTH_SHORT
        ).show()
    }
}