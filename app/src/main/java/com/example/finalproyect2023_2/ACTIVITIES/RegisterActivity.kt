package com.example.finalproyect2023_2.ACTIVITIES

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.finalproyect2023_2.ENTITIES.UserPostulanteModel
import com.example.finalproyect2023_2.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val Postular_vol: ImageView = findViewById(R.id.Postular1)
        val Cancelar_vol: ImageView = findViewById(R.id.Volver1)

        Cancelar_vol.setOnClickListener {
            startActivity(Intent(this,DetailRegisterActivity::class.java))
        }


        val txtNombre: EditText = findViewById(R.id.txtVoluntario)
        val txtApellido: EditText = findViewById(R.id.txtApellido)
        val txtTelefonoPos: EditText = findViewById(R.id.txtVoluntarioTelefono)
        val txtGmailPos: EditText = findViewById(R.id.txtEmailVoluntario)
        val txtPasswordPos: EditText = findViewById(R.id.txtPasswordVoluntario)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("userPostulante")


        Postular_vol.setOnClickListener{
            val email = txtGmailPos.text.toString()
            val password = txtPasswordPos.text.toString()
            val Nombre = txtNombre.text.toString()
            val Apellido = txtApellido.text.toString()
            val Telefono = txtTelefonoPos.text.toString()

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){

                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid

                        val userModel = UserPostulanteModel(email,password,Nombre,Apellido,uid.toString())
                        collectionRef.add(userModel)
                            .addOnSuccessListener { documentReference ->

                            }.addOnFailureListener{error ->
                                Snackbar
                                    .make(
                                        findViewById(android.R.id.content),
                                        "Error al registrar: $error",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                            }
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Registro exitoso: $uid",
                                Snackbar.LENGTH_LONG
                            ).show()
                        startActivity(Intent(this,Login2Activity::class.java))
                    }
                }


        }

    }
    }
