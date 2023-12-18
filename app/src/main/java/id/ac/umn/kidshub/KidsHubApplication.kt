package id.ac.umn.kidshub

import android.app.Application
import com.google.firebase.FirebaseApp

class KidsHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}