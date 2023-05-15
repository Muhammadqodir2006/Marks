package uz.itschool.marks.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.ForeignKey
import uz.itschool.marks.R
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.database.entity.Student
import uz.itschool.marks.database.entity.Subject
import uz.itschool.marks.databinding.FragmentStudentBinding
import uz.itschool.marks.util.ShPHelper

class StudentFragment : Fragment() {
    val appDataBase = AppDataBase.getInstance(requireContext())
    val shPHelper = ShPHelper.getInstance(requireContext())
    val id = shPHelper.getUser()!![0].toInt()
    lateinit var student : Student
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStudentBinding.inflate(inflater, container, false)

        val groups = appDataBase.getGroupDao().getGroup()
        val teacherGroupSubject = appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubject()
        val students = appDataBase.getStudentDao().getStudents()

        /////////////////////////////////////////////////////////////////////////////////////////
        //     GET SUBJECTS OF A STUDENT
        for (i in students){
            if (i.id == id) {
                student = i
                break
            }
        }

        val mySubjects = mutableListOf<Subject>()
        val intLIst = mutableListOf<Int>()
        for (i in appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubject()){
            if (student.group_id == i.groupId) intLIst.add(student.group_id)
        }
        for (i in appDataBase.getSubjectDao().getSubjects()){
            if (intLIst.contains(i.id)) mySubjects.add(i)
        }
        /////////////////////////////////////////////////////////////////////////////////////////

        return binding.root
    }
}