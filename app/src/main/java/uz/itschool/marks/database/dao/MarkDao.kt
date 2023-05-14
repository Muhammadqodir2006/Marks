package uz.itschool.marks.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.marks.database.entity.Mark
import uz.itschool.marks.database.entity.Student

@Dao
interface MarkDao {
    @Query("select * from marks")
    fun getMarks():List<Mark>

    @Query("select * from marks where student_id = :studentId")
    fun getMarks(studentId: Int):List<Mark>

    @Delete
    fun deleteMark(mark: Mark)

    @Insert
    fun addMark(mark: Mark)
}