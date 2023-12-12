package id.ac.umn.kidshub.data

sealed class UIEvent {
    data class UsernameChanged(val username: String): UIEvent()
    data class EmailChanged(val email: String): UIEvent()
    data class PasswordChanged(val password: String): UIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String): UIEvent()
}