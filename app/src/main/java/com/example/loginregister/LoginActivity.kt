package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        var registeredUser: User? = null
        val btnToggleLoginPassword = findViewById<Button>(R.id.btnToggleLoginPassword)
        var isLoginPassVisible = false

        registeredUser = intent.getParcelableExtra("registered_user")

        btnToggleLoginPassword.setOnClickListener {
            isLoginPassVisible = !isLoginPassVisible
            etPassword.transformationMethod = if (isLoginPassVisible)
                HideReturnsTransformationMethod.getInstance()
            else
                PasswordTransformationMethod.getInstance()

            etPassword.setSelection(etPassword.text.length)

            btnToggleLoginPassword.text = if (isLoginPassVisible) "👁" else "🙈"
        }


        btnLogin.setOnClickListener {
            val input = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (input.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email/username and password", Toast.LENGTH_SHORT).show()
//            } else if (!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
//                etEmail.error = "Invalid email format"
//                etEmail.requestFocus()
            } else if (registeredUser != null &&
                (input == registeredUser.email || input == registeredUser.username) &&
                password == registeredUser.password) {

                val intent = Intent(this, LandingActivity::class.java)
                intent.putExtra("user_data", registeredUser)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incorrect email/username or password", Toast.LENGTH_SHORT).show()
            }
        }


        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}