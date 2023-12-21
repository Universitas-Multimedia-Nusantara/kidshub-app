package id.ac.umn.kidshub.data.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import id.ac.umn.kidshub.data.home.HomeViewModel
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen
import com.google.firebase.firestore.FirebaseFirestore
import id.ac.umn.kidshub.data.rules.Validator
import id.ac.umn.kidshub.data.signup.SignupUIEvent
import id.ac.umn.kidshub.data.signup.SignupUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class ProfileViewModel: ViewModel() {

    private val TAG = ProfileViewModel::class.simpleName

    private val homeViewModel = HomeViewModel()

    var profileUIState = mutableStateOf(SignupUIState())

    private var allValidationPassed = mutableStateOf(false)

    private var updateUserInProgress = mutableStateOf(false)

    val usersData = homeViewModel.userData

    val userExp: MutableLiveData<String> = MutableLiveData()

    fun onEvent(event: ProfileUIEvent) {
        validateDataWithRules()
        when(event) {
            is ProfileUIEvent.FirstNameChanged -> {
                profileUIState.value = profileUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is ProfileUIEvent.LastNameChanged -> {
                profileUIState.value = profileUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is ProfileUIEvent.UpdateUserButtonClicked -> {
                updateUser()
            }
        }
    }

    private fun validateDataWithRules() {

        val firstNameResult = Validator.validateFirstName(
            firstName = profileUIState.value.firstName
        )

        val lastNameResult  = Validator.validateLastName(
            lastName = profileUIState.value.lastName
        )


        Log.d(TAG, "Inside validateDataWithRules()")
        Log.d(TAG, "firstNameResult: $firstNameResult")
        Log.d(TAG, "lastNameResult: $lastNameResult")

        profileUIState.value = profileUIState.value.copy(
            firstNameError = !firstNameResult.status,
            lastNameError = !lastNameResult.status,
        )

        allValidationPassed.value = firstNameResult.status && lastNameResult.status
    }

    fun userExpDifferentiate(): Any {
        return when (usersData.exp) {
            in 0..500 -> {
                userExp.value = "Newcomer"
            }
            in 500..5000 -> {
                userExp.value = "Explorer"
            }
            else -> {
                userExp.value = "Researcher"
            }
        }
    }

    private fun updateUser() {
        val firstName = profileUIState.value.firstName
        val lastName = profileUIState.value.lastName

        updateUserInProgress.value = true

        Log.d(TAG, "Inside_updateUser()")
        Log.d(TAG, "firstName: $firstName")
        Log.d(TAG, "lastName: $lastName")

        if (allValidationPassed.value) {
            updateUserFromFirebase(
                firstName = firstName,
                lastName = lastName,
            )
        }
    }

     private fun updateUserFromFirebase(
         firstName: String,
         lastName: String,
     ) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()

         updateUserInProgress.value = true

        db.collection("users")
            .document(userId!!)
            .update(
                "firstName", firstName,
                "lastName", lastName,
            )
            .addOnSuccessListener {

                homeViewModel.userData.firstName = firstName
                homeViewModel.userData.lastName = lastName

                updateUserInProgress.value = false

                NavigationRouter.navigateTo(Screen.ProfileScreen)

                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    fun logout() {
        try {
            val firebaseAuth = FirebaseAuth.getInstance()

            homeViewModel.clearVideosDataList()
            homeViewModel.clearBooksDataList()

            firebaseAuth.signOut()

            val authStateListener = FirebaseAuth.AuthStateListener {
                if (it.currentUser == null) {
                    Log.d(TAG, "Inside_logout_success")
                    NavigationRouter.navigateTo(Screen.LoginScreen)
                } else {
                    Log.d(TAG, "Inside_logout_failure")
                }
            }

            firebaseAuth.addAuthStateListener(authStateListener)
        }catch (e: Exception) {
            Log.d(TAG, "Exception: $e")
        }
    }
}