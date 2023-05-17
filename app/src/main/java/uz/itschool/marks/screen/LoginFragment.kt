package uz.itschool.marks.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.itschool.marks.R
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.databinding.FragmentLoginBinding
import uz.itschool.marks.util.ShPHelper

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val appDataBase = AppDataBase.getInstance(requireContext())

        binding.logInButton.setOnClickListener {
            // TODO: Check and go home
            val shPHelper = ShPHelper.getInstance(requireContext())
            val login = binding.logInLoginEditText.text.toString()
            val password = binding.logInPasswordEditText.text.toString()
            val students = appDataBase.getStudentDao().getStudents()
            for (i in students){
                if (i.login == login){
                    if (i.password == password){
                        shPHelper.setUser(i.id.toString(), "2")
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        return@setOnClickListener
                    }
                }
            }
            val teachers = appDataBase.getTeacherDao().getTeachers()
            for (i in teachers){
                if (i.login == login){
                    if (i.password == password){
                        shPHelper.setUser(i.id.toString(), "5")
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        return@setOnClickListener
                    }
                }
            }
            Toast.makeText(requireContext(), "Noto'gri login yoki parol", Toast.LENGTH_LONG).show()
        }

        binding.logInSignUpText.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        return binding.root
    }
}