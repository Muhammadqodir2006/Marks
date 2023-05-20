package uz.itschool.marks.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.marks.R
import uz.itschool.marks.adapter.StudentMarkRecycler
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.database.entity.Student
import uz.itschool.marks.database.entity.Subject
import uz.itschool.marks.databinding.FragmentStudentBinding
import uz.itschool.marks.util.ShPHelper

private const val ARG_PARAM1 = "param1"

class StudentFragment : Fragment() {
    private var param1: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStudentBinding.inflate(inflater, container, false)
        val appDataBase = AppDataBase.getInstance(requireContext())
        val student:Student = appDataBase.getStudentDao().getStudent(param1!!)
        val teacherGroupSubject = appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubjects()
        /////////////////////////////////////////////////////////////////////////////////////////
        //     GET SUBJECTS OF A STUDENT
        val mySubjects = mutableListOf<Subject>()
        val intLIst = mutableListOf<Int>()
        for (i in teacherGroupSubject){
            if (student.group_id == i.groupId) intLIst.add(i.subjectId)
        }
        for (i in appDataBase.getSubjectDao().getSubjects()){
            if (intLIst.contains(i.id)) mySubjects.add(i)
        }
        /////////////////////////////////////////////////////////////////////////////////////////


        binding.studentMarkRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.studentMarkRecycler.adapter = StudentMarkRecycler(appDataBase, requireContext(), student.id)


        binding.studentLogout.setOnClickListener {
            ShPHelper.getInstance(requireContext()).setUser("","")
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
        binding.studentGroup.text = appDataBase.getGroupDao().getGroup(student.group_id).name
        binding.studentName.text = student.firstName + " " + student.lastName


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }
    companion object {
        fun newInstanceSt(param1: Int) =
            StudentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}