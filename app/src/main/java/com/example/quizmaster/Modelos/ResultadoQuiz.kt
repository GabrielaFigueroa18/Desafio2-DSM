package com.example.quizmaster.Modelos

import java.io.Serializable

data class ResultadoQuiz(
    val tipoQuiz: String,
    val dificultad: String,
    val preguntas: List<String>,
    val respuestasElegidas: List<String>,
    val respuestasCorrectas: List<String>,
    val cantidadAciertos: Int
) : Serializable