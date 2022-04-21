package id.afif.binarchallenge5.Activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.afif.binarchallenge5.API.TMDBClient
import id.afif.binarchallenge5.API.TMDBService
import id.afif.binarchallenge5.Adapter.MoviesAdapter
import id.afif.binarchallenge5.Model.Movies
import id.afif.binarchallenge5.Model.Result
import id.afif.binarchallenge5.R
import id.afif.binarchallenge5.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter : MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        getDataFromNetwork()
    }

    private fun initRecycler(){
        moviesAdapter = MoviesAdapter()
        binding.apply {
            rvMovies.adapter = moviesAdapter
            rvMovies.layoutManager = GridLayoutManager(requireContext(),2)
        }
    }

    private fun getDataFromNetwork(){
        val apiTMDBService = TMDBClient.instance
        apiTMDBService.getAllMovie("c548b9c05e09ed4c22de8c8eed87a602").enqueue(object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if(response.isSuccessful){
                    if(response.body()!=null){
                        val responseBody = response.body()
                        response.body().let {
                            moviesAdapter.updateData(responseBody!!.results)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}