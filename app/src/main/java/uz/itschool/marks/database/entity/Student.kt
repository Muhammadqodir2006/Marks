package uz.itschool.marks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "students", foreignKeys = [
        ForeignKey(Group::class, ["id"], ["group_id"])
    ]
)
class Student(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var login: String,
    var password: String,
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastName: String,
    var group_id: Int,
)