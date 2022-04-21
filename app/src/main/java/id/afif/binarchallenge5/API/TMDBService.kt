package id.afif.binarchallenge5.API

import id.afif.binarchallenge5.Model.MovieDetail
import id.afif.binarchallenge5.Model.Movies
import id.afif.binarchallenge5.Model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    fun getAllMovie(@Query("api_key") a : String) : Call<Movies>

    @GET("/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId : Int) : Call<MovieDetail>
}