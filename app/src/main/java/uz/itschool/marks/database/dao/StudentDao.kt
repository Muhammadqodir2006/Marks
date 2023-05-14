package uz.itschool.marks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.marks.database.entity.Mark
import uz.itschool.marks.database.entity.Student

@Dao
interface StudentDao {

    @Query("select * from students")
    fun getStudents():List<Student>


    @Query("select * from students where group_id = :groupId")
    fun getStudents(groupId:Int):List<Student>

    @Insert
    fun addStudent(student: Student)

}