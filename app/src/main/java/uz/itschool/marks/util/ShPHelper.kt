package uz.itschool.marks.util

import android.content.Context

class ShPHelper private constructor(context: Context) {
    val shared = context.getSharedPreferences("data", 0)
    val edit = shared.edit()


    companion object{
        private var instance:ShPHelper? = null
        fun getInstance(context: Context): ShPHelper {
            if (instance == null){
                instance = ShPHelper(context)
            }
            return instance!!
        }
    }

    fun setUser(id :String, status:String){
        edit.putString("id", id)
        edit.putString("status", status).apply()
    }
    fun getUser(): List<String>? {
        val id = shared.getString("id", "")!!
        val status = shared.getString("status", "")!!
        if (id == ""){
            return null
        }
        return listOf(id, status)
    }
}