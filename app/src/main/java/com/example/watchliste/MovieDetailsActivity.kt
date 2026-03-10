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


        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvDesc = findViewById<TextView>(R.id.tvDescription)
        val rbUserRating = findViewById<RatingBar>(R.id.rbUserRating)
        val btnMarkWatched = findViewById<Button>(R.id.btnMarkWatched)
        val btnBack = findViewById<Button>(R.id.btnBack)


        tvTitle.text = title

        tvDesc.text = "Genre: $genre\n\n$desc"


        btnMarkWatched.setOnClickListener {
            val userRating = rbUserRating.rating.toInt()

            if (userRating > 0) {

                Toast.makeText(this, "Rated $title $userRating/10! Moved to Watched.", Toast.LENGTH_LONG).show()


                btnMarkWatched.text = "WATCHED ✓"
                btnMarkWatched.isEnabled = false
                btnMarkWatched.alpha = 0.5f
            } else {

                Toast.makeText(this, "Please select a rating first!", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onSupportNavigateUp()
        return true
    }
}