package com.appsv.notesapp.auth.splash.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.appsv.notesapp.R
import com.appsv.notesapp.auth.AuthViewModel
import com.appsv.notesapp.auth.ViewModelFactoryForActivityContext
import com.appsv.notesapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private var splashHandler: Handler? = null
    private val authViewModel: AuthViewModel by viewModels{
        ViewModelFactoryForActivityContext(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        checkLoginStatus()
        return binding.root
    }

    private fun checkLoginStatus() {
        splashHandler = Handler(Looper.getMainLooper())
        splashHandler?.postDelayed({


            val currentUserEmailId = authViewModel.getUserId()
            if (currentUserEmailId != null) {
                val bundle = Bundle()
                bundle.putString("userId", currentUserEmailId)
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment,bundle)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
            }
        }, 2500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (isAdded) {
            splashHandler?.removeCallbacksAndMessages(null)
            splashHandler = null
        }
    }
}
