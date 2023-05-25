package com.example.davaleba3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.wear.tiles.material.Button

class UpdatePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)

        findViewById<android.widget.Button>(R.id.button_save_password).setOnClickListener {
            val currentPassword = findViewById<EditText>(R.id.edittext_current_password).text.toString()
            val newPassword = findViewById<EditText>(R.id.edittext_new_password).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.edittext_confirm_password).text.toString()

            if (newPassword != confirmPassword) {
                Toast.makeText(this@UpdatePasswordActivity, "New password confirmation does not match", Toast.LENGTH_SHORT).show()
            } else {
                // Здесь нужно добавить код для изменения пароля
                Toast.makeText(this@UpdatePasswordActivity, "Password changed successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

}
