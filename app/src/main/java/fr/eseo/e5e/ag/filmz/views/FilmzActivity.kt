package fr.eseo.e5e.ag.filmz.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.eseo.e5e.ag.filmz.R
import fr.eseo.e5e.ag.filmz.views.fragments.MovieSummaryFragment

class FilmzActivity : AppCompatActivity() {
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
  }
}
