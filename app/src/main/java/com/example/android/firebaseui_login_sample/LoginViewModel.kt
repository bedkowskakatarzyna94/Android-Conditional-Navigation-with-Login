package com.example.android.firebaseui_login_sample

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.preference.PreferenceManager
import kotlin.random.Random

class LoginViewModel : ViewModel() {

    companion object {
        val androidFacts = arrayOf(
            "The first commercial Android device was launched in September 2008",
            "The Android operating system has over 2 billion monthly active users",
            "The first Android version (1.0) was released on September 23, 2008",
            "The first smart phone running Android was the HTC Dream called the T-Mobile G1 in " + "some countries"
        )

    }

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    fun getFactToDisplay(context: Context): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val factTypePreferenceKey = context.getString(R.string.preference_fact_type_key)
        val defaultFactType = context.resources.getStringArray(R.array.fact_type)[0]
        sharedPreferences.getString(factTypePreferenceKey, defaultFactType)

        return androidFacts[Random.nextInt(0, androidFacts.size)]
    }
}
