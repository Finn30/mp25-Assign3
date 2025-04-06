package com.example.loginregister

import android.annotation.SuppressLint
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
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etInput = findViewById<EditText>(R.id.etInput)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        var registeredUser: User? = null

        registeredUser = intent.getParcelableExtra("registered_user")

        var isPasswordVisible = false

        etPassword.setOnTouchListener { v, event ->
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                val drawable = etPassword.compoundDrawables[drawableEnd]

                if (drawable != null && event.rawX >= (etPassword.right - drawable.bounds.width() - etPassword.paddingEnd)) {
                    isPasswordVisible = !isPasswordVisible

                    if (isPasswordVisible) {
                        etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_lock, 0, R.drawable.ic_visibilty, 0
                        )
                    } else {
                        etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_lock, 0, R.drawable.ic_visibilty_off, 0
                        )
                    }

                    etPassword.setSelection(etPassword.text.length)
                    return@setOnTouchListener true
                }
            }
            false
        }

        btnLogin.setOnClickListener {
            val input = etInput.text.toString()
            val password = etPassword.text.toString()

            if (input.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email/username and password", Toast.LENGTH_SHORT).show()
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