package uz.itschool.marks.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.marks.database.entity.TeacherGroupSubject

@Dao
interface TeacherGroupSubjectDao {
    @Query("select * from teacher_group_subject")
    fun getTeacherGroupSubjects():List<TeacherGroupSubject>

    @Query("select * from teacher_group_subject where teacher_id = :teacherId")
    fun getTeacherGroupSubjects(teacherId:Int):List<TeacherGroupSubject>
    @Query("select group_id from teacher_group_subject where teacher_id = :teacherId")
    fun getTeacherGroups(teacherId:Int):List<Int>

    @Insert
    fun addTeacherGroupSubject(teacherGroupSubject: TeacherGroupSubject)

    @Delete
    fun deleteTeacherGroupSubject(teacherGroupSubject: TeacherGroupSubject)
}