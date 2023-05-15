package uz.itschool.marks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "marks", foreignKeys = [
    ForeignKey(TeacherGroupSubject::class, ["id"], ["teacher_group_subject"]),
    ForeignKey(Student::class, ["id"], ["student_id"])
])
class Mark(
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    var mark:Int,
    @ColumnInfo(name = "teacher_group_subject")
    var teacherGroupSubject:Int,
    @ColumnInfo(name = "student_id")
    var studentId:Int
)