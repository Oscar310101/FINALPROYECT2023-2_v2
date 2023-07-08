package com.example.finalproyect2023_2.ACTIVITIES

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.finalproyect2023_2.R

class DetailRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_register)


        val btnRVoluntario = findViewById<Button>(R.id.btnDVoluntario)
        val btnREmpresa = findViewById<Button>(R.id.btnDEmpresa)

        btnRVoluntario.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        btnREmpresa.setOnClickListener{
            startActivity(Intent(this,RegisterEmpresaActivity::class.java))
        }

        val Cancelar: ImageView = findViewById(R.id.Cancelar)

        Cancelar.setOnClickListener {
            startActivity(Intent(this,Login2Activity::class.java))
        }

    }

}