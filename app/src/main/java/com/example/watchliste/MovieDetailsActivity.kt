package com.example.watchliste

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra("MOVIE_TITLE") ?: "Unknown Movie"
        val genre = intent.getStringExtra("MOVIE_GENRE")
        val desc = intent.getStringExtra("MOVIE_DESC")
        val existingRating = intent.getStringExtra("MOVIE_RATING")

        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvDesc = findViewById<TextView>(R.id.tvDescription)
        val rbUserRating = findViewById<RatingBar>(R.id.rbUserRating)
        val btnMarkWatched = findViewById<Button>(R.id.btnMarkWatched)
        val btnBack = findViewById<Button>(R.id.btnBack)

        tvTitle.text = title
        tvDesc.text = "Genre: $genre\n\n$desc"

        if (!existingRating.isNullOrEmpty()) {
            rbUserRating.rating = existingRating.toFloat()
            rbUserRating.setIsIndicator(true)

            btnMarkWatched.text = "WATCHED"
            btnMarkWatched.isEnabled = false
            btnMarkWatched.alpha = 0.5f
        }

        btnMarkWatched.setOnClickListener {
            val userRating = rbUserRating.rating

            if (userRating > 0) {
                Toast.makeText(this, "Rated $title $userRating/5! Moved to Watched.", Toast.LENGTH_LONG).show()

                btnMarkWatched.text = "WATCHED"
                btnMarkWatched.isEnabled = false
                btnMarkWatched.alpha = 0.5f
                rbUserRating.setIsIndicator(true)
            } else {
                Toast.makeText(this, "Please select a rating first!", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}