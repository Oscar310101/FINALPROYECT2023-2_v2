package com.example.finalproyect2023_2.ACTIVITIES

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.finalproyect2023_2.ENTITIES.JobOffer
import com.example.finalproyect2023_2.ENTITIES.Postulacion
import com.example.finalproyect2023_2.ENTITIES.Postulante
import com.example.finalproyect2023_2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*



class DetailOffersActivity : AppCompatActivity() {
    private lateinit var tituloTextView: TextView
    private lateinit var descripcionTextView: TextView
    private lateinit var postularButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_offers)

        // Obtener referencias de los elementos de la interfaz de usuario
        tituloTextView = findViewById(R.id.tituloTextView)
        descripcionTextView = findViewById(R.id.descripcionTextView)
        postularButton = findViewById(R.id.postularButton)

        // Obtener el índice de la oferta seleccionada desde el intent
        val oferta: JobOffer? = intent.getParcelableExtra("oferta")

        tituloTextView.setText( oferta?.title)
        descripcionTextView.setText(oferta?.description)



        // Configurar el clic del botón de postulación
        postularButton.setOnClickListener {
            // Lógica para postularse a la oferta

            // Obtener el ID del usuario actual logueado (puedes usar Firebase Auth o cualquier otro método de autenticación)
            val userId = obtenerIdUsuarioActual()

            // Obtener la fecha de postulación actual
            val fechaPostulacion = obtenerFechaActual()

            // Obtener el nombre de la persona que postula (puedes obtenerlo del perfil del usuario logueado)
            val nombrePersonaPostula = obtenerNombrePersonaPostula()

            // Crear la instancia de la clase Postulante con los datos obtenidos
            val postulante = Postulante(UUID.randomUUID().toString(), nombrePersonaPostula, "@carril el chef")

            // Guardar la postulación en la base de datos
            oferta?.let { it1 -> guardarPostulacion(postulante, it1) }
        }



    }

    private fun obtenerOfertaPorIndice(indice: String , callback: (JobOffer?) -> Unit) {

        val ofertaId = "oferta${indice + 1}"
        val db = FirebaseFirestore.getInstance()
        val ofertaRef = db.collection("ofertas").document(ofertaId)

        // Obtener la oferta desde Firestore
        ofertaRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                // La oferta existe en Firestore
                val oferta = documentSnapshot.toObject(JobOffer::class.java)
                // Verificar si se obtuvo correctamente la oferta
                if (oferta != null) {
                    // Pasar la oferta obtenida al callback
                    callback(oferta)
                }
            } else {
                // La oferta no existe en Firestore
                // Pasar null al callback para indicar que no se encontró la oferta
                callback(null)
            }
        }.addOnFailureListener { e ->
            // Hubo un error al obtener la oferta desde Firestore
            // Manejar el error de acuerdo a tus necesidades
            callback(null)
        }
    }


    private fun obtenerIdUsuarioActual(): String {
        // Implementar la lógica para obtener el ID del usuario actual logueado
        // Por ejemplo, si estás utilizando Firebase Auth, puedes obtener el ID así:
        val currentUser = FirebaseAuth.getInstance().currentUser
        return currentUser?.uid ?: ""
    }

    private fun obtenerFechaActual(): Date {
        // Implementar la lógica para obtener la fecha actual
        return Calendar.getInstance().time
    }

    private fun obtenerNombrePersonaPostula(): String {
        // Implementar la lógica para obtener el nombre de la persona que postula
        // Por ejemplo, si tienes los datos del usuario logueado en una variable llamada "usuario":
        val usuario = obtenerUsuarioLogueado() // Obtener el usuario logueado de alguna manera
        return usuario?.nombre ?: ""
    }


    private fun guardarPostulacion(postulante: Postulante, oferta: JobOffer) {
        // Crear una nueva instancia de la clase Postulacion con los datos obtenidos
        val postulacion = Postulacion(
            UUID.randomUUID().toString(),
            postulante.nombre,
            obtenerFechaActual().toString(),
            "Detalles de la postulación",
            oferta.id,
            postulante.id
        )

        // Guardar la postulación en la colección "Postulaciones" en Firestore
        val db = FirebaseFirestore.getInstance()
        db.collection("Postulaciones")
            .document(postulacion.idPostulacion)
            .set(postulacion)
            .addOnSuccessListener {
                Toast.makeText(
                    applicationContext,
                    "La postulación se guardó correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    applicationContext,
                    "Error al guardar la postulación: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    private fun obtenerTimestampActual(): com.google.firebase.Timestamp {
        // Implementar la lógica para obtener el timestamp actual
        return com.google.firebase.Timestamp.now()
    }

    private fun obtenerUsuarioLogueado(): Postulante? {
        // Obtener el usuario actual logueado utilizando Firebase Authentication
        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        // Verificar si el usuario está logueado
        if (firebaseUser != null) {
            // Obtener los datos del usuario
            val userId: String = firebaseUser.uid
            val nombre: String = firebaseUser.displayName ?: ""
            val email: String = firebaseUser.email ?: ""

            // Crear y retornar el objeto Postulante con los datos obtenidos
            return Postulante(userId,nombre, email)
        }

        // Si no hay un usuario logueado, retornar null o manejarlo de acuerdo a tus necesidades
        return Postulante("dhiuhsuidhuihds","Carrill", "Carril@gmail.com")
    }


}
