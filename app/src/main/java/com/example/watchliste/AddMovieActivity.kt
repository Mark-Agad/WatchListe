package com.example.watchliste

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class AddMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val etTitle = findViewById<TextInputEditText>(R.id.etMovieTitle)
        val tvGenreSelector = findViewById<TextView>(R.id.tvGenreSelector)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        val genresArray = arrayOf("Action", "Comedy", "Drama", "Sci-Fi", "Horror", "Animation", "Fantasy", "Romance")
        val selectedGenres = BooleanArray(genresArray.size)
        val userSelectedList = mutableListOf<Int>()

        tvGenreSelector.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Genres")
            builder.setCancelable(false)

            builder.setMultiChoiceItems(genresArray, selectedGenres) { _, which, isChecked ->
                if (isChecked) {
                    if (!userSelectedList.contains(which)) {
                        userSelectedList.add(which)
                    }
                } else {
                    userSelectedList.remove(which)
                }
            }

            builder.setPositiveButton("OK") { _, _ ->
                val stringBuilder = StringBuilder()
                userSelectedList.sort()
                for (i in 0 until userSelectedList.size) {
                    stringBuilder.append(genresArray[userSelectedList[i]])
                    if (i != userSelectedList.size - 1) {
                        stringBuilder.append(", ")
                    }
                }

                if (stringBuilder.isEmpty()) {
                    tvGenreSelector.text = "Select Genres"
                } else {
                    tvGenreSelector.text = stringBuilder.toString()
                }
            }

            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }

        btnCancel.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            val movieTitle = etTitle.text.toString()
            val movieGenres = tvGenreSelector.text.toString()

            if (movieTitle.isNotEmpty() && movieGenres != "Select Genres") {
                Toast.makeText(this, "$movieTitle added to Watchlist!", Toast.LENGTH_SHORT).show()
                finish()
            } else if (movieTitle.isEmpty()) {
                etTitle.error = "Please enter a title"
            } else {
                Toast.makeText(this, "Please select at least one genre", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}