package id.afif.binarchallenge5.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.afif.binarchallenge5.API.TMDBService
import id.afif.binarchallenge5.Helper.UserRepo
import id.afif.binarchallenge5.Model.MovieDetail
import id.afif.binarchallenge5.Model.Movies
import id.afif.binarchallenge5.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(private val apiTMDBService: TMDBService?,context: Context?) : ViewModel() {

    private val _dataMovies = MutableLiveData<Movies>()
    val dataMovies : LiveData<Movies> get() = _dataMovies

    private val _dataMovieDetail = MutableLiveData<MovieDetail>()
    val dataMovieDetail : LiveData<MovieDetail> get() = _dataMovieDetail

    private val _dataUser = MutableLiveData<User>()
    val dataUser : LiveData<User> get() = _dataUser

    private val userRepo = UserRepo(context!!)

    fun getAllMovies(){
        apiTMDBService!!.getAllMovie("c548b9c05e09ed4c22de8c8eed87a602").enqueue(object: Callback<Movies>{
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
        apiTMDBService!!.getMovieDetail(id,"c548b9c05e09ed4c22de8c8eed87a602").enqueue(object: Callback<MovieDetail>{
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

   fun saveToDb(username : String, email:String, password:String){
        val data = User(null,username,email,password,"","","")
       viewModelScope.launch (Dispatchers.IO) {
           userRepo.saveRegister(data)
       }
   }

   fun getDataById(username : String, password:String){
       viewModelScope.launch(Dispatchers.IO) {
           val result = userRepo.getDataById(username,password)
           Log.e("Resllts isi",result.toString())
               _dataUser.postValue(result!!)

       }
   }


}