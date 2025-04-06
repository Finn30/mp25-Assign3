package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnTogglePassword = findViewById<Button>(R.id.btnTogglePassword)
        val btnToggleConfirmPassword = findViewById<Button>(R.id.btnToggleConfirmPassword)
        val etUsername = findViewById<EditText>(R.id.etUsername)

        var isPasswordVisible = false
        var isConfirmVisible = false

        btnTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            etPassword.transformationMethod = if (isPasswordVisible)
                HideReturnsTransformationMethod.getInstance()
            else
                PasswordTransformationMethod.getInstance()

            etPassword.setSelection(etPassword.text.length)

            btnTogglePassword.text = if (isPasswordVisible) "👁" else "🙈"
        }

        btnToggleConfirmPassword.setOnClickListener {
            isConfirmVisible = !isConfirmVisible
            etConfirmPassword.transformationMethod = if (isConfirmVisible)
                HideReturnsTransformationMethod.getInstance()
            else
                PasswordTransformationMethod.getInstance()

            etPassword.setSelection(etPassword.text.length)

            btnTogglePassword.text = if (isPasswordVisible) "👁" else "🙈"
        }

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            when {
                name.isEmpty() -> {
                    etName.error = "Name is required"
                    etName.requestFocus()
                }

                username.isEmpty() -> {
                    etUsername.error = "Username is required"
                    etUsername.requestFocus()
                }

                email.isEmpty() -> {
                    etEmail.error = "Email is required"
                    etEmail.requestFocus()
                }

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    etEmail.error = "Invalid email format"
                    etEmail.requestFocus()
                }

                password.length < 6 -> {
                    etPassword.error = "Password must be at least 6 characters"
                    etPassword.requestFocus()
                }

                !password.matches(".*[A-Z].*".toRegex()) ||
                        !password.matches(".*[a-z].*".toRegex()) ||
                        !password.matches(".*[0-9].*".toRegex()) -> {
                    etPassword.error = "Password must contain upper, lower, number"
                    etPassword.requestFocus()
                }

                confirmPassword != password -> {
                    etConfirmPassword.error = "Passwords do not match"
                    etConfirmPassword.requestFocus()
                }

                else -> {
                    val user = User(name, username, email, password)
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("registered_user", user)
                    Toast.makeText(
                        this,
                        "Registration successful. Please login.",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}