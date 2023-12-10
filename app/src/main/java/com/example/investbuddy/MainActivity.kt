package com.example.investbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.investbuddy.data.UserPreferences
import com.example.investbuddy.ui.auth.AuthActivity
import com.example.investbuddy.ui.home.HomeActivity
import com.example.investbuddy.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if (it == null) AuthActivity ::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}