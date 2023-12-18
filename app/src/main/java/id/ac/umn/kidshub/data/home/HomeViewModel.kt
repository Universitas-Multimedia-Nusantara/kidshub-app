package id.ac.umn.kidshub.data.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.ac.umn.kidshub.data.home.users.UsersData
import id.ac.umn.kidshub.data.home.users.UsersDataProvider
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel: ViewModel(), CoroutineScope {

    private val TAG = HomeViewModel::class.simpleName

    val userData = UsersDataProvider.usersData

    val isUserLoggedIn: MutableLiveData<Boolean?> = MutableLiveData()

    fun checkForActiveSession() {
        if(FirebaseAuth.getInstance().currentUser != null) {
            isUserLoggedIn.value = true

            Log.d(TAG, "Valid Session")
        } else {
            isUserLoggedIn.value = false

            Log.d(TAG, "User is not logged in")
        }
    }

    fun getCurrentTime (): String {
        return System.currentTimeMillis().toString()
    }

    suspend fun getUserData(): UsersData {
        val deferred = CompletableDeferred<UsersData>()

        coroutineScope {
            launch(Dispatchers.IO) {
                try {
                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                    val db = FirebaseFirestore.getInstance()

                    val document = db.collection("users")
                        .document(userId!!)
                        .get()
                        .await()

                    if (document != null) {
                        userData.userId = document.id
                        userData.firstName = document.data?.get("firstName").toString()
                        userData.lastName = document.data?.get("lastName").toString()
                        userData.email = document.data?.get("email").toString()
                        userData.password = document.data?.get("password").toString()
                        userData.role = document.data?.get("role").toString()
                        userData.exp = document.data?.get("exp").toString().toInt()

                        Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    } else {
                        Log.d(TAG, "No such document")
                    }

                    deferred.complete(userData)
                } catch (e: Exception) {
                    Log.d(TAG, "Exception: $e")
                    deferred.completeExceptionally(e)
                }
            }
        }

        return deferred.await()
    }

    override val coroutineContext = Dispatchers.Main
}