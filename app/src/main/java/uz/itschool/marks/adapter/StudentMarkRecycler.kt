package uz.itschool.marks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.itschool.marks.R
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.database.entity.Mark

class StudentMarkRecycler(val appDataBase: AppDataBase, val contexT: Context, val studentId: Int) :
    RecyclerView.Adapter<StudentMarkRecycler.MyHolder>() {
    val student = appDataBase.getStudentDao().getStudent(studentId)
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fan: TextView = itemView.findViewById(R.id.fan)
        val studentMarksGrid: GridLayout =
            itemView.findViewById(R.id.item_student_marks_grid_layout)
    }

    private val subjectIds = mutableListOf<Int>()
    val allMarks = appDataBase.getMarkDao().getMarks(studentId)

    init {
            for (j in appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubjects()) {
                if (j.groupId == student.group_id) {
                    if (!subjectIds.contains(j.subjectId)) {
                        subjectIds.add(j.subjectId)
                    }
                    break
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student_marks, parent, false).rootView)
    }

    override fun getItemCount(): Int {
        return subjectIds.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val subject = appDataBase.getSubjectDao().getSubject(subjectIds[position])
        val myMarks = mutableListOf<Mark>()
        val student = appDataBase.getStudentDao().getStudent(studentId)
        var teacherGroupSubjectId = 0
        for (i in appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubjects()){
            if (i.groupId == student.group_id && i.subjectId == subjectIds[position]){
                teacherGroupSubjectId = i.id
                break
            }
        }
        for (i in allMarks){
            if (i.teacherGroupSubject == teacherGroupSubjectId){
                myMarks.add(i)
            }
        }
        for (i in myMarks){
            when (i.mark){
                5->{
                    holder.studentMarksGrid.addView(View.inflate(contexT, R.layout.mark_5, null))
                }
                4->{
                    holder.studentMarksGrid.addView(View.inflate(contexT, R.layout.mark_4, null))
                }
                3->{
                    holder.studentMarksGrid.addView(View.inflate(contexT, R.layout.mark_3, null))
                }
            }
        }
        holder.fan.text = subject.name
    }
}