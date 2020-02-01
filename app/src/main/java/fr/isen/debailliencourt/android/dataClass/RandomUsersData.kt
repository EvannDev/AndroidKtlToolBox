package fr.isen.debailliencourt.android.dataClass

data class RandomUsersData(
    val result: Result
)

data class Result(
    val name: Name?=null,
    val location: Location?=null,
    val email:String?=null
)

data class Name(
    val title: String?=null,
    val first: String?=null,
    val last: String?=null
)

data class Location(
    val street: Street?=null,
    val city: String?=null,
    val state: String?=null,
    val country: String?=null,
    val postcode: String?=null,
    val coordinate: Coordinate?=null,
    val timezone: Timezone?=null
)

data class Street(
    val number: String?=null,
    val name: String?=null
)

data class Coordinate(
    val latitude: String?=null,
    val longitude: String?=null
)

data class Timezone(
    val offset: String?=null,
    val description: String?=null
)
