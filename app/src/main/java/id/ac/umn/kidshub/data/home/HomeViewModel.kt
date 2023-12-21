package id.ac.umn.kidshub.data.home

import android.app.DownloadManager
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.ac.umn.kidshub.data.home.books.BooksData
import id.ac.umn.kidshub.data.home.books.BooksDataProvider
import id.ac.umn.kidshub.data.home.users.UsersData
import id.ac.umn.kidshub.data.home.users.UsersDataProvider
import id.ac.umn.kidshub.data.home.videos.VideosData
import id.ac.umn.kidshub.data.home.videos.VideosDataProvider
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Calendar

class HomeViewModel: ViewModel(), CoroutineScope {

    private val TAG = HomeViewModel::class.simpleName

    val userData = UsersDataProvider.usersData

    val isUserLoggedIn: MutableLiveData<Boolean?> = MutableLiveData()

    private val currentTime = System.currentTimeMillis()

    val greeting = getGreetingByTime(currentTime)

    fun checkForActiveSession() {
        if(FirebaseAuth.getInstance().currentUser != null) {
            isUserLoggedIn.value = true

            Log.d(TAG, "Valid Session")
        } else {
            isUserLoggedIn.value = false

            Log.d(TAG, "User is not logged in")
        }
    }

    private fun getGreetingByTime(currentTime: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTime
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when {
            hour < 12 -> "Good Morning !"
            hour < 16 -> "Good Afternoon !"
            hour < 21 -> "Good Evening !"
            else -> "Good Night !"
        }
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

    suspend fun getVideosData(): List<VideosData>  {
        val deferred = CompletableDeferred<List<VideosData>>()

        coroutineScope {
            launch(Dispatchers.IO) {
                try {
                    val db = FirebaseFirestore.getInstance()

                    db.collection("videos")
                        .get()
                        .addOnSuccessListener { snapshot ->
                            for (document in snapshot) {
                                val videosData = VideosData(
                                    document.data["id"].toString(),
                                    document.data["title"].toString(),
                                    document.data["thumbnail"].toString(),
                                    document.data["code"].toString(),
                                    document.data["url"].toString(),
                                    document.data["description"].toString(),
                                    document.data["uploader"].toString(),
                                )

                                VideosDataProvider.videosDataList.add(videosData)

                                Log.d(TAG, "${document.id} => ${document.data}")
                            }
                        }
                        .await()
                    deferred.complete(VideosDataProvider.videosDataList)
                } catch (e: Exception) {
                    Log.d(TAG, "Exception: $e")
                    deferred.completeExceptionally(e)
                }
            }
        }
        return deferred.await()
    }

    fun clearVideosDataList() {
        VideosDataProvider.videosDataList.clear()
    }

    fun clearBooksDataList() {
        BooksDataProvider.booksDataList.clear()
    }

    suspend fun getBooksData(): List<BooksData>  {
        val deferred = CompletableDeferred<List<BooksData>>()

        coroutineScope {
            launch(Dispatchers.IO) {
                try {
                    val db = FirebaseFirestore.getInstance()

                    db.collection("books")
                        .get()
                        .addOnSuccessListener { snapshot ->
                            for (document in snapshot) {
                                val booksData = BooksData(
                                    document.data["id"].toString(),
                                    document.data["cover"].toString(),
                                    document.data["title"].toString(),
                                    document.data["author"].toString(),
                                    document.data["description"].toString(),
                                    document.data["publisher"].toString(),
                                )

                                BooksDataProvider.booksDataList.add(booksData)

                                Log.d(TAG, "${document.id} => ${document.data}")
                            }
                        }
                        .await()
                    deferred.complete(BooksDataProvider.booksDataList)
                } catch (e: Exception) {
                    Log.d(TAG, "Exception: $e")
                    deferred.completeExceptionally(e)
                }
            }
        }
        return deferred.await()
    }

    fun updateUserExp() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        val document = db.collection("users")
            .document(userId!!)
            .update("exp", expIncrement(userData.exp))
            .addOnSuccessListener {

                userData.exp = expIncrement(userData.exp)

                Log.d(TAG, "Exp successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "Error updating document", e)
            }
    }

    private fun expIncrement (exp: Int): Int {
       val  randomInt = (25..100).random()
        return exp + randomInt
    }
    override val coroutineContext = Dispatchers.Main
}