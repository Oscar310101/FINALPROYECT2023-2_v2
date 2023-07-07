package com.example.finalproyect2023_2.ADAPTERS

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproyect2023_2.ENTITIES.Postulacion
import com.example.finalproyect2023_2.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class PostulacionesAdapter(options: FirestoreRecyclerOptions<Postulacion>) :
    FirestoreRecyclerAdapter<Postulacion, PostulacionesAdapter.PostulacionViewHolder>(options) {

    inner class PostulacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val empresaTextView: TextView = itemView.findViewById(R.id.idEmpresaTextView)

        fun bind(postulacion: Postulacion) {
            nombreTextView.text = postulacion.nombre
            empresaTextView.text = postulacion.fechaPostulacion.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostulacionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_postulacion, parent, false)
        return PostulacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostulacionViewHolder, position: Int, model: Postulacion) {
        holder.bind(model)
    }
}
