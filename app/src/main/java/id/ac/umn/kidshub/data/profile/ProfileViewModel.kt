package id.ac.umn.kidshub.data.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import id.ac.umn.kidshub.data.home.HomeViewModel
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen

class ProfileViewModel: ViewModel() {

    private val TAG = ProfileViewModel::class.simpleName

    private val homeViewModel = HomeViewModel()

    val usersData = homeViewModel.userData

    val userExp: MutableLiveData<String> = MutableLiveData()

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

    fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()

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
    }
}