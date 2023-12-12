package id.ac.umn.kidshub.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: UIEvent) {
        when(event) {
            is UIEvent.UsernameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(username = event.username)
            }
            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(email = event.email)
            }
            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(password = event.password)
            }
            is UIEvent.ConfirmPasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(confirmPassword = event.confirmPassword)
            }
        }
    }
}