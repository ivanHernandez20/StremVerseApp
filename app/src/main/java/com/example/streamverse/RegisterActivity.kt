package com.example.streamverse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // RECUPERACIÓN DE DATOS
        val etNombre = findViewById<EditText>(R.id.etNombre);
        val etApaterno = findViewById<EditText>(R.id.etApaterno);
        val etAmaterno = findViewById<EditText>(R.id.etAmaterno);
        val etCorreo = findViewById<EditText>(R.id.etCorreo);
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar);
        val tvInicio = findViewById<TextView>(R.id.tvLogin)

        // MANEJO DEL TOKEN

        // CUANDO SE PRESIONA EL BOTON LOGIN
        tvInicio.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}