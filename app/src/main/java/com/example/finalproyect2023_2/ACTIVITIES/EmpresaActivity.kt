package com.example.finalproyect2023_2.ACTIVITIES

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.finalproyect2023_2.ADAPTERS.JobOffersAdapter
import com.example.finalproyect2023_2.ENTITIES.JobOffer
import com.example.finalproyect2023_2.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class EmpresaActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: JobOffersAdapter
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa)

        viewPager = findViewById(R.id.viewpager2)
        firestore = FirebaseFirestore.getInstance()

        val query = firestore.collection("job_offers").orderBy("timestamp", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<JobOffer>()
            .setQuery(query, JobOffer::class.java)
            .build()

        adapter = JobOffersAdapter(options, applicationContext)
        viewPager.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}