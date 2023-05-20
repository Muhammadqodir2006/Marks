package uz.itschool.marks.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.itschool.marks.R
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.databinding.FragmentTeacherBinding
import uz.itschool.marks.screen.teacher.GroupsFragment
import uz.itschool.marks.util.ShPHelper

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
        val shPHelper = ShPHelper.getInstance(requireContext())

        binding.teacherName.text = "${teacher.firstName} ${teacher.lastName}"

        childFragmentManager.beginTransaction().add(R.id.teacherr_frag_container, GroupsFragment()).commit()

        binding.teacherLogout.setOnClickListener {
            shPHelper.setUser("","")
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
        binding.teacherSubject.text = appDataBase.getSubjectDao().getSubject(teacher.subjectId).name

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