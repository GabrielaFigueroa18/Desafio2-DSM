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
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.UserProfileChangeRequest

class RegistroActivity : AppCompatActivity() {

    private lateinit var autenticacion: FirebaseAuth

    private lateinit var campoNombre: EditText
    private lateinit var campoApellido: EditText
    private lateinit var campoCorreoRegistro: EditText
    private lateinit var campoContrasenaRegistro: EditText
    private lateinit var campoConfirmarContrasena: EditText

    private lateinit var botonRegistrarse: Button
    private lateinit var botonVolverLogin: Button
    private lateinit var textoErrorRegistro: TextView
    private lateinit var progresoRegistro: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registro)

        autenticacion =
            FirebaseAuth.getInstance()

        inicializarComponentes()
        configurarBotones()
    }

    private fun inicializarComponentes() {

        campoNombre =
            findViewById(R.id.campoNombre)

        campoApellido =
            findViewById(R.id.campoApellido)

        campoCorreoRegistro =
            findViewById(R.id.campoCorreoRegistro)

        campoContrasenaRegistro =
            findViewById(R.id.campoContrasenaRegistro)

        campoConfirmarContrasena =
            findViewById(R.id.campoConfirmarContrasena)

        botonRegistrarse =
            findViewById(R.id.botonRegistrarse)

        botonVolverLogin =
            findViewById(R.id.botonVolverLogin)

        textoErrorRegistro =
            findViewById(R.id.textoErrorRegistro)

        progresoRegistro =
            findViewById(R.id.progresoRegistro)
    }

    private fun configurarBotones() {

        botonRegistrarse.setOnClickListener {
            registrarUsuario()
        }

        botonVolverLogin.setOnClickListener {

            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            startActivity(intent)
            finish()
        }
    }

    private fun registrarUsuario() {

        val nombre =
            campoNombre.text.toString().trim()

        val apellido =
            campoApellido.text.toString().trim()

        val correo =
            campoCorreoRegistro.text
                .toString()
                .trim()

        val contrasena =
            campoContrasenaRegistro.text
                .toString()

        val confirmarContrasena =
            campoConfirmarContrasena.text
                .toString()

        ocultarError()

        if (!validarDatos(
                nombre,
                apellido,
                correo,
                contrasena,
                confirmarContrasena
            )
        ) {
            return
        }

        mostrarCargando()

        autenticacion
            .createUserWithEmailAndPassword(
                correo,
                contrasena
            )
            .addOnCompleteListener(this) { tarea ->

                if (tarea.isSuccessful) {

                    guardarNombreUsuario(
                        nombre,
                        apellido
                    )

                } else {

                    ocultarCargando()

                    mostrarError(
                        obtenerMensajeError(
                            tarea.exception
                        )
                    )
                }
            }
    }

    private fun guardarNombreUsuario(
        nombre: String,
        apellido: String
    ) {

        val usuarioActual =
            autenticacion.currentUser

        if (usuarioActual == null) {

            ocultarCargando()

            mostrarError(
                "No se pudo obtener el usuario registrado."
            )

            return
        }

        val nombreCompleto =
            "$nombre $apellido"

        val actualizacionPerfil =
            UserProfileChangeRequest.Builder()
                .setDisplayName(nombreCompleto)
                .build()

        usuarioActual
            .updateProfile(actualizacionPerfil)
            .addOnCompleteListener { tarea ->

                ocultarCargando()

                if (tarea.isSuccessful) {

                    autenticacion.signOut()

                    val intent = Intent(
                        this,
                        LoginActivity::class.java
                    )

                    intent.putExtra(
                        "registroExitoso",
                        true
                    )

                    startActivity(intent)
                    finish()

                } else {

                    mostrarError(
                        "La cuenta fue creada, pero no se pudo guardar el nombre."
                    )
                }
            }
    }

    private fun validarDatos(
        nombre: String,
        apellido: String,
        correo: String,
        contrasena: String,
        confirmarContrasena: String
    ): Boolean {

        if (nombre.isEmpty()) {

            mostrarError(
                "Ingrese su nombre."
            )

            campoNombre.requestFocus()

            return false
        }

        if (nombre.length < 2) {

            mostrarError(
                "El nombre debe tener al menos 2 caracteres."
            )

            campoNombre.requestFocus()

            return false
        }

        if (apellido.isEmpty()) {

            mostrarError(
                "Ingrese su apellido."
            )

            campoApellido.requestFocus()

            return false
        }

        if (apellido.length < 2) {

            mostrarError(
                "El apellido debe tener al menos 2 caracteres."
            )

            campoApellido.requestFocus()

            return false
        }

        if (correo.isEmpty()) {

            mostrarError(
                "Ingrese su correo electrónico."
            )

            campoCorreoRegistro.requestFocus()

            return false
        }

        if (!Patterns.EMAIL_ADDRESS
                .matcher(correo)
                .matches()
        ) {

            mostrarError(
                "Ingrese un correo electrónico válido."
            )

            campoCorreoRegistro.requestFocus()

            return false
        }

        if (contrasena.isEmpty()) {

            mostrarError(
                "Ingrese una contraseña."
            )

            campoContrasenaRegistro.requestFocus()

            return false
        }

        if (contrasena.length < 6) {

            mostrarError(
                "La contraseña debe tener al menos 6 caracteres."
            )

            campoContrasenaRegistro.requestFocus()

            return false
        }

        if (confirmarContrasena.isEmpty()) {

            mostrarError(
                "Confirme su contraseña."
            )

            campoConfirmarContrasena.requestFocus()

            return false
        }

        if (contrasena != confirmarContrasena) {

            mostrarError(
                "Las contraseñas no coinciden."
            )

            campoConfirmarContrasena.requestFocus()

            return false
        }

        return true
    }

    private fun obtenerMensajeError(
        exception: Exception?
    ): String {

        return when (exception) {

            is FirebaseAuthUserCollisionException ->
                "Ya existe una cuenta registrada con este correo."

            is FirebaseAuthWeakPasswordException ->
                "La contraseña es demasiado débil."

            else ->
                "No se pudo crear la cuenta. Verifique su conexión e inténtelo nuevamente."
        }
    }

    private fun mostrarError(
        mensaje: String
    ) {

        textoErrorRegistro.text =
            mensaje

        textoErrorRegistro.visibility =
            View.VISIBLE
    }

    private fun ocultarError() {

        textoErrorRegistro.text = ""

        textoErrorRegistro.visibility =
            View.GONE
    }

    private fun mostrarCargando() {

        progresoRegistro.visibility =
            View.VISIBLE

        botonRegistrarse.isEnabled =
            false

        botonVolverLogin.isEnabled =
            false
    }

    private fun ocultarCargando() {

        progresoRegistro.visibility =
            View.GONE

        botonRegistrarse.isEnabled =
            true

        botonVolverLogin.isEnabled =
            true
    }
}