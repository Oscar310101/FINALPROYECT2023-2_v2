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

class RegisterEmpresaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_empresa)

        val Postular_vol1: ImageView = findViewById(R.id.Postular2)
        val Cancelar_vol1: ImageView = findViewById(R.id.Cancelar2)

        Cancelar_vol1.setOnClickListener {
           startActivity(Intent(this,DetailRegisterActivity::class.java))
        }

        val txtEmpresa: EditText = findViewById(R.id.txtOrganizacion)
        val txtRuc: EditText = findViewById(R.id.txtRUC)
        val txtTelefonoEmpresa: EditText = findViewById(R.id.txtTelefonoEmpresa)
        val txtGmailEm: EditText = findViewById(R.id.txtEmailEmpresa)
        val txtPasswordEm: EditText = findViewById(R.id.txtPasswordEmpresa)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("userEmpresa")

        Postular_vol1.setOnClickListener{
            val email = txtGmailEm.text.toString()
            val password = txtPasswordEm.text.toString()
            val Empresa = txtEmpresa.text.toString()
            val RUC = txtRuc.text.toString()
            val Telefono = txtTelefonoEmpresa.text.toString()

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){

                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid

                        val userModel = UserPostulanteModel(email,password,Empresa,RUC,Telefono,uid.toString())
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