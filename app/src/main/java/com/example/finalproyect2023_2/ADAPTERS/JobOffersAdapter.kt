package com.example.finalproyect2023_2.ADAPTERS

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproyect2023_2.ACTIVITIES.DetailOffersActivity
import com.example.finalproyect2023_2.ENTITIES.JobOffer
import com.example.finalproyect2023_2.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class JobOffersAdapter(options: FirestoreRecyclerOptions<JobOffer>,
                       private val context: Context
) :
    FirestoreRecyclerAdapter<JobOffer, JobOffersAdapter.JobOfferViewHolder>(options) {

    inner class JobOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val fechaPublicacionTextView: TextView = itemView.findViewById(R.id.fechapublicacionTextView)


        fun bind(jobOffer: JobOffer) {
            titleTextView.text = jobOffer.title
            descriptionTextView.text = jobOffer.description
            fechaPublicacionTextView.text = jobOffer.fechaPublicacion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobOfferViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_job_offer, parent, false)
        return JobOfferViewHolder(view)
    }
    override fun onBindViewHolder(holder: JobOfferViewHolder, position: Int, model: JobOffer) {
        holder.bind(model)

        // Configurar el click del elemento del RecyclerView
        holder.itemView.setOnClickListener {
            // Abrir el DetalleOfertaActivity con el objeto JobOffer
            val intent = Intent(context, DetailOffersActivity::class.java)
            intent.putExtra("oferta", model)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(intent)
        }


    }
    inline fun <reified T : Parcelable> Intent.putParcelableExtra(key: String, value: T) {
        val bundle = Bundle().apply { putParcelable(key, value) }
        putExtras(bundle)
    }

}
