package com.example.watchliste

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class WatchlistActivity : AppCompatActivity() {

    val watchlistMovies = mutableListOf(
        Movie("Spider-Man", "Animation", "", "A story about Miles Morales discovering the multiverse."),
        Movie("The Dark Knight", "Action", "", "Batman faces his greatest psychological test against the Joker."),
        Movie("Interstellar", "Sci-Fi", "", "A team of explorers travel through a wormhole to save humanity."),
        Movie("Kung Fu Panda", "Animation", "", "Po the panda is chosen as the Dragon Warrior."),
        Movie("Kung Fu Panda 2", "Animation", "", "Po and his friends fight to stop a villain with a weapon."),
        Movie("Kung Fu Panda 3", "Animation", "", "Po must train a village of clumsy pandas."),
        Movie("The Wild Robot", "Animation", "", "A shipwrecked robot discovers what it means to be alive."),
        Movie("Cars", "Animation", "", "A hotshot race car learns there is more to life than winning."),
        Movie("Cars 2", "Animation", "", "Lightning McQueen heads overseas for the Grand Prix."),
        Movie("Cars 3", "Animation", "", "Lightning McQueen proves he's still the best."),
        Movie("9", "Sci-Fi/Animation", "", "A rag doll awakens in a post-apocalyptic world."),
        Movie("The Legend of Hei", "Animation", "", "A young cat spirit goes on a journey to find a new home.")
    )

    val watchedMovies = mutableListOf(
        Movie("3 Idiots", "Comedy/Drama", "4.9", "Two friends search for their long-lost companion."),
        Movie("Inception", "Sci-Fi", "4.8", "A thief who steals secrets through dream-sharing."),
        Movie("The Lion King", "Animation", "4.9", "The king of the pride lands."),
        Movie("Howl's Moving Castle", "Animation/Fantasy", "5.0", "A young woman is cursed with an old body by a spiteful witch."),
        Movie("The Wind Rises", "Animation/Drama", "5.0", "A look at the life of Jiro Horikoshi, the man who designed Japanese fighter planes during World War II.")
    )

    private var currentMainList = watchlistMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rvWatchlist = findViewById<RecyclerView>(R.id.rvWatchlist)
        val tvHeader = findViewById<TextView>(R.id.tvHeader)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val spinnerFilter = findViewById<Spinner>(R.id.spinnerGenreFilter)

        bottomNav.selectedItemId = R.id.nav_watchlist
        rvWatchlist.layoutManager = LinearLayoutManager(this)

        val genres = arrayOf("All Genres", "Action", "Animation", "Comedy", "Drama", "Sci-Fi", "Fantasy")

        // FIXED: Using your custom layout R.layout.spinner_item for both views
        val filterAdapter = ArrayAdapter(this, R.layout.spinner_item, genres)
        filterAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinnerFilter.adapter = filterAdapter

        rvWatchlist.adapter = MovieAdapter(watchlistMovies)
        currentMainList = watchlistMovies

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    finish()
                    true
                }
                R.id.nav_watchlist -> {
                    tvHeader.text = "YOUR WATCHLIST"
                    currentMainList = watchlistMovies
                    applyFilter(spinnerFilter.selectedItem.toString(), rvWatchlist)
                    true
                }
                R.id.nav_watched -> {
                    tvHeader.text = "WATCHED MOVIES"
                    currentMainList = watchedMovies
                    applyFilter(spinnerFilter.selectedItem.toString(), rvWatchlist)
                    true
                }
                else -> false
            }
        }

        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                applyFilter(genres[position], rvWatchlist)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun applyFilter(genre: String, recyclerView: RecyclerView) {
        val filteredList = if (genre == "All Genres") {
            currentMainList
        } else {
            currentMainList.filter { it.genre.contains(genre, ignoreCase = true) }
        }
        recyclerView.adapter = MovieAdapter(filteredList.toMutableList())
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}