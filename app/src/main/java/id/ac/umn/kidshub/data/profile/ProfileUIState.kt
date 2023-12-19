package id.ac.umn.kidshub.data.profile

data class ProfileUIState(
    var firstName :String = "",
    var lastName :String = "",

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
)
