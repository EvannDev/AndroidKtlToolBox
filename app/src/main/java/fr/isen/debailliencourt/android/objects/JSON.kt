package fr.isen.debailliencourt.android.objects

import com.google.gson.Gson
import java.io.File
import java.io.FileReader

class JSON {
    private var filepath: String = ""
    private val gson = Gson()

    constructor() {}
    constructor(path: String) {
        setPath(path)
    }


    private fun setPath(path: String): JSON {
        filepath = path + "user.json"
        return this
    }

    fun save(user: DataUser): JSON {
        File(filepath).writeText(gson.toJson(user))

        return this
    }

    fun load(): DataUser {
        return gson.fromJson(FileReader(filepath), DataUser::class.java)
    }
}