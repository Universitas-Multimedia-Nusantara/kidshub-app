package id.ac.umn.kidshub.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.ac.umn.kidshub.data.rules.Validator
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen

class SignupViewModel(): ViewModel() {

    private val TAG = SignupViewModel::class.simpleName

    var signupUIState = mutableStateOf(SignupUIState())

    var allValidationPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent) {
        validateDataWithRules()
        when(event) {
            is SignupUIEvent.FirstNameChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUIEvent.LastNameChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignupUIEvent.PasswordChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        Log.d(TAG, "Inside signUp()")
        printState()
        if (allValidationPassed.value) {
            createUserInFirebase(
                firstName = signupUIState.value.firstName,
                lastName = signupUIState.value.lastName,
                email = signupUIState.value.email,
                password = signupUIState.value.password
            )
        }
    }

    private fun validateDataWithRules() {

        val firstNameResult = Validator.validateFirstName(
            firstName = signupUIState.value.firstName
        )

        val lastNameResult  = Validator.validateLastName(
            lastName = signupUIState.value.lastName
        )

        val emailResult     = Validator.validateEmail(
            email = signupUIState.value.email
        )

        val passwordResult  = Validator.validatePassword(
            password = signupUIState.value.password
        )

        Log.d(TAG, "Inside validateDataWithRules()")
        Log.d(TAG, "emailResult = $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")

        signupUIState.value = signupUIState.value.copy(
            firstNameError = !firstNameResult.status,
            lastNameError = !lastNameResult.status,
            emailError = !emailResult.status,
            passwordError = !passwordResult.status,
        )

        allValidationPassed.value = (
                firstNameResult.status &&
                lastNameResult.status &&
                emailResult.status &&
                passwordResult.status
        )

    }

    private fun printState() {
        Log.d(TAG, "Inside printState()")
        Log.d(TAG, signupUIState.value.toString())
    }

    private fun  createUserInFirebase(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {

        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside onComleteListener()")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value = false

                FirebaseFirestore
                    .getInstance()
                    .collection("users")
                    .document(it.result!!.user!!.uid)
                    .set(
                        hashMapOf(
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "email" to email,
                            "role" to "user",
                            "exp" to 0,
                        )
                    )
                    .addOnSuccessListener {
                        Log.d(TAG, "Inside onSuccessListener()")
                        Log.d(TAG, "isSuccessful = true")

                        NavigationRouter.navigateTo(Screen.LoginScreen)
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "Inside onFailureListener()")
                        Log.d(TAG, "Exception = ${it.message}")
                        Log.d(TAG, "Exception = ${it.localizedMessage}")
                    }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside onFailureListener()")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }
}