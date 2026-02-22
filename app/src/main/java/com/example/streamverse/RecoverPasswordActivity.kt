package com.example.streamverse

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.streamverse.Api.RetrofitClient
import com.example.streamverse.Model.ResetPasswordRequest
import kotlinx.coroutines.launch

class RecoverPasswordActivity : AppCompatActivity() {

    private lateinit var etEmail : EditText
    private lateinit var btnReset : Button
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        etEmail = findViewById(R.id.etCorreo)
        btnReset = findViewById(R.id.btnEnviar)
        progressBar = findViewById(R.id.progressBar)

        btnReset.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if(email.isEmpty()){
                Toast.makeText(this, "Ingresa tu correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            resetPassword(email)
        }
    }

    private fun resetPassword(email: String){
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try{
                val response = RetrofitClient.api.resetPassword(ResetPasswordRequest(email))
                progressBar.visibility = View.GONE
                if(response.isSuccessful && response.body()?.success == true){
                    Toast.makeText(this@RecoverPasswordActivity, "Revisa tu correo para continuar", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@RecoverPasswordActivity, "Error al enviar solicitud", Toast.LENGTH_SHORT).show()
                }
            }catch (e: Exception){
                progressBar.visibility = View.GONE
                Toast.makeText(this@RecoverPasswordActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        }
    }
}