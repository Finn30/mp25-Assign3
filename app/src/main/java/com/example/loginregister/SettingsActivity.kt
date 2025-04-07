package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val user = intent.getParcelableExtra<User>("user_data")

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.selectedItemId = R.id.menuSettings
        bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.itemId == bottomNavigationView.selectedItemId) return@setOnItemSelectedListener true
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
            }
            true
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, LandingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }

    }
}