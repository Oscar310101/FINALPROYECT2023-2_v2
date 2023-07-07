package com.example.finalproyect2023_2.ACTIVITIES

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.finalproyect2023_2.R

class RegisterEmpresaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_empresa)

        val Postular_vol1: ImageView = findViewById(R.id.Postular2)
        val Cancelar_vol1: ImageView = findViewById(R.id.Volver2)

        Cancelar_vol1.setOnClickListener {
            startActivity(Intent(this,DetailRegisterActivity::class.java))
        }
    }
}