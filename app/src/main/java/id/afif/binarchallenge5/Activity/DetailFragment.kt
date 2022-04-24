package id.afif.binarchallenge5.Activity

import android.os.Bundle
import android.text.TextUtils.concat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import id.afif.binarchallenge5.API.TMDBClient
import id.afif.binarchallenge5.API.TMDBService
import id.afif.binarchallenge5.Helper.viewModelsFactory
import id.afif.binarchallenge5.Model.MovieDetail
import id.afif.binarchallenge5.R
import id.afif.binarchallenge5.databinding.FragmentDetailBinding
import id.afif.binarchallenge5.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val apiTMDBService : TMDBService by lazy { TMDBClient.instance }
    private val moviesViewModel : MoviesViewModel by viewModelsFactory { MoviesViewModel(apiTMDBService,requireContext()) }

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
        moviesViewModel.getMovieDetail(id!!)
        getDetailFromNetwork()

    }

    private fun getDetailFromNetwork(){
        moviesViewModel.dataMovieDetail.observe(viewLifecycleOwner){
         addData(it)
            binding.pbLoading.isVisible = false
        }
    }



    private fun addData(movieDetail: MovieDetail){
        var text1 = ""

        Glide.with(requireContext()).load("https://www.themoviedb.org/t/p/w220_and_h330_face/${movieDetail?.posterPath}")
            .into(binding.ivMoviesDetail)
        binding.tvMovieDetailTitle.text = movieDetail!!.title
        binding.tvReleaseDate.text = "(${ movieDetail.releaseDate.take(4) })"
        binding.tvRelease.text = movieDetail.releaseDate
        for(i in 0 until movieDetail.genres.size){
            text1  = concat(text1,"${movieDetail.genres[i].name}, ").toString()
        }

        binding.tvGenre.text = text1

        binding.progressDetail.progress = (movieDetail.voteAverage*10).toInt()
        binding.tvPercentageDetail.text = "${movieDetail.voteAverage*10}%"
        if(!movieDetail.tagline.isNullOrBlank()){
            binding.tvTagline.isVisible = true
        }
        val jam = movieDetail.runtime/60
        val menit = movieDetail.runtime%60
        binding.tvRuntime.text = "${jam}h ${menit}m"
        binding.tvTagline.text = movieDetail.tagline
        binding.tvOverview.text = movieDetail.overview

    }





}