package com.example.quizmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var botonIniciarSesion: Button
    private lateinit var botonRegistrarme: Button
    private lateinit var autenticacion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        autenticacion = FirebaseAuth.getInstance()

        // Si ya hay sesión iniciada, no mostrar inicio público
        if (autenticacion.currentUser != null) {

            val intent = Intent(
                this,
                BienvenidaActivity::class.java
            )

            startActivity(intent)
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        inicializarComponentes()
        configurarBotones()
    }

    private fun inicializarComponentes() {

        botonIniciarSesion =
            findViewById(R.id.botonIniciarSesion)

        botonRegistrarme =
            findViewById(R.id.botonRegistrarme)
    }

    private fun configurarBotones() {

        botonIniciarSesion.setOnClickListener {

            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            startActivity(intent)
        }

        botonRegistrarme.setOnClickListener {

            val intent = Intent(
                this,
                RegistroActivity::class.java
            )

            startActivity(intent)
        }
    }
}