package com.example.streamverse

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.streamverse.Model.Movie
import android.widget.Button

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movie = intent.getSerializableExtra("movie") as Movie

        val imgHero = findViewById<ImageView>(R.id.imgM)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvYearDuration = findViewById<TextView>(R.id.tvYearDuration)
        val tvRating = findViewById<TextView>(R.id.tvRating)
        val tvCategory = findViewById<TextView>(R.id.tvCategory)
        val tvDescription = findViewById<TextView>(R.id.tvDescriptionDetail)
        val tvDirector = findViewById<TextView>(R.id.tvDirector)
        val tvCast = findViewById<TextView>(R.id.tvCast)
        val btnTrailer = findViewById<Button>(R.id.btnTrailer)

        // Cargar imagen principal
        Glide.with(this).load(movie.heroImage).into(imgHero)

        tvTitle.text = movie.title
        tvYearDuration.text = "${movie.year} • ${movie.duration}"
        tvRating.text = "⭐ ${movie.rating}"
        tvCategory.text = "Categoría: ${movie.category}"
        tvDescription.text = movie.description
        tvDirector.text = "Director: ${movie.director.trim()}"
        tvCast.text = "Reparto: ${movie.cast.joinToString(", ")}"

        btnTrailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.trailerUrl))
            startActivity(intent)
        }
    }
}