package uz.itschool.marks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.itschool.marks.database.dao.*
import uz.itschool.marks.database.entity.*

@Database(entities = [Group::class, Mark::class, Student::class, Subject::class, Teacher::class, TeacherGroupSubject::class], version = 1)
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
                if (instance!!.getStudentDao().getStudents().isEmpty()){
                    initDB()
                }
            }
            return instance!!
        }

        private fun initDB() {
            instance!!.getGroupDao().addGroup(Group(name = "10-05"))
            instance!!.getGroupDao().addGroup(Group(name = "10-04"))
            instance!!.getGroupDao().addGroup(Group(name = "10-03"))

            instance!!.getSubjectDao().addSubject(Subject(name = "Matematika"))
            instance!!.getSubjectDao().addSubject(Subject(name = "Fizika"))
            instance!!.getSubjectDao().addSubject(Subject(name = "Informatika"))

            instance!!.getTeacherDao().addTeacher(Teacher(login = "t1", password = "1234", firstName = "teacher1", lastName =  "Karimov", subjectId = 1))
            instance!!.getTeacherDao().addTeacher(Teacher(login = "t2", password = "1234", firstName = "teacher2", lastName =  "Karimov", subjectId = 2))
            instance!!.getTeacherDao().addTeacher(Teacher(login = "t3", password = "1234", firstName = "teacher3", lastName =  "Karimov", subjectId = 3))

            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 1, groupId = 1, subjectId = 1))
            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 1, groupId = 2, subjectId = 1))
            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 1, groupId = 3, subjectId = 1))
            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 2, groupId = 1, subjectId = 2))
            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 2, groupId = 2, subjectId = 2))
            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 2, groupId = 3, subjectId = 2))
            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 3, groupId = 1, subjectId = 3))
            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 3, groupId = 2, subjectId = 3))
            instance!!.getTeacherGroupSubjectDao().addTeacherGroupSubject(TeacherGroupSubject(teacherId = 3, groupId = 3, subjectId = 3))

            instance!!.getStudentDao().addStudent(Student(login = "s1", password = "1234", firstName = "Student1", lastName = "Aliyev", group_id = 1))
            instance!!.getStudentDao().addStudent(Student(login = "s2", password = "1234", firstName = "Student2", lastName = "Aliyev", group_id = 1))
            instance!!.getStudentDao().addStudent(Student(login = "s3", password = "1234", firstName = "Student3", lastName = "Aliyev", group_id = 1))
            instance!!.getStudentDao().addStudent(Student(login = "s4", password = "1234", firstName = "Student4", lastName = "Aliyev", group_id = 1))
            instance!!.getStudentDao().addStudent(Student(login = "s5", password = "1234", firstName = "Student5", lastName = "Aliyev", group_id = 2))
            instance!!.getStudentDao().addStudent(Student(login = "s6", password = "1234", firstName = "Student6", lastName = "Aliyev", group_id = 2))
            instance!!.getStudentDao().addStudent(Student(login = "s7", password = "1234", firstName = "Student7", lastName = "Aliyev", group_id = 2))
            instance!!.getStudentDao().addStudent(Student(login = "s8", password = "1234", firstName = "Student8", lastName = "Aliyev", group_id = 2))
            instance!!.getStudentDao().addStudent(Student(login = "s9", password = "1234", firstName = "Student9", lastName = "Aliyev", group_id = 3))
            instance!!.getStudentDao().addStudent(Student(login = "s10", password = "1234", firstName = "Student10", lastName = "Aliyev", group_id = 3))
            instance!!.getStudentDao().addStudent(Student(login = "s11", password = "1234", firstName = "Student11", lastName = "Aliyev", group_id = 3))
            instance!!.getStudentDao().addStudent(Student(login = "s12", password = "1234", firstName = "Student12", lastName = "Aliyev", group_id = 3))

            ///////////////////////////////////////////////////////////////////////////////////////

            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 1, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 1, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 1, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 4, teacherGroupSubject = 1, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 4, teacherGroupSubject = 1, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 3, teacherGroupSubject = 1, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 3, teacherGroupSubject = 1, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 1, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 5, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 3, teacherGroupSubject = 5, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 4, teacherGroupSubject = 5, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 4, teacherGroupSubject = 5, studentId = 1))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 5, studentId = 1))


            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 2, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 4, teacherGroupSubject = 2, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 2, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 3, teacherGroupSubject = 2, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 2, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 3, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 3, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 4, teacherGroupSubject = 3, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 3, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 3, teacherGroupSubject = 3, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 3, studentId = 5))
            instance!!.getMarkDao().addMark(Mark(mark = 5, teacherGroupSubject = 3, studentId = 5))
        }
    }
}