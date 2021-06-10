package com.programmer2704.movapp.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import com.programmer2704.movapp.R
import com.programmer2704.movapp.constant.Constants
import com.programmer2704.movapp.data.local.MovieEntity
import com.programmer2704.movapp.databinding.ActivityDescriptionBinding
import com.programmer2704.movapp.usage.Usage
import com.programmer2704.movapp.view.viewmodel.DescriptionViewmodel
import com.programmer2704.movapp.view.viewmodel.FavViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionActivity : AppCompatActivity() {

    private lateinit var b: ActivityDescriptionBinding
    private val movieDetailsViewModel: DescriptionViewmodel by viewModels()
//    private lateinit var favoritesViewModel: FavViewmodel
    private val favoritesViewModel: FavViewmodel by viewModels()
    private var posterPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(b.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieId = intent.getIntExtra("id", 0)
        val isFavorite = intent.getBooleanExtra("isFavorite", false)

        b.actionButtonFavorites.text = if (isFavorite) {
            b.actionButtonFavorites.setBackgroundColor(resources.getColor(R.color.colorAccent))
            getString(R.string.remove_favorite)
        } else {
            b.actionButtonFavorites.setBackgroundColor(resources.getColor(R.color.colorGreen))
            getString(R.string.add_favorite)
        }

        // FAB
        if (isFavorite) {
            b.fabFavorite.setImageResource(R.drawable.ic_favorite_48)
        } else {
            b.fabFavorite.setImageResource(R.drawable.ic_not_favorite_48)
        }

//        movieDetailsViewModel = ViewModelProviders.of(this).get(DescriptionViewmodel::class.java)
//        favoritesViewModel = ViewModelProviders.of(this).get(FavViewmodel::class.java)

        movieId.let {
            if (Usage.isOnline(this)) {
                movieDetailsViewModel.fetchDetails(it)
            } else {
                Toast.makeText(
                    this,
                    "No internet Present, Please enable internet connection",
                    Toast.LENGTH_SHORT
                ).show()
                b.progressCircular.visibility = View.GONE
            }
            movieDetailsViewModel.movieDetails.observe(this, Observer {
                b.progressCircular.visibility = View.GONE
                b.actionButtonFavorites.visibility = View.VISIBLE

                Picasso.get().load(Constants.MOVIE_PHOTO_URL + it.poster_path).into(b.posterImage)
                posterPath = it.poster_path
                b.title.text = it.original_title
                b.overview.text = it.overview
                if (it.revenue == 0 || it.revenue == null)
                    b.revenue.visibility = View.GONE
                else
                    b.revenue.text = "Revenue: $ ${it.revenue}"

                if (it.runtime == 0 || it.runtime == null)
                    b.runtime.visibility = View.GONE
                else
                    b.runtime.text = "Runtime: ${it.runtime}"

                if (it.budget == 0 || it.budget == null)
                    b.budget.visibility = View.GONE
                else
                    b.budget.text = "Budget: $ ${it.budget}"

                b.originalLanguage.text = "Language: ${it.original_language}"

            })
        }

        b.actionButtonFavorites.setOnClickListener {
            if (b.actionButtonFavorites.text.equals(getString(R.string.add_favorite))) {
                favoritesViewModel.insert(MovieEntity(id = movieId, poster_path = posterPath))
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
                b.actionButtonFavorites.visibility = View.GONE
            } else if (b.actionButtonFavorites.text.equals(getString(R.string.remove_favorite))) {
                favoritesViewModel.delete(MovieEntity(id = movieId, poster_path = posterPath))
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
                b.actionButtonFavorites.visibility = View.GONE
            }
        }

        b.fabFavorite.setOnClickListener {

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}