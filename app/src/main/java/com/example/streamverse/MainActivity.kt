package com.example.streamverse

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.streamverse.Adapter.MovieAdapter
import com.example.streamverse.Api.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val token = getSharedPreferences("app_prefs", MODE_PRIVATE).getString("TOKEN", null)
            if(token == null){
                finish()
            }
            val response = RetrofitClient.api.getMovies("Bearer $token")
            if(response.isSuccessful){
                val movies = response.body() ?: emptyList()
                recyclerView.adapter = MovieAdapter(movies)
            }
        }

    }

    override fun onDestroy(){
        super.onDestroy()
        val sharedPref = getSharedPreferences("app_prefs", MODE_PRIVATE)
        sharedPref.edit().remove("TOKEN").apply()
    }
}