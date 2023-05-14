package uz.itschool.marks.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.itschool.marks.database.dao.*

abstract class AppDataBase : RoomDatabase() {
    abstract fun getGroupDao(): GroupDao
    abstract fun getMarkDao(): MarkDao
    abstract fun getStudentDao(): StudentDao
    abstract fun getSubjectDao(): SubjectDao
    abstract fun getTeacherDao(): TeacherDao
    abstract fun getTeacherGroupSubjectDao(): TeacherGroupSubjectDao

    companion object {
        private var instance: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "app_db")
                    .allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}