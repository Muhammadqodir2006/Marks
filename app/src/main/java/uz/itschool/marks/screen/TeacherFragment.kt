package uz.itschool.marks.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.itschool.marks.R
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.databinding.FragmentTeacherBinding

private const val ARG_PARAM1 = "param1"
class TeacherFragment : Fragment() {
    private var param1: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appDataBase = AppDataBase.getInstance(requireContext())
        val binding = FragmentTeacherBinding.inflate(inflater, container, false)
        val teacher = appDataBase.getTeacherDao().getTeacher(param1!!)


        binding.teacherName.text = teacher.firstName + " " + teacher.lastName


        return binding.root
    }

    companion object {
        fun newInstance(param1: Int) =
            TeacherFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}