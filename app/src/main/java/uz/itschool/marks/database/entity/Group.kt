package uz.itschool.marks.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
class Group(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String
)