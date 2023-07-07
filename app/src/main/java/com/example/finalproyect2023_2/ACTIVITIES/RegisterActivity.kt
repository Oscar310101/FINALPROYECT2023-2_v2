package com.example.finalproyect2023_2.ACTIVITIES

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.finalproyect2023_2.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val Postular_vol: ImageView = findViewById(R.id.Postular2)
        val Cancelar_vol: ImageView = findViewById(R.id.Volver2)

        Cancelar_vol.setOnClickListener {
            startActivity(Intent(this,DetailRegisterActivity::class.java))
        }

    }
}