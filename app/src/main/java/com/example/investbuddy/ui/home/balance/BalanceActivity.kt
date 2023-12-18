package com.example.investbuddy.ui.home.balance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.investbuddy.R
import com.example.investbuddy.ui.detail.BalanceFragment

class BalanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, BalanceFragment())
        fragmentTransaction.commit()
    }
}