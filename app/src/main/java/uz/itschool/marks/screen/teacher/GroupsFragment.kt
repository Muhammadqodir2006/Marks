package uz.itschool.marks.screen.teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.marks.R
import uz.itschool.marks.adapter.GroupsAdapter
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.database.entity.Group
import uz.itschool.marks.databinding.FragmentGroupsBinding
import uz.itschool.marks.util.ShPHelper

class GroupsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val shPHelper = ShPHelper.getInstance(requireContext())
        val binding = FragmentGroupsBinding.inflate(inflater, container, false)
        val appDataBase = AppDataBase.getInstance(requireContext())
        val teacherId = shPHelper.getUser()!![0].toInt()

        binding.groupsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.groupsRecycler.adapter = GroupsAdapter(requireContext(), appDataBase, teacherId, object : GroupsAdapter.GoMArks{
            override fun pressed(group: Group) {
                childFragmentManager.beginTransaction().replace(R.id.teacherr_nav_host, MarksFragment.newInstance(teacherId, group.id)).addToBackStack("").commit()
            }

        })

        return binding.root
    }

}