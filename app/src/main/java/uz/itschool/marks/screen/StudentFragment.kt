package uz.itschool.marks.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.database.entity.Student
import uz.itschool.marks.database.entity.Subject
import uz.itschool.marks.databinding.FragmentStudentBinding

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






        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
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