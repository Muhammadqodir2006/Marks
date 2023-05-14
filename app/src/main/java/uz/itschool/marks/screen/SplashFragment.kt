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
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.databinding.FragmentSplashBinding
import uz.itschool.marks.util.ShPHelper

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSplashBinding.inflate(inflater, container, false)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_anim)
        binding.splashLogo.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({
            val shPHelper = ShPHelper.getInstance(requireContext())
            val a = shPHelper.getUser()
            if (a ==  null){
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, 1500)
        return binding.root
    }

}