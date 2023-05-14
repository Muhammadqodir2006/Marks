package uz.itschool.marks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.marks.database.entity.Group

@Dao
interface GroupDao {
    @Query("select * from groups")
    fun getGroup():List<Group>

    @Insert
    fun addGroup(group: Group)
}