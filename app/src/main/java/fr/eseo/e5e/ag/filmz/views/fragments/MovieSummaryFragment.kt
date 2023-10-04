package fr.eseo.e5e.ag.filmz.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.eseo.e5e.ag.filmz.FilmzApp
import fr.eseo.e5e.ag.filmz.databinding.FragmentMovieSummaryBinding
import fr.eseo.e5e.ag.filmz.viewadapters.MovieSummaryViewAdapter
import fr.eseo.e5e.ag.filmz.viewmodels.MovieSummaryViewModel
import fr.eseo.e5e.ag.filmz.viewmodels.MovieSummaryViewModelFactory
import fr.eseo.e5e.ag.filmz.views.FilmzActivity

class MovieSummaryFragment : Fragment() {
  companion object {
    fun newInstance() = MovieSummaryFragment()
  }

  private lateinit var viewAdapter: MovieSummaryViewAdapter
  private lateinit var binding: FragmentMovieSummaryBinding

  private val movieSummaryViewModel: MovieSummaryViewModel by viewModels {
    MovieSummaryViewModelFactory((activity?.application as FilmzApp).summaryRepository)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    Log.d("TABLET", "create fragment view")
    binding = FragmentMovieSummaryBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d("TABLET", "created fragment view")
    val recycler = binding.recyclerMovieList
    val llm = LinearLayoutManager(activity)
    llm.orientation = LinearLayoutManager.VERTICAL
    recycler.layoutManager = llm
    recycler.setHasFixedSize(true)
    viewAdapter = MovieSummaryViewAdapter(this)
    recycler.adapter = viewAdapter
    movieSummaryViewModel.movies.observe(
        this.requireActivity(),
        { movies ->
          Log.d("TABLET", "observed movieSummaryViewModeln")
          movies?.let { viewAdapter.submitList(it) }
        })
  }

  fun selectMovie(idMovie: Int) {
    (activity as FilmzActivity).selectMovie(idMovie)
  }
}
