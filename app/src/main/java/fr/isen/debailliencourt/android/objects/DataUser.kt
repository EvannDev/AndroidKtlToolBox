package fr.isen.debailliencourt.android.objects

class DataUser {
    var name: String = ""
    var surname: String = ""
    var birthday: String = ""


    constructor() {}

    constructor(name: String, surname: String, birthday: String) {
        this.name = name
        this.surname = surname
        this.birthday = birthday
    }

    override fun toString(): String {
        return "surname: $surname, name: $name, birthday: $birthday"
    }
}