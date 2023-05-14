package uz.itschool.marks.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.marks.database.entity.TeacherGroupSubject

@Dao
interface TeacherGroupSubjectDao {
    @Query("select * from teacher_group_subject")
    fun getTeacherGroupSubject():List<TeacherGroupSubject>

    @Query("select * from teacher_group_subject where teacher_id = :teacherId")
    fun getTeacherGroupSubject(teacherId:Int):List<TeacherGroupSubject>

    @Insert
    fun addTeacherGroupSubject(teacherGroupSubject: TeacherGroupSubject)

    @Delete
    fun deleteTeacherGroupSubject(teacherGroupSubject: TeacherGroupSubject)
}