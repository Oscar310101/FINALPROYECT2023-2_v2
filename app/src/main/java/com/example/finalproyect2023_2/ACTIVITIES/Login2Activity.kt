package com.example.finalproyect2023_2.ACTIVITIES

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.finalproyect2023_2.JobOffersActivity
import com.example.finalproyect2023_2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Login2Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var buttonLogin: Button
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var spinnerUserType: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        //----------------------
        buttonLogin = findViewById(R.id.buttonLogin)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        spinnerUserType = findViewById(R.id.spinnerUserType)

        // Configurar el adaptador para el spinner
        val adapter = ArrayAdapter.createFromResource(this, R.array.user_types, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUserType.adapter = adapter







        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val userType = spinnerUserType.selectedItem.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Verificar si hay un usuario autenticado
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    // El usuario ya está autenticado, redirigir según el tipo de usuario seleccionado
                    redirectUser(userType)
                } else {
                    // Iniciar sesión con Firebase Auth
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Verificar si el usuario existe en la colección "postulante" de Firestore
                                val userId = auth.currentUser?.uid
                                if (userId != null) {
                                    val firestore = FirebaseFirestore.getInstance()
                                    val postulanteRef = firestore.collection("postulante").document(userId)
                                    postulanteRef.get()
                                        .addOnSuccessListener { document ->
                                            if (document.exists()) {
                                                // El usuario existe en la colección "postulante", redirigir a la actividad de postulante
                                                redirectUser("Postulante")
                                            } else {
                                                // El usuario no existe en la colección "postulante"
                                                Toast.makeText(
                                                    this,
                                                    "Usuario no válido",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(
                                                this,
                                                "Error al consultar la base de datos",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                }
                            } else {
                                Toast.makeText(
                                    this,
                                    "Inicio de sesión fallido",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
    }

    private fun redirectUser(userType: String) {
        if (userType == "Postulante") {
            // Redirigir al usuario a la actividad de postulante
            val intent = Intent(this, JobOffersActivity::class.java)
            startActivity(intent)
            finish()
        } else if (userType == "Empresa") {
            // Redirigir al usuario a la actividad de empresa
            val intent = Intent(this, EmpresaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}