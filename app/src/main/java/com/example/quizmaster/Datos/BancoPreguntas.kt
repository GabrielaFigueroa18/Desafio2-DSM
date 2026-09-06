package com.example.quizmaster.Modelos

object BancoPreguntas {

    private const val FACIL = "Fácil"
    private const val DIFICIL = "Difícil"

    private const val INGLES = "Inglés"
    private const val MATEMATICAS = "Matemáticas"
    private const val ENTRETENIMIENTO = "Entretenimiento"
    private const val CULTURA = "Cultura"

    val preguntas = listOf(

        // =========================================================
        // INGLÉS - FÁCIL
        // =========================================================

        Pregunta(
            enunciado = "¿Cuál es la traducción de \"house\"?",
            opciones = listOf(
                "Casa",
                "Escuela",
                "Calle",
                "Mesa"
            ),
            respuestaCorrecta = "Casa",
            dificultad = FACIL,
            categoria = INGLES
        ),

        Pregunta(
            enunciado = "¿Cuál es el pasado de \"go\"?",
            opciones = listOf(
                "Goed",
                "Went",
                "Gone",
                "Going"
            ),
            respuestaCorrecta = "Went",
            dificultad = FACIL,
            categoria = INGLES
        ),

        Pregunta(
            enunciado = "¿Qué palabra significa \"rápido\"?",
            opciones = listOf(
                "Slow",
                "Fast",
                "Weak",
                "Late"
            ),
            respuestaCorrecta = "Fast",
            dificultad = FACIL,
            categoria = INGLES
        ),

        Pregunta(
            enunciado = "Completa la oración: \"She ___ a doctor.\"",
            opciones = listOf(
                "are",
                "am",
                "is",
                "be"
            ),
            respuestaCorrecta = "is",
            dificultad = FACIL,
            categoria = INGLES
        ),

        Pregunta(
            enunciado = "¿Cuál es el plural correcto de \"child\"?",
            opciones = listOf(
                "Childs",
                "Children",
                "Childes",
                "Childrens"
            ),
            respuestaCorrecta = "Children",
            dificultad = FACIL,
            categoria = INGLES
        ),

        // =========================================================
        // INGLÉS - DIFÍCIL
        // =========================================================

        Pregunta(
            enunciado = "¿Qué tiempo verbal expresa una acción que comenzó en el pasado y continúa hasta el presente?",
            opciones = listOf(
                "Present Perfect",
                "Simple Past",
                "Future Simple",
                "Past Continuous"
            ),
            respuestaCorrecta = "Present Perfect",
            dificultad = DIFICIL,
            categoria = INGLES
        ),

        Pregunta(
            enunciado = "Selecciona la opción gramaticalmente correcta.",
            opciones = listOf(
                "If I knew, I will tell you.",
                "If I had known, I would have told you.",
                "If I know, I would told you.",
                "If I knew, I would have tell you."
            ),
            respuestaCorrecta = "If I had known, I would have told you.",
            dificultad = DIFICIL,
            categoria = INGLES
        ),

        Pregunta(
            enunciado = "¿Cuál es el significado más preciso de \"although\"?",
            opciones = listOf(
                "Por lo tanto",
                "Aunque",
                "A menos que",
                "Mientras tanto"
            ),
            respuestaCorrecta = "Aunque",
            dificultad = DIFICIL,
            categoria = INGLES
        ),

        Pregunta(
            enunciado = "¿Qué forma es correcta para una oración pasiva en presente?",
            opciones = listOf(
                "The book writes by Ana.",
                "The book is written by Ana.",
                "The book was write by Ana.",
                "The book writing by Ana."
            ),
            respuestaCorrecta = "The book is written by Ana.",
            dificultad = DIFICIL,
            categoria = INGLES
        ),

        Pregunta(
            enunciado = "Completa correctamente: \"I ___ swim, but I ___ fly.\"",
            opciones = listOf(
                "can / can't",
                "can't / can",
                "can / can",
                "can't / can't"
            ),
            respuestaCorrecta = "can / can't",
            dificultad = DIFICIL,
            categoria = INGLES
        ),


        // =========================================================
        // MATEMÁTICAS - FÁCIL
        // =========================================================

        Pregunta(
            enunciado = "¿Cuánto es 7 × 8?",
            opciones = listOf(
                "54",
                "56",
                "64",
                "48"
            ),
            respuestaCorrecta = "56",
            dificultad = FACIL,
            categoria = MATEMATICAS
        ),

        Pregunta(
            enunciado = "¿Cuál es el resultado de 15 + 27?",
            opciones = listOf(
                "32",
                "40",
                "42",
                "45"
            ),
            respuestaCorrecta = "42",
            dificultad = FACIL,
            categoria = MATEMATICAS
        ),

        Pregunta(
            enunciado = "¿Cuánto es 100 ÷ 4?",
            opciones = listOf(
                "20",
                "25",
                "30",
                "40"
            ),
            respuestaCorrecta = "25",
            dificultad = FACIL,
            categoria = MATEMATICAS
        ),

        Pregunta(
            enunciado = "¿Cuál es el perímetro de un cuadrado de lado 5 cm?",
            opciones = listOf(
                "10 cm",
                "15 cm",
                "20 cm",
                "25 cm"
            ),
            respuestaCorrecta = "20 cm",
            dificultad = FACIL,
            categoria = MATEMATICAS
        ),

        Pregunta(
            enunciado = "Si x + 7 = 12, ¿cuál es el valor de x?",
            opciones = listOf(
                "3",
                "4",
                "5",
                "6"
            ),
            respuestaCorrecta = "5",
            dificultad = FACIL,
            categoria = MATEMATICAS
        ),

        // =========================================================
        // MATEMÁTICAS - DIFÍCIL
        // =========================================================

        Pregunta(
            enunciado = "Si f(x) = 2x² - 3x + 1, ¿cuál es f(2)?",
            opciones = listOf(
                "1",
                "3",
                "5",
                "7"
            ),
            respuestaCorrecta = "3",
            dificultad = DIFICIL,
            categoria = MATEMATICAS
        ),

        Pregunta(
            enunciado = "¿Cuál es la derivada de x³ - 4x?",
            opciones = listOf(
                "3x² - 4",
                "x² - 4",
                "3x - 4",
                "3x² + 4"
            ),
            respuestaCorrecta = "3x² - 4",
            dificultad = DIFICIL,
            categoria = MATEMATICAS
        ),

        Pregunta(
            enunciado = "¿Cuál es la solución positiva de x² - 5x + 6 = 0?",
            opciones = listOf(
                "1",
                "2",
                "3",
                "6"
            ),
            respuestaCorrecta = "3",
            dificultad = DIFICIL,
            categoria = MATEMATICAS
        ),

        Pregunta(
            enunciado = "¿Cuál es el valor de log₂(32)?",
            opciones = listOf(
                "4",
                "5",
                "6",
                "8"
            ),
            respuestaCorrecta = "5",
            dificultad = DIFICIL,
            categoria = MATEMATICAS
        ),

        Pregunta(
            enunciado = "Si A = [[1, 2], [3, 4]], ¿cuál es el valor de la suma de los elementos de la diagonal principal?",
            opciones = listOf(
                "3",
                "4",
                "5",
                "6"
            ),
            respuestaCorrecta = "5",
            dificultad = DIFICIL,
            categoria = MATEMATICAS
        ),


        // =========================================================
        // ENTRETENIMIENTO - FÁCIL
        // =========================================================

        Pregunta(
            enunciado = "¿Cuál de estos es un superhéroe de Marvel?",
            opciones = listOf(
                "Batman",
                "Spider-Man",
                "Superman",
                "Wonder Woman"
            ),
            respuestaCorrecta = "Spider-Man",
            dificultad = FACIL,
            categoria = ENTRETENIMIENTO
        ),

        Pregunta(
            enunciado = "¿Qué género cinematográfico busca principalmente provocar miedo?",
            opciones = listOf(
                "Comedia",
                "Terror",
                "Romance",
                "Documental"
            ),
            respuestaCorrecta = "Terror",
            dificultad = FACIL,
            categoria = ENTRETENIMIENTO
        ),

        Pregunta(
            enunciado = "¿Cómo se llama la persona que dirige una película?",
            opciones = listOf(
                "Director",
                "Guionista",
                "Editor",
                "Actor"
            ),
            respuestaCorrecta = "Director",
            dificultad = FACIL,
            categoria = ENTRETENIMIENTO
        ),

        Pregunta(
            enunciado = "¿Cómo se llama la escuela de magia a la que asiste Harry Potter?",
            opciones = listOf(
                "Beauxbatons",
                "Durmstrang",
                "Hogwarts",
                "Ilvermorny"
            ),
            respuestaCorrecta = "Hogwarts",
            dificultad = FACIL,
            categoria = ENTRETENIMIENTO
        ),

        Pregunta(
            enunciado = "¿Qué objeto pierde Cenicienta al salir del baile?",
            opciones = listOf(
                "Una corona",
                "Un zapato de cristal",
                "Un collar",
                "Un abanico"
            ),
            respuestaCorrecta = "Un zapato de cristal",
            dificultad = FACIL,
            categoria = ENTRETENIMIENTO
        ),

        // =========================================================
        // ENTRETENIMIENTO - DIFÍCIL
        // =========================================================

        Pregunta(
            enunciado = "¿Qué compositor escribió la Novena Sinfonía conocida por incluir la \"Oda a la alegría\"?",
            opciones = listOf(
                "Mozart",
                "Beethoven",
                "Bach",
                "Vivaldi"
            ),
            respuestaCorrecta = "Beethoven",
            dificultad = DIFICIL,
            categoria = ENTRETENIMIENTO
        ),

        Pregunta(
            enunciado = "¿Quién dirigió la película \"Psycho\" de 1960?",
            opciones = listOf(
                "Stanley Kubrick",
                "Alfred Hitchcock",
                "Orson Welles",
                "Francis Ford Coppola"
            ),
            respuestaCorrecta = "Alfred Hitchcock",
            dificultad = DIFICIL,
            categoria = ENTRETENIMIENTO
        ),

        Pregunta(
            enunciado = "¿Cuál es la especialidad médica principal del Dr. Gregory House en la serie \"House M.D.\"?",
            opciones = listOf(
                "Cardiología",
                "Diagnóstico",
                "Pediatría",
                "Dermatología"
            ),
            respuestaCorrecta = "Diagnóstico",
            dificultad = DIFICIL,
            categoria = ENTRETENIMIENTO
        ),

        Pregunta(
            enunciado = "¿Cómo se llama el padre de Simba en \"El Rey León\"?",
            opciones = listOf(
                "Scar",
                "Mufasa",
                "Timon",
                "Rafiki"
            ),
            respuestaCorrecta = "Mufasa",
            dificultad = DIFICIL,
            categoria = ENTRETENIMIENTO
        ),

        Pregunta(
            enunciado = "¿Cuál de estos directores es conocido por la trilogía cinematográfica \"The Lord of the Rings\"?",
            opciones = listOf(
                "Peter Jackson",
                "Christopher Nolan",
                "James Cameron",
                "Ridley Scott"
            ),
            respuestaCorrecta = "Peter Jackson",
            dificultad = DIFICIL,
            categoria = ENTRETENIMIENTO
        ),


        // =========================================================
        // CULTURA GENERAL - FÁCIL
        // =========================================================

        Pregunta(
            enunciado = "¿Cuál es la capital de Francia?",
            opciones = listOf(
                "Madrid",
                "París",
                "Roma",
                "Lisboa"
            ),
            respuestaCorrecta = "París",
            dificultad = FACIL,
            categoria = CULTURA
        ),

        Pregunta(
            enunciado = "¿Cuál es el océano más grande del planeta?",
            opciones = listOf(
                "Atlántico",
                "Índico",
                "Pacífico",
                "Ártico"
            ),
            respuestaCorrecta = "Pacífico",
            dificultad = FACIL,
            categoria = CULTURA
        ),

        Pregunta(
            enunciado = "¿Cuál es el idioma oficial de Brasil?",
            opciones = listOf(
                "Español",
                "Portugués",
                "Francés",
                "Italiano"
            ),
            respuestaCorrecta = "Portugués",
            dificultad = FACIL,
            categoria = CULTURA
        ),

        Pregunta(
            enunciado = "¿Cuántos días tiene un año común?",
            opciones = listOf(
                "360",
                "365",
                "366",
                "364"
            ),
            respuestaCorrecta = "365",
            dificultad = FACIL,
            categoria = CULTURA
        ),

        Pregunta(
            enunciado = "¿Cuál de estos animales es un mamífero?",
            opciones = listOf(
                "Tiburón",
                "Delfín",
                "Cocodrilo",
                "Águila"
            ),
            respuestaCorrecta = "Delfín",
            dificultad = FACIL,
            categoria = CULTURA
        ),

        // =========================================================
        // CULTURA GENERAL - DIFÍCIL
        // =========================================================

        Pregunta(
            enunciado = "¿Cuál es el río más largo de Europa?",
            opciones = listOf(
                "Danubio",
                "Volga",
                "Rin",
                "Sena"
            ),
            respuestaCorrecta = "Volga",
            dificultad = DIFICIL,
            categoria = CULTURA
        ),

        Pregunta(
            enunciado = "¿En qué país se encuentra la ciudad histórica de Petra?",
            opciones = listOf(
                "Egipto",
                "Jordania",
                "Grecia",
                "Turquía"
            ),
            respuestaCorrecta = "Jordania",
            dificultad = DIFICIL,
            categoria = CULTURA
        ),

        Pregunta(
            enunciado = "¿Cuál es la moneda oficial de Japón?",
            opciones = listOf(
                "Won",
                "Yuan",
                "Yen",
                "Ringgit"
            ),
            respuestaCorrecta = "Yen",
            dificultad = DIFICIL,
            categoria = CULTURA
        ),

        Pregunta(
            enunciado = "¿Qué tratado puso fin oficialmente a la Primera Guerra Mundial con Alemania?",
            opciones = listOf(
                "Tratado de París",
                "Tratado de Versalles",
                "Tratado de Utrecht",
                "Tratado de Tordesillas"
            ),
            respuestaCorrecta = "Tratado de Versalles",
            dificultad = DIFICIL,
            categoria = CULTURA
        ),

        Pregunta(
            enunciado = "¿Cuál es el elemento químico cuyo símbolo es W?",
            opciones = listOf(
                "Wolframio",
                "Wolframita",
                "Westonio",
                "Tungstenoide"
            ),
            respuestaCorrecta = "Wolframio",
            dificultad = DIFICIL,
            categoria = CULTURA
        )
    )

    fun obtenerPreguntas(
        tipoQuiz: String,
        dificultad: String
    ): List<Pregunta> {

        return preguntas
            .filter {
                it.categoria == tipoQuiz &&
                        it.dificultad.equals(
                            dificultad,
                            ignoreCase = true
                        )
            }
            .take(5)
    }
}