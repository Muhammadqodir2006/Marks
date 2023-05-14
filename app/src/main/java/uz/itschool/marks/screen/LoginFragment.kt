package uz.itschool.marks.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.itschool.marks.R
import uz.itschool.marks.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.logInButton.setOnClickListener {
            // TODO: Check and go home
        }

        binding.logInSignUpText.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        return binding.root
    }
}