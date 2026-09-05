package com.example.quizmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var autenticacion: FirebaseAuth

    private lateinit var textoBienvenida: TextView
    private lateinit var botonCerrarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        autenticacion = FirebaseAuth.getInstance()

        inicializarComponentes()

        verificarUsuario()

        configurarBotonCerrarSesion()
    }

    private fun inicializarComponentes() {

        textoBienvenida =
            findViewById(R.id.textoBienvenida)

        botonCerrarSesion =
            findViewById(R.id.botonCerrarSesion)
    }

    private fun verificarUsuario() {

        val usuarioActual =
            autenticacion.currentUser

        if (usuarioActual == null) {

            val intent =
                Intent(
                    this,
                    LoginActivity::class.java
                )

            startActivity(intent)

            finish()

            return
        }

        textoBienvenida.text =
            "Bienvenido a QuizMaster\n\n${usuarioActual.email}"
    }

    private fun configurarBotonCerrarSesion() {

        botonCerrarSesion.setOnClickListener {

            autenticacion.signOut()

            val intent =
                Intent(
                    this,
                    LoginActivity::class.java
                )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()
        }
    }
}