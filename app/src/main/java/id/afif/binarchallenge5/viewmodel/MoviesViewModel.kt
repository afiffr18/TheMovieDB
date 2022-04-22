package id.afif.binarchallenge5.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afif.binarchallenge5.API.TMDBService
import id.afif.binarchallenge5.Model.MovieDetail
import id.afif.binarchallenge5.Model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(private val apiTMDBService: TMDBService) : ViewModel() {

    private val _dataMovies = MutableLiveData<Movies>()
    val dataMovies : LiveData<Movies> get() = _dataMovies

    private val _dataMovieDetail = MutableLiveData<MovieDetail>()
    val dataMovieDetail : LiveData<MovieDetail> get() = _dataMovieDetail

    fun getAllMovies(){
        apiTMDBService.getAllMovie("c548b9c05e09ed4c22de8c8eed87a602").enqueue(object: Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        response.body().let {
                            _dataMovies.postValue(response.body())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    fun getMovieDetail(id:Int){
        apiTMDBService.getMovieDetail(id,"c548b9c05e09ed4c22de8c8eed87a602").enqueue(object: Callback<MovieDetail>{
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if(response.isSuccessful){
                    if(response.body()!=null){
                        response.body().let {
                            _dataMovieDetail.postValue(response.body())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}