package id.ac.umn.kidshub.data.profile

sealed class ProfileUIEvent {
    data class FirstNameChanged (val firstName: String): ProfileUIEvent()

    data class LastNameChanged (val lastName: String): ProfileUIEvent()
    object UpdateUserButtonClicked : ProfileUIEvent()
}