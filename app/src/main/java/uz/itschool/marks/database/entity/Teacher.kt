package uz.itschool.marks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "teachers", foreignKeys = [
    ForeignKey(Subject::class, ["id"], ["subject_id"])
])
class Teacher (
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    var login:String,
    var password:String,
    @ColumnInfo(name = "first_name")
    var firstName:String,
    @ColumnInfo(name = "last_name")
    var lastName:String,
    @ColumnInfo(name = "subject_id")
    var subjectId:Int
        )