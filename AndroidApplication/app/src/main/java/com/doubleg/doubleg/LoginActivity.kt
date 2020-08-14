package com.doubleg.doubleg

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var usernameTextView : TextView
    private lateinit var passwordTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        usernameTextView = findViewById(R.id.editTextTextPersonName)
        passwordTextView = findViewById(R.id.editTextTextPassword)
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            login()
        }
    }

    private fun login()
    {
        var username = usernameTextView.text.trim()
        var password = passwordTextView.text.trim()
        if(username.equals("") || password.equals(""))
        {
            Toast.makeText(this,
                "Ingrese el nombre de usuario y la contraseña",
                Toast.LENGTH_SHORT)
                .show()
            return
        }
        //Aca debo entrar a buscar los datos a la bd


    }
}