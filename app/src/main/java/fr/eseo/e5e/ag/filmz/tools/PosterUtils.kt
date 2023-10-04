package fr.eseo.e5e.ag.filmz.tools

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.Scanner
import org.json.JSONException
import org.json.JSONObject

class PosterUtils {
  companion object {
    const val API_BASE_URL = "https://api.themoviedb.org/3/search/movie?"

    const val API_KEY_QUERY = "api_key"
    const val API_MOVIE_QUERY = "query"
    const val API_YEAR_QUERY = "year"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w200/"

    @JvmStatic
    fun buildApiUrl(movieName: String, year: String, apiKey: String): URL? {
      try {

        Log.d("POSTER", "A")
        Log.d("POSTER", movieName)
        Log.d("POSTER", year)
        Log.d("POSTER", apiKey)
        val uri =
            Uri.parse(API_BASE_URL)
                .buildUpon()
                .appendQueryParameter(API_KEY_QUERY, apiKey)
                .appendQueryParameter(API_MOVIE_QUERY, movieName)
                .appendQueryParameter(API_YEAR_QUERY, year)
                .build()
                .toString()
        Log.d("POSTER", uri.toString())
        return URL(uri)
      } catch (mue: MalformedURLException) {

        Log.d("POSTER", "B", mue)
        return null
      }
    }

    @JvmStatic
    fun getReplyFromHttpUrl(url: URL): String? {
      val urlConnection = (url.openConnection() as HttpURLConnection)

      Log.d("POSTER", "xA")
      try {

        Log.d("POSTER", "xB")
        val scanner = Scanner(urlConnection.inputStream)
        scanner.useDelimiter("\\A")
        var hasInput = scanner.hasNext()
        Log.d("POSTER", "xC " + hasInput)
        var scannerText = scanner.next()
        Log.d("POSTER", "xD " + scannerText)

        if (hasInput) {
          return scannerText
        } else {
          return null
        }
      } finally {

        Log.d("POSTER", "xC")
        urlConnection.disconnect()
      }
    }

    @JvmStatic
    fun parseForPoster(result: String): String? {
      try {
        return JSONObject(result).getJSONArray("results").getJSONObject(0).getString("poster_path")
      } catch (e: JSONException) {
        return null
      }
    }

    @JvmStatic
    fun getPoster(poster: String): Bitmap? {
      val posterUrl = URL(POSTER_BASE_URL + poster)
      Log.d("POSTER", "url=" + posterUrl.toString())
      return BitmapFactory.decodeStream(posterUrl.openStream())
    }
  }
}
