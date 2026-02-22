package com.example.streamverse

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.streamverse.Api.RetrofitClient
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.content.Intent
import com.example.streamverse.Model.LoginRequest

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // RECUPERACIÓN DE DATOS
        val etCorreo = findViewById<EditText>(R.id.etEmail)
        val etContrasena = findViewById<EditText>(R.id.etPassword)
        val btnRecuperarPass = findViewById<TextView>(R.id.tvRecuperarPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        // Almacenamiento local donde se guardará el token
        val LocalStorage = getSharedPreferences("app_prefs", MODE_PRIVATE)

        // CUANDO SE DA CLICK AL BOTON INGRESAR
        btnLogin.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val password = etContrasena.text.toString().trim()

            // SI HAY CAMPOS VACIOS MOSTRAR UN MENSAJE
            if(correo.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try{
                    val response = RetrofitClient.api.login(LoginRequest(correo, password))
                    if(response.isSuccessful){
                        val loginResponse = response.body()
                        loginResponse?.let {
                            // GUARDAMOS EL TOKEN
                            LocalStorage.edit().putString("TOKEN", it.token).apply()
                            // MENSAJE DE BIENVENIDA
                            Toast.makeText(this@LoginActivity, "Bienvenido ${it.user.firstName}", Toast.LENGTH_SHORT).show()

                            // NOS REDIRIGIMOS AL MENU PRINCIPAL
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }else{
                        Toast.makeText(this@LoginActivity, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }catch(e: Exception){
                    Toast.makeText(this@LoginActivity, "Error de Conexión", Toast.LENGTH_SHORT).show()
                }
            }

        }

        // CUANDO SE DA CLICK AL BOTON RECUPERAR CONTRASEÑA
        btnRecuperarPass.setOnClickListener {
            val intent = Intent(this, RecoverPasswordActivity::class.java)
            startActivity(intent)
        }

    }
}