package com.doubleg.doubleg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doubleg.doubleg.R;
import com.doubleg.doubleg.communication.NetworkFunctions;
import com.doubleg.doubleg.communication.NetworkRequestListener;
import com.doubleg.doubleg.data.UserData;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText,passwordEditText;
    private Button boton;
    private TextView linkRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.editTextTextPersonName);
        passwordEditText = findViewById(R.id.editTextTextPassword);

        boton = findViewById(R.id.button);
        boton.setOnClickListener(v -> {
            login();
        });
        linkRegistrar = findViewById(R.id.registerLink);
        linkRegistrar.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        });
    }

    private void login()
    {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if(username.equals("") || password.equals(""))
        {
            Toast.makeText(this, "Debe ingresar el nombre de usuario y la contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }
        //Ahora inicio el proceso de login
        NetworkFunctions.login(this,username,password,new NetworkRequestListener<UserData>(){
            @Override
            public void getResult(UserData object) {
                //Llego algo
                Log.d("TAG","Me llego la data del usuario");
            }

            @Override
            public void getError(String s) {
                Log.d("TAG",s);
            }
        });

    }
}
