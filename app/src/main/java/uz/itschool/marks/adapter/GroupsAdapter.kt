package uz.itschool.marks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import uz.itschool.marks.R
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.database.entity.Group

class GroupsAdapter(val context: Context, private val appDataBase: AppDataBase, private val teacherId:Int, val goMArks: GoMArks): RecyclerView.Adapter<GroupsAdapter.MyHolder>() {
    private val groups = mutableListOf<Group>()

    init {
        val groupIds = mutableListOf<Int>()
        for (i in appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubjects(teacherId)){
            groupIds.add(i.groupId)
        }
        for (i in appDataBase.getGroupDao().getGroup()){
            if (groupIds.contains(i.id)){
                groups.add(i)
            }
        }
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val namE = itemView.findViewById<MaterialButton>(R.id.group)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teacher_group, parent, false))
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.namE.text = groups[position].name
        holder.namE.setOnClickListener {
            goMArks.pressed(groups[position])
        }
    }

    interface GoMArks{
        fun pressed(group:Group)
    }

}