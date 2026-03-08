package com.example.watchliste

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class AddMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val etTitle = findViewById<TextInputEditText>(R.id.etMovieTitle)
        val spinnerGenre = findViewById<Spinner>(R.id.spinnerGenre)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val genres = arrayOf("Action", "Comedy", "Drama", "Sci-Fi", "Horror", "Animation")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGenre.adapter = adapter

        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }
        btnSave.setOnClickListener {
            val movieTitle = etTitle.text.toString()

            if (movieTitle.isNotEmpty()) {
                Toast.makeText(this, "$movieTitle added to Watchlist!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                etTitle.error = "Please enter a title"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}