package com.example.quizmaster

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    private lateinit var textoTipoQuiz: TextView
    private lateinit var textoDificultad: TextView
    private lateinit var textoPuntaje: TextView
    private lateinit var textoRetroalimentacion: TextView
    private lateinit var contenedorRevision: LinearLayout

    private lateinit var botonReintentar: Button
    private lateinit var botonOtroQuiz: Button
    private lateinit var botonInicio: Button

    private var tipoQuiz = ""
    private var dificultad = ""

    private var preguntas = ArrayList<String>()
    private var respuestasElegidas = ArrayList<String>()
    private var respuestasCorrectas = ArrayList<String>()

    private var cantidadAciertos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resultado)

        inicializarComponentes()

        obtenerDatosQuiz()

        calcularPuntaje()

        mostrarResultado()

        mostrarRevision()

        configurarBotones()
    }

    private fun inicializarComponentes() {

        textoTipoQuiz = findViewById(R.id.textoTipoQuiz)
        textoDificultad = findViewById(R.id.textoDificultad)
        textoPuntaje = findViewById(R.id.textoPuntaje)
        textoRetroalimentacion =
            findViewById(R.id.textoRetroalimentacion)

        contenedorRevision =
            findViewById(R.id.contenedorRevision)

        botonReintentar =
            findViewById(R.id.botonReintentar)

        botonOtroQuiz =
            findViewById(R.id.botonOtroQuiz)

        botonInicio =
            findViewById(R.id.botonInicio)
    }

    private fun obtenerDatosQuiz() {

        tipoQuiz =
            intent.getStringExtra("tipoQuiz") ?: "Quiz"

        dificultad =
            intent.getStringExtra("dificultad") ?: "Fácil"

        preguntas =
            intent.getStringArrayListExtra("preguntas")
                ?: ArrayList()

        respuestasElegidas =
            intent.getStringArrayListExtra("respuestasElegidas")
                ?: ArrayList()

        respuestasCorrectas =
            intent.getStringArrayListExtra("respuestasCorrectas")
                ?: ArrayList()
    }

    private fun calcularPuntaje() {

        cantidadAciertos = 0

        val cantidadPreguntas =
            minOf(
                respuestasElegidas.size,
                respuestasCorrectas.size
            )

        for (indice in 0 until cantidadPreguntas) {

            if (
                respuestasElegidas[indice].trim()
                    .equals(
                        respuestasCorrectas[indice].trim(),
                        ignoreCase = true
                    )
            ) {

                cantidadAciertos++
            }
        }
    }

    private fun mostrarResultado() {

        textoTipoQuiz.text =
            "Quiz: $tipoQuiz"

        textoDificultad.text =
            "Dificultad: $dificultad"

        textoPuntaje.text =
            "Obtuviste $cantidadAciertos de ${preguntas.size} respuestas correctas."

        textoRetroalimentacion.text =
            obtenerRetroalimentacion()
    }

    private fun obtenerRetroalimentacion(): String {

        return when {

            cantidadAciertos <= 1 ->
                "Mejor me dedico a otra cosa."

            cantidadAciertos in 2..3 ->
                "Mas o menos OK."

            cantidadAciertos == 4 ->
                "Me merezco un churro."

            cantidadAciertos == 5 &&
                    dificultad.equals(
                        "Difícil",
                        ignoreCase = true
                    ) ->
                "Como pegarle a un bolo."

            cantidadAciertos == 5 ->
                "¡Excelente! Todas las respuestas son correctas."

            else ->
                "Buen trabajo. Sigue practicando."
        }
    }

    private fun mostrarRevision() {

        contenedorRevision.removeAllViews()

        for (indice in preguntas.indices) {

            val pregunta =
                preguntas[indice]

            val respuestaElegida =
                if (indice < respuestasElegidas.size) {
                    respuestasElegidas[indice]
                } else {
                    "Sin respuesta"
                }

            val respuestaCorrecta =
                if (indice < respuestasCorrectas.size) {
                    respuestasCorrectas[indice]
                } else {
                    "No disponible"
                }

            val respuestaCorrectaIndicada =
                respuestaElegida.trim()
                    .equals(
                        respuestaCorrecta.trim(),
                        ignoreCase = true
                    )

            agregarRevision(
                indice + 1,
                pregunta,
                respuestaElegida,
                respuestaCorrecta,
                respuestaCorrectaIndicada
            )
        }
    }

    private fun agregarRevision(
        numeroPregunta: Int,
        pregunta: String,
        respuestaElegida: String,
        respuestaCorrecta: String,
        esCorrecta: Boolean
    ) {

        val tarjeta = LinearLayout(this)

        tarjeta.orientation =
            LinearLayout.VERTICAL

        tarjeta.setPadding(
            20,
            20,
            20,
            20
        )

        val textoPregunta =
            TextView(this)

        textoPregunta.text =
            "$numeroPregunta. $pregunta"

        textoPregunta.textSize = 18f
        textoPregunta.setTypeface(
            null,
            Typeface.BOLD
        )

        val textoRespuestaElegida =
            TextView(this)

        textoRespuestaElegida.text =
            "Tu respuesta: $respuestaElegida"

        textoRespuestaElegida.textSize = 16f

        val textoRespuestaCorrecta =
            TextView(this)

        textoRespuestaCorrecta.text =
            "Respuesta correcta: $respuestaCorrecta"

        textoRespuestaCorrecta.textSize = 16f

        val textoEstado =
            TextView(this)

        if (esCorrecta) {

            textoEstado.text =
                "✓ Correcta"

        } else {

            textoEstado.text =
                "✗ Incorrecta"
        }

        textoEstado.textSize = 16f
        textoEstado.setTypeface(
            null,
            Typeface.BOLD
        )

        tarjeta.addView(textoPregunta)
        tarjeta.addView(textoRespuestaElegida)
        tarjeta.addView(textoRespuestaCorrecta)
        tarjeta.addView(textoEstado)

        val parametros =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        parametros.setMargins(
            0,
            0,
            0,
            20
        )

        tarjeta.layoutParams = parametros

        contenedorRevision.addView(tarjeta)
    }

    private fun configurarBotones() {

        botonReintentar.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MainActivity::class.java
                )

            intent.putExtra(
                "accion",
                "reintentar"
            )

            intent.putExtra(
                "tipoQuiz",
                tipoQuiz
            )

            intent.putExtra(
                "dificultad",
                dificultad
            )

            startActivity(intent)

            finish()
        }

        botonOtroQuiz.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MainActivity::class.java
                )

            intent.putExtra(
                "accion",
                "otroQuiz"
            )

            startActivity(intent)

            finish()
        }

        botonInicio.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MainActivity::class.java
                )

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP

            startActivity(intent)

            finish()
        }
    }
}