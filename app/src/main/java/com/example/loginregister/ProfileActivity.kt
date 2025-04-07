package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvHome = findViewById<TextView>(R.id.tvHome)
        val user = intent.getParcelableExtra<User>("user_data")

        tvHome.text = "Name\t: ${user?.name}\nUsername\t: ${user?.username}\nEmail\t: ${user?.email}"

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.selectedItemId = R.id.menuProfile
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
                R.id.menuSettings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
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