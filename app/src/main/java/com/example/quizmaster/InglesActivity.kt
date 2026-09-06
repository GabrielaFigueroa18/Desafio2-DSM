package com.example.quizmaster

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizmaster.Modelos.BancoPreguntas

class InglesActivity : AppCompatActivity() {

    private lateinit var contenedorPreguntas: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ingles)

        contenedorPreguntas =
            findViewById(R.id.contenedorPreguntas)

        val dificultad =
            intent.getStringExtra("dificultad") ?: "Fácil"

        cargarPreguntas(dificultad)
    }

    private fun cargarPreguntas(
        dificultad: String
    ) {

        val preguntas =
            BancoPreguntas.obtenerPreguntas(
                "Inglés",
                dificultad
            )

        contenedorPreguntas.removeAllViews()

        preguntas.forEachIndexed { indice, pregunta ->

            val textoPregunta =
                TextView(this)

            textoPregunta.text =
                "${indice + 1}. ${pregunta.enunciado}"

            textoPregunta.textSize = 18f
            textoPregunta.setTypeface(
                null,
                android.graphics.Typeface.BOLD
            )

            textoPregunta.setPadding(
                0,
                20,
                0,
                10
            )

            contenedorPreguntas.addView(
                textoPregunta
            )

            val grupoOpciones =
                RadioGroup(this)

            grupoOpciones.orientation =
                RadioGroup.VERTICAL

            pregunta.opciones.forEach { opcion ->

                val radio =
                    RadioButton(this)

                radio.text = opcion
                radio.textSize = 16f

                radio.setPadding(
                    8,
                    8,
                    8,
                    8
                )

                grupoOpciones.addView(
                    radio
                )
            }

            contenedorPreguntas.addView(
                grupoOpciones
            )
        }
    }
}