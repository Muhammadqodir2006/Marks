package uz.itschool.marks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.marks.database.entity.Teacher

@Dao
interface TeacherDao {
    @Query("select * from teachers")
    fun getTeachers():List<Teacher>

    @Insert
    fun addTeacher(teacher: Teacher)
    @Query("select * from teachers where id = :id")
    fun getTeacher(id:Int):Teacher
}