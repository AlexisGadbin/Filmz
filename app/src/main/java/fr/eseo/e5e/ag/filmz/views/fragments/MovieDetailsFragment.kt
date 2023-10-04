package fr.eseo.e5e.ag.filmz.views.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.eseo.e5e.ag.filmz.FilmzApp
import fr.eseo.e5e.ag.filmz.R
import fr.eseo.e5e.ag.filmz.databinding.FragmentMovieDetailsBinding
import fr.eseo.e5e.ag.filmz.tools.PosterUtils
import fr.eseo.e5e.ag.filmz.viewadapters.MovieRoleViewAdapter
import fr.eseo.e5e.ag.filmz.viewmodels.MovieDetailViewModel
import fr.eseo.e5e.ag.filmz.viewmodels.MovieDetailViewModelFactory
import java.util.concurrent.Executors

class MovieDetailsFragment : Fragment() {
  private lateinit var binding: FragmentMovieDetailsBinding
  private var genres = ArrayList<String>()

  private val movieDetailViewModel: MovieDetailViewModel by viewModels {
    MovieDetailViewModelFactory((activity?.application as FilmzApp).detailsRepository)
  }

  fun updatePoster(bitmap: Bitmap?) {
    if (bitmap != null) {
      try {
        binding.movieDetailsPoster.setImageBitmap(bitmap)
      } catch (ex: Exception) {}
    }
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (arguments != null) {
      val movieId = requireArguments().getInt("MOVIE_ID", 0)
      binding.movieDetailsRoles.setHasFixedSize(true)
      val roleAdapter = movieId.let { MovieRoleViewAdapter(it) }
      binding.movieDetailsRoles.adapter = roleAdapter
      val llm = LinearLayoutManager(activity)
      llm.orientation = LinearLayoutManager.HORIZONTAL
      binding.movieDetailsRoles.layoutManager = llm
      movieDetailViewModel
          .getMovieFromId(movieId)
          .observe(
              this.requireActivity(),
              { movies ->
                val url =
                    PosterUtils.buildApiUrl(
                        movies.movie.movie_title,
                        movies.movie.year.toString(),
                        resources.getString(R.string.api_key))
                val anExecutor = Executors.newSingleThreadExecutor()
                val aHandler = Handler(Looper.getMainLooper())
                anExecutor.execute {
                  val result = url?.let { PosterUtils.getReplyFromHttpUrl(it) }
                  val poster = result?.let { PosterUtils.parseForPoster(it) }
                  val posterImage = poster?.let { PosterUtils.getPoster(it) }
                  aHandler.post { updatePoster(posterImage) }
                }

                binding.movieDetailsTitle.text = movies.movie.movie_title
                binding.movieDetailsYear.text = movies.movie.year.toString()
                binding.movieDetailsSynopsis.text = movies.movie.synopsis
                movieDetailViewModel
                    .getDirector(movies.movie.refDirecteur)
                    .observe(
                        this.requireActivity(),
                        { director ->
                          binding.movieDetailsDirector.text =
                              director.surname.uppercase() +
                                  " " +
                                  director.forename.substring(0, 1).uppercase() +
                                  director.forename.substring(1).lowercase()
                        })
                movieDetailViewModel
                    .getCountry(movies.movie.refCountry)
                    .observe(
                        this.requireActivity(),
                        { country -> binding.movieDetailsCountry.text = country.name.uppercase() })
                movieDetailViewModel
                    .getRolesFromId(movieId)
                    .observe(
                        this.requireActivity(),
                        { roles -> roles?.let { roleAdapter.submitList(it) } })
              })
    }
  }

  companion object {
    fun newInstance(idMovie: Int): MovieDetailsFragment {
      val fragment = MovieDetailsFragment()
      val bundle = Bundle()
      bundle.putInt("MOVIE_ID", idMovie)
      fragment.arguments = bundle
      return fragment
    }
  }
}
