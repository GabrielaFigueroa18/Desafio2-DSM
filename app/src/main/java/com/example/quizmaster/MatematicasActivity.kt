package com.example.quizmaster

import android.content.Intent
import android.os.Bundle
import android.view.View
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

    private val tarjetasVistas =
        mutableListOf<View>()

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

        tarjetasVistas.clear()

        preguntas.forEachIndexed {
                indice,
                pregunta ->

            val tarjeta =
                layoutInflater.inflate(
                    R.layout.item_pregunta,
                    contenedorPreguntas,
                    false
                )

            val textoPregunta =
                tarjeta.findViewById<TextView>(
                    R.id.textoPregunta
                )

            val grupoOpciones =
                tarjeta.findViewById<RadioGroup>(
                    R.id.grupoOpciones
                )

            textoPregunta.text =
                "${indice + 1}. ${pregunta.enunciado}"

            pregunta.opciones.forEachIndexed {
                    indiceOpcion,
                    opcion ->

                if (indiceOpcion < grupoOpciones.childCount) {

                    val radioButton =
                        grupoOpciones.getChildAt(
                            indiceOpcion
                        ) as RadioButton

                    radioButton.text =
                        opcion

                    radioButton.visibility =
                        View.VISIBLE
                }
            }

            for (
            indiceOpcion
            in pregunta.opciones.size until grupoOpciones.childCount
            ) {

                grupoOpciones
                    .getChildAt(indiceOpcion)
                    .visibility = View.GONE
            }

            tarjetasVistas.add(
                tarjeta
            )

            contenedorPreguntas.addView(
                tarjeta
            )
        }
    }

    private fun reiniciarQuiz() {

        tarjetasVistas.forEach { tarjeta ->

            val grupoOpciones =
                tarjeta.findViewById<RadioGroup>(
                    R.id.grupoOpciones
                )

            grupoOpciones.clearCheck()
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

        tarjetasVistas.forEachIndexed {
                indice,
                tarjeta ->

            val grupoOpciones =
                tarjeta.findViewById<RadioGroup>(
                    R.id.grupoOpciones
                )

            if (
                grupoOpciones.checkedRadioButtonId == -1
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

            val tarjeta =
                tarjetasVistas[indice]

            val grupoOpciones =
                tarjeta.findViewById<RadioGroup>(
                    R.id.grupoOpciones
                )

            val idSeleccionado =
                grupoOpciones.checkedRadioButtonId

            val radioSeleccionado =
                grupoOpciones.findViewById<RadioButton>(
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