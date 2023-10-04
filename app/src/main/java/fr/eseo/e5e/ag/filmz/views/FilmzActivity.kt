package fr.eseo.e5e.ag.filmz.views

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import fr.eseo.e5e.ag.filmz.R
import fr.eseo.e5e.ag.filmz.views.fragments.MovieDetailsFragment
import fr.eseo.e5e.ag.filmz.views.fragments.MovieSummaryFragment

class FilmzActivity : AppCompatActivity() {

  private var landScapeTabletMode: Boolean = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("TABLET", "onCreate called")
    setContentView(R.layout.activity_filmz)
    var movieSummaryFragment = MovieSummaryFragment.newInstance()
    if (savedInstanceState == null) {
      Log.d("TABLET", "Creating movie summary")
      supportFragmentManager
          .beginTransaction()
          .add(R.id.placeholder_movie_summary, movieSummaryFragment)
          .addToBackStack("Summary")
          .commit()
    }

    if (findViewById<LinearLayout>(R.id.tablet_layout) != null) {
      landScapeTabletMode = true
    }
  }

  fun selectMovie(idMovie: Int) {
    val detailsFragment = MovieDetailsFragment.newInstance(idMovie)

    if (landScapeTabletMode) {
      Log.d("TABLET", "getting details")
      supportFragmentManager
          .beginTransaction()
          .replace(R.id.placeholder_movie_details, detailsFragment)
          .addToBackStack("Details")
          .commit()
    } else {
      supportFragmentManager
          .beginTransaction()
          .replace(R.id.placeholder_movie_summary, detailsFragment)
          .addToBackStack("Details")
          .commit()
    }
  }
}
