package id.ac.umn.kidshub.data.home.users

data class UsersData(
    var userId: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var role: String = "",
    var exp: Int = 0,
)
