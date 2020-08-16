package com.doubleg.doubleg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doubleg.doubleg.R;
import com.doubleg.doubleg.communication.NetworkFunctions;
import com.doubleg.doubleg.communication.NetworkRequestListener;
import com.doubleg.doubleg.utilities.Constants;
import com.doubleg.doubleg.utilities.Utilities;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText,passwordEditText,nameEditText,lastnameEditText,mailEditText;
    private Button boton;
    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.editTextTextPersonName2);
        lastnameEditText = findViewById(R.id.editTextTextPersonName4);
        usernameEditText = findViewById(R.id.editTextTextPersonName5);
        mailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword2);
        boton = findViewById(R.id.button3);
        loginLink = findViewById(R.id.textView6);
        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        });
        boton.setOnClickListener(v -> {
            register();
        });
    }

    private void register()
    {
        String name,lastname,username,email,password;
        name = nameEditText.getText().toString().trim();
        lastname = lastnameEditText.getText().toString().trim();
        username = usernameEditText.getText().toString().trim();
        email = mailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();

        if(Utilities.checkEmptyString(new String[]{name,lastname,username,email,password}))
        {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        //Debo chequear la longitud de la contraseña
        if(password.length()< Constants.MIN_PASSWORD_LENGTH)
        {
            Toast.makeText(this, "La contraseña debe tener mas de 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        NetworkFunctions.createUser(this,name,lastname,username,email,password,new NetworkRequestListener<Boolean>(){

            @Override
            public void getResult(Boolean object) {

            }

            @Override
            public void getError(String s) {

            }
        });


    }
}
