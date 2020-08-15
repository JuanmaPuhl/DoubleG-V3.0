package com.doubleg.doubleg.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doubleg.doubleg.R;
import com.doubleg.doubleg.communication.NetworkFunctions;
import com.doubleg.doubleg.communication.NetworkRequestListener;
import com.doubleg.doubleg.data.UserData;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText usernameEditText,passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }

    private void login()
    {
        String username = usernameEditText.getText().toString().trim();
        String password = usernameEditText.getText().toString().trim();
        if(username.equals("") || password.equals(""))
        {
            Toast.makeText(this, "Debe ingresar el nombre de usuario y la contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }
        //Ahora inicio el proceso de login
        NetworkFunctions.findUserByUsername(this,username,new NetworkRequestListener<UserData>(){
            @Override
            public void getResult(UserData object) {

            }

            @Override
            public void getError(String s) {

            }
        });

    }
}
