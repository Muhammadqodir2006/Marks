package uz.itschool.marks.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.itschool.marks.R
import uz.itschool.marks.database.AppDataBase
import uz.itschool.marks.database.entity.Mark
import uz.itschool.marks.database.entity.Student

class MarksAdapterTeacher(
    val contexT: Context,
    private val appDataBase: AppDataBase,
    teacherId: Int,
    groupId: Int,
    private val subjectId: Int,
) : RecyclerView.Adapter<MarksAdapterTeacher.MyHolder>() {
    private val students = appDataBase.getStudentDao().getStudents()
    private val myStudents = mutableListOf<Student>()
    var teacherGroupSubjectId: Int = 0
    private val teacherGroupSubjects =
        appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubjects()

    init {
        for (i in students) {
            if (i.group_id == groupId) {
                myStudents.add(i)
            }
        }
        for (i in appDataBase.getTeacherGroupSubjectDao().getTeacherGroupSubjects()) {
            if (i.teacherId == teacherId && i.groupId == groupId) {
                teacherGroupSubjectId = i.id
                break
            }
        }
    }


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView = itemView.findViewById(R.id.item_teacher_marks_name)
        val gridLayout: GridLayout = itemView.findViewById(R.id.item_teacher_marks_grid_layout)
        val add: FloatingActionButton = itemView.findViewById(R.id.item_teacher_marks_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_teacher_marks, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return myStudents.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val student = myStudents[position]
        holder.studentName.text = "${student.firstName} ${student.lastName}"

        val marks = appDataBase.getMarkDao().getMarks(student.id)
        val myMarks = mutableListOf<Int>()

        for (i in marks) {
            for (j in teacherGroupSubjects) {
                if (i.teacherGroupSubject == j.id) {
                    if (j.subjectId == subjectId){
                        myMarks.add(i.mark)
                    }
                    break
                }
            }
        }
        for (i in myMarks) {
            when (i) {
                5 -> {
                    holder.gridLayout.addView(View.inflate(contexT, R.layout.mark_5, null))
                }
                4 -> {
                    holder.gridLayout.addView(View.inflate(contexT, R.layout.mark_4, null))
                }
                3 -> {
                    holder.gridLayout.addView(View.inflate(contexT, R.layout.mark_3, null))
                }
            }
        }
        holder.add.setOnClickListener {
            val dialog = BottomSheetDialog(contexT)
            dialog.setContentView(R.layout.dialog_bottom_sheet_marks)

            val group = dialog.findViewById<RadioGroup>(R.id.radioGroup)!!
            val ok = dialog.findViewById<MaterialButton>(R.id.mark_ok)!!
            val cancel = dialog.findViewById<MaterialButton>(R.id.mark_cancel)!!

            ok.setOnClickListener {
                when (group.checkedRadioButtonId) {
                    R.id.mark3 -> {
                        appDataBase.getMarkDao()
                            .addMark(Mark(mark = 3, studentId = student.id, teacherGroupSubject = teacherGroupSubjectId))
                    }
                    R.id.mark4 -> {
                        appDataBase.getMarkDao()
                            .addMark(Mark(mark = 4, studentId = student.id, teacherGroupSubject = teacherGroupSubjectId))
                    }
                    R.id.mark5 -> {
                        appDataBase.getMarkDao()
                            .addMark(Mark(mark = 5, studentId = student.id, teacherGroupSubject = teacherGroupSubjectId))
                    }
                }
                notifyItemChanged(position)
                dialog.cancel()
            }
            cancel.setOnClickListener {
                dialog.cancel()
            }

            dialog.show()
        }
    }
}