package uz.itschool.marks.screen.teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.marks.R
import uz.itschool.marks.adapter.MarksAdapterTeacher
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.databinding.FragmentMarksBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MarksFragment : Fragment() {
    private var param1: Int? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: Int) =
            MarksFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appDataBase = AppDataBase.getInstance(requireContext())
        val teacher = appDataBase.getTeacherDao().getTeacher(param1!!)
        val binding = FragmentMarksBinding.inflate(inflater, container, false)

        binding.teacherMarksRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.teacherMarksRecycler.adapter = MarksAdapterTeacher(requireContext(), appDataBase, param1!!, param2!!, teacher.subjectId)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            parentFragmentManager.beginTransaction().replace(R.id.teacherr_frag_container, GroupsFragment()).commit()
        }
        return binding.root
    }


}