package id.ac.umn.kidshub.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import id.ac.umn.kidshub.data.rules.Validator
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen

class LoginViewModel: ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    private var allValidationPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    var loginValidationPassed = MutableLiveData<Boolean?>()

    var loginStatus = MutableLiveData<Boolean?>()

    fun onEvent(event: LoginUIEvent) {
        validateDataWithRules()
        when(event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
    }

    private fun login() {

        val email = loginUIState.value.email
        val password = loginUIState.value.password

        loginInProgress.value = true

        Log.d(TAG, "Inside_login()")
        Log.d(TAG, "email: $email")
        Log.d(TAG, "password: $password")

        if (allValidationPassed.value) {
            FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    Log.d(TAG, "Inside_login_success")
                    Log.d(TAG, "${it.isSuccessful}")

                    if (it.isSuccessful) {
                        loginStatus.value = true
                        loginInProgress.value = false
                        loginValidationPassed.value = true

                        NavigationRouter.navigateTo(Screen.HomeScreen)
                    }
                }
                .addOnFailureListener {
                    loginStatus.value = false
                    loginInProgress.value = false
                    loginValidationPassed.value = true

                    Log.d(TAG, "Inside_login_failure")
                    Log.d(TAG, "${it.message}")
                    Log.d(TAG, "${loginStatus.value}")
                }
        }else {
            loginStatus.value = null
            loginInProgress.value = false
            loginValidationPassed.value = false

            Log.d(TAG, "Inside_login_failure")
            Log.d(TAG, "Validation failed")
            Log.d(TAG, "${loginStatus.value}")
        }
    }

    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(
            loginUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = !emailResult.status,
            passwordError = !passwordResult.status
        )

        allValidationPassed.value = emailResult.status && passwordResult.status
    }

}