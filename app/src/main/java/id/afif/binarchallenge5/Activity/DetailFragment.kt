package id.afif.binarchallenge5.Activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.afif.binarchallenge5.API.TMDBClient
import id.afif.binarchallenge5.Model.MovieDetail
import id.afif.binarchallenge5.R
import id.afif.binarchallenge5.databinding.FragmentDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id")
        getDetailFromNetwork(id!!)

    }

    private fun getDetailFromNetwork(id : Int){
        val tmdbService = TMDBClient.instance
        tmdbService.getMovieDetail(id,"c548b9c05e09ed4c22de8c8eed87a602").enqueue(object : Callback<MovieDetail>{
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if(response.isSuccessful){
                    if(response.body() !=null){
                        response.body()?.let {
                            addData(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun addData(movieDetail: MovieDetail){
        val background = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/${movieDetail.backdropPath}"
        Glide.with(requireContext()).load("https://www.themoviedb.org/t/p/w220_and_h330_face/${movieDetail?.posterPath}")
            .into(binding.ivMoviesDetail)
        binding.tvMovieDetailTitle.text = movieDetail!!.title
        binding.tvReleaseDate.text = movieDetail.releaseDate
        binding.tvRelease.text = movieDetail.releaseDate
        binding.tvGenre.text = "${movieDetail.genres[0].name},${movieDetail.genres[1].name}"
        binding.progressDetail.progress = (movieDetail.voteAverage*10).toInt()
        binding.tvPercentageDetail.text = "${movieDetail.voteAverage*10}%"
        binding.tvTagline.text = movieDetail.tagline
        binding.tvOverview.text = movieDetail.overview
        
    }



}