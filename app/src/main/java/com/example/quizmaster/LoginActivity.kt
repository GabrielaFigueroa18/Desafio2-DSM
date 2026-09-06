package com.example.quizmaster

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {

    private lateinit var autenticacion: FirebaseAuth

    private lateinit var campoCorreo: EditText
    private lateinit var campoContrasena: EditText
    private lateinit var botonIniciarSesion: Button
    private lateinit var botonIrRegistro: Button
    private lateinit var textoErrorLogin: TextView
    private lateinit var progresoLogin: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        autenticacion = FirebaseAuth.getInstance()

        inicializarComponentes()
        configurarBotones()
        verificarSesion()
    }

    private fun inicializarComponentes() {

        campoCorreo = findViewById(R.id.campoCorreo)
        campoContrasena = findViewById(R.id.campoContrasena)

        botonIniciarSesion =
            findViewById(R.id.botonIniciarSesion)

        botonIrRegistro =
            findViewById(R.id.botonIrRegistro)

        textoErrorLogin =
            findViewById(R.id.textoErrorLogin)

        progresoLogin =
            findViewById(R.id.progresoLogin)
    }

    private fun configurarBotones() {

        botonIniciarSesion.setOnClickListener {
            iniciarSesion()
        }

        botonIrRegistro.setOnClickListener {

            val intent = Intent(
                this,
                RegistroActivity::class.java
            )

            startActivity(intent)
        }
    }

    private fun verificarSesion() {

        if (autenticacion.currentUser != null) {

            val intent = Intent(
                this,
                BienvenidaActivity::class.java
            )

            startActivity(intent)
            finish()
        }
    }

    private fun iniciarSesion() {

        val correo =
            campoCorreo.text.toString().trim()

        val contrasena =
            campoContrasena.text.toString()

        ocultarError()

        if (!validarDatos(correo, contrasena)) {
            return
        }

        mostrarCargando()

        autenticacion
            .signInWithEmailAndPassword(
                correo,
                contrasena
            )
            .addOnCompleteListener(this) { tarea ->

                ocultarCargando()

                if (tarea.isSuccessful) {

                    val intent = Intent(
                        this,
                        BienvenidaActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                    finish()

                } else {

                    mostrarError(
                        obtenerMensajeError(
                            tarea.exception
                        )
                    )
                }
            }
    }

    private fun validarDatos(
        correo: String,
        contrasena: String
    ): Boolean {

        if (correo.isEmpty()) {

            mostrarError(
                "Ingrese su correo electrónico."
            )

            campoCorreo.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS
                .matcher(correo)
                .matches()
        ) {

            mostrarError(
                "Ingrese un correo electrónico válido."
            )

            campoCorreo.requestFocus()
            return false
        }

        if (contrasena.isEmpty()) {

            mostrarError(
                "Ingrese su contraseña."
            )

            campoContrasena.requestFocus()
            return false
        }

        if (contrasena.length < 6) {

            mostrarError(
                "La contraseña debe tener al menos 6 caracteres."
            )

            campoContrasena.requestFocus()
            return false
        }

        return true
    }

    private fun obtenerMensajeError(
        exception: Exception?
    ): String {

        return when (exception) {

            is FirebaseAuthInvalidUserException ->
                "No existe una cuenta con este correo electrónico."

            is FirebaseAuthInvalidCredentialsException ->
                "El correo o la contraseña son incorrectos."

            else ->
                "No se pudo iniciar sesión. Verifique sus datos e inténtelo nuevamente."
        }
    }

    private fun mostrarError(mensaje: String) {

        textoErrorLogin.text = mensaje
        textoErrorLogin.visibility = View.VISIBLE
    }

    private fun ocultarError() {

        textoErrorLogin.text = ""
        textoErrorLogin.visibility = View.GONE
    }

    private fun mostrarCargando() {

        progresoLogin.visibility = View.VISIBLE

        botonIniciarSesion.isEnabled = false
        botonIrRegistro.isEnabled = false
    }

    private fun ocultarCargando() {

        progresoLogin.visibility = View.GONE

        botonIniciarSesion.isEnabled = true
        botonIrRegistro.isEnabled = true
    }
}