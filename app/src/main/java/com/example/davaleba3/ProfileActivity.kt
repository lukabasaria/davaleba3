package com.example.group1FirebaseProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.example.davaleba3.R
import com.example.davaleba3.UpdatePasswordActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private val Firebase.database: Any
    get() {
        TODO("Not yet implemented")
    }


class ProfileActivity : AppCompatActivity() {

    private lateinit var imageView : ImageView
    private lateinit var userNameTV : TextView
    private lateinit var userNameET : EditText
    private lateinit var userUrlET : EditText
    private lateinit var uploadBtn : Button

    private val db = Firebase.database.getReference("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        listeners()

        db.ch ild(FirebaseAuth.getInstance().uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo = snapshot.getValue(User::class.java) ?: return
//                if (userInfo == null) return
                Glide.with(this@ProfileActivity).load(userInfo.url).into(imageView)
                userNameTV.text = userInfo.name
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ProfileActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
    class ProfileActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_profile)

            findViewById<Button>(R.id.button_logout).setOnClickListener {
                finishAffinity()
            }

            findViewById<Button>(R.id.button_update_password).setOnClickListener {
                val intent = Intent(this@ProfileActivity, UpdatePasswordActivity::class.java)
                startActivity(intent)
            }
        }

    }


    class ProfileActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_profile)


            val logoutButton = findViewById<Button>(R.id.logout_button)
            logoutButton.setOnClickListener {
                finishAffinity()
            }


            val updatePasswordButton = findViewById<Button>(R.id.update_password_button)
            updatePasswordButton.setOnClickListener {
                val intent = Intent(this, UpdatePasswordActivity::class.java)
                startActivity(intent)
            }
        }

        class UpdatePasswordActivity {

        }
    }

    private fun listeners() {
        uploadBtn.setOnClickListener {
            val name = userNameET.text.toString()
            val url = userUrlET.text.toString()
            val userInfo = User(name, url)
//            Glide.with(this).load(url).into(imageView)
            db.child(FirebaseAuth.getInstance().uid!!).setValue(userInfo)
        }
    }

    private fun init(){
        imageView = findViewById(R.id.imageView)
        userNameTV = findViewById(R.id.userNameTV)
        userNameET = findViewById(R.id.userNameET)
        userUrlET = findViewById(R.id.userUrlET)
        uploadBtn = findViewById(R.id.uploadBtn)
    }
}

private fun Any.getReference(s: String): Any {

}

