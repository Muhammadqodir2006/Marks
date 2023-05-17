package uz.itschool.marks.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.itschool.marks.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.signupRg.setOnCheckedChangeListener { group, checkedId ->
            if (binding.singupTeacherRb.isChecked){
                binding.signupSecondfieldEditText.hint = "Fan"
            }else{
                binding.signupSecondfieldEditText.hint = "Sinf"
            }
        }

        binding.signupButton.setOnClickListener {
            // TODO: Sign up
            Toast.makeText(requireContext(), "Login qiling", Toast.LENGTH_LONG).show()
        }
        binding.signupLoginText.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return binding.root
    }
}