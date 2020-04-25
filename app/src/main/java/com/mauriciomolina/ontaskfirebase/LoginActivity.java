package com.mauriciomolina.ontaskfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonResetPassword;
    private String Email= "";
    private String Password = "";

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextEmail= (EditText)findViewById(R.id.editTextEMail);
        mEditTextPassword = (EditText)findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();
        mButtonLogin = (Button)findViewById(R.id.btnLogin);
        mButtonResetPassword = (Button)findViewById(R.id.btnSendToResetPassword);


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = mEditTextEmail.getText().toString();
                Password =mEditTextPassword.getText().toString();
                if(!Email.isEmpty() && !Password.isEmpty()){
                    loginUser();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Complete los campos requeridos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, GuiaActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion, compruebe los datos y vuelvalo a intentar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
