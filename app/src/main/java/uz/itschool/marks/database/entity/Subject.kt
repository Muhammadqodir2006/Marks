package uz.itschool.marks.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
class Subject(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name:String
)