package id.afif.binarchallenge5.Activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import id.afif.binarchallenge5.Helper.SharePref
import id.afif.binarchallenge5.R
import id.afif.binarchallenge5.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private var _binding : FragmentSplashBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharePref  = SharePref(requireContext())
        Handler(Looper.getMainLooper()).postDelayed({
            if (!sharePref.isLogedIn()){
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        },3000)
    }


}