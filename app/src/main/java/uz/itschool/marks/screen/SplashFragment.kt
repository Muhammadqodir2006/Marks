package uz.itschool.marks.screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import uz.itschool.marks.R
import uz.itschool.marks.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_anim)
        binding.splashLogo.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({
            go()
        }, 1500)
        return binding.root
    }

    private fun go() {
        // TODO: Check for logged in user
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }
}