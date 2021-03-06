package id.afif.binarchallenge5.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.afif.binarchallenge5.API.TMDBClient
import id.afif.binarchallenge5.API.TMDBService
import id.afif.binarchallenge5.Adapter.MoviesAdapter
import id.afif.binarchallenge5.Helper.SharePref
import id.afif.binarchallenge5.Helper.UserRepo
import id.afif.binarchallenge5.Helper.viewModelsFactory
import id.afif.binarchallenge5.viewmodel.MoviesViewModel
import id.afif.binarchallenge5.R
import id.afif.binarchallenge5.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter : MoviesAdapter

    private val apiTMDBService : TMDBService by lazy { TMDBClient.instance }
    private val userRepo : UserRepo by lazy { UserRepo(requireContext()) }
    private val viewModel : MoviesViewModel by viewModelsFactory { MoviesViewModel(apiTMDBService,userRepo) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        viewModel.getAllMovies()
        getDataFromNetwork()
        setUsernameLogin()
        profileClicked()
    }

    private fun initRecycler(){
        moviesAdapter = MoviesAdapter{ id: Int ->
            val bundle = Bundle()
            bundle.putInt("id",id)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }
        binding.apply {
            rvMovies.adapter = moviesAdapter
            rvMovies.layoutManager = GridLayoutManager(requireContext(),2)
        }
    }

    private fun getDataFromNetwork(){
        viewModel.dataMovies.observe(viewLifecycleOwner){
            moviesAdapter.updateData(it.results)
            binding.pbLoading.isVisible = false
        }
    }


    private fun setUsernameLogin(){
        val sharePref = SharePref(requireContext())
        val nama = sharePref.getUsername()
        binding.tvUserLogin.text = "welcome : $nama"
    }

    private fun profileClicked(){
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }


}