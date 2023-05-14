package uz.itschool.marks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher_group_subject")
class TeacherGroupSubject(
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    @ColumnInfo(name = "teacher_id")
    var teacherId:Int,
    @ColumnInfo(name = "group_id")
    var groupId:Int,
    @ColumnInfo(name = "subject_id")
    var subjectId:Int,
)