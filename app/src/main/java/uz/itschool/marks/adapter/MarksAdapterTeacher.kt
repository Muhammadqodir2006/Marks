package uz.itschool.marks.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.itschool.marks.R
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.database.entity.Mark
import uz.itschool.marks.database.entity.Student

class MarksAdapterTeacher(val contexT: Context, private val appDataBase: AppDataBase, teacherId:Int, private val  subjectId:Int) : RecyclerView.Adapter<MarksAdapterTeacher.MyHolder>() {
    private val groupIds = mutableListOf<Int>()
    private val students = appDataBase.getStudentDao().getStudents()
    private val myStudents = mutableListOf<Student>()
    private val teacherGroupSubjects = appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubjects()

    init {
        for (i in appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubjects(teacherId)){
            if (!groupIds.contains(i.groupId)){
                groupIds.add(i.groupId)
            }
        }
        for (i in students){
            if (groupIds.contains(i.group_id)){
                myStudents.add(i)
            }
        }
    }


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val studentName: TextView = itemView.findViewById(R.id.item_teacher_marks_name)
        val gridLayout: GridLayout = itemView.findViewById(R.id.item_teacher_marks_grid_layout)
        val add: FloatingActionButton = itemView.findViewById(R.id.item_teacher_marks_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teacher_marks, parent, false))
    }

    override fun getItemCount(): Int {
        return students.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val student = students[position]
        holder.studentName.text = "${student.firstName} ${student.lastName}"

        val marks = appDataBase.getMarkDao().getMarks(student.id)
        val myMarks = mutableListOf<Int>()

        for (i in marks){
            for (j in teacherGroupSubjects){
                if (i.teacherGroupSubject == j.id){
                    if (j.subjectId == subjectId){
                        myMarks.add(i.mark)
                    }
                    break
                }
            }
        }
        for (i in myMarks){
            if (i == 5){
                holder.gridLayout.addView(View.inflate(contexT, R.layout.mark_5, null))
            }
            if (i == 4){
                holder.gridLayout.addView(View.inflate(contexT, R.layout.mark_4, null))
            }
            if (i == 3){
                holder.gridLayout.addView(View.inflate(contexT, R.layout.mark_3, null))
            }
        }

              



        holder.add.setOnClickListener {
            // TODO: Baho qo'y
        }
    }
}