package com.example.quizmaster.Modelos

import java.io.Serializable

data class Pregunta(
    val enunciado: String,
    val opciones: List<String>,
    val respuestaCorrecta: String,
    val dificultad: String,
    val categoria: String
) : Serializable