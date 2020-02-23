package com.example.biometricentriessystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //to connect with xml resources
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.LoginBtn);
        mCreateBtn = findViewById(R.id.creatText);

        //validation
        mLoginBtn.setOnClickListener(new View.OnClickListener() {


            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            @Override
            public void onClick(View v) {

               if (TextUtils.isEmpty(email))

                {
                    mEmail.setError("Email is required");

                }
               if (TextUtils.isEmpty(password))
               {
                   mPassword.setError("password is required");
               }
fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful())
        {
            Toast.makeText(Login.this, "logged in successfully",Toast.LENGTH_SHORT ).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        else{
           // Toast.makeText(Login.this,"error !"+  task.getException().getMessage() , Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

});
            }
        });

    }

}

