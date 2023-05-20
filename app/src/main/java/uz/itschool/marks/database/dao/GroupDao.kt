package uz.itschool.marks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.marks.database.entity.Group

@Dao
interface GroupDao {
    @Query("select * from groups")
    fun getGroups():List<Group>

    @Query("select * from groups where id=:groupId")
    fun getGroup(groupId:Int):Group

    @Insert
    fun addGroup(group: Group)
}