package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val user = intent.getParcelableExtra<User>("user_data")

        tvWelcome.text = "Welcome, ${user?.name}!"
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("user_data", user)
                    startActivity(intent)
                    true
                }
                R.id.menuSearch -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("user_data", user)
                    startActivity(intent)
                    true
                }
                R.id.menuProfile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.putExtra("user_data", user)
                    startActivity(intent)
                    true
                }
                R.id.menuSettings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    intent.putExtra("user_data", user)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

    }
}