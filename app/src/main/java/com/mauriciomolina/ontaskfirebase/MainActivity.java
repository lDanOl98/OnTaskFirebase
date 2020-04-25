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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;
    private Button mButtonSendToLogin;

    //Variables de los datos que se van a registrar

    private String Name="" ;
    private String Email="" ;
    private String Password="" ;


    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEditTextName =(EditText)findViewById(R.id.editTextName);
        mEditTextEmail =(EditText)findViewById(R.id.editTextEMail);
        mEditTextPassword =(EditText)findViewById(R.id.editTextPassword);
        mButtonRegister =(Button)findViewById(R.id.btnRegister);
        mButtonSendToLogin =(Button)findViewById(R.id.btnSendToLogin);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = mEditTextName.getText().toString();
                Email = mEditTextEmail.getText().toString();
                Password = mEditTextPassword.getText().toString();

                if (!Name.isEmpty() && !Email.isEmpty() && !Password.isEmpty()) {

                    if(Password.length()>= 6){

                    }
                    else{
                        Toast.makeText(MainActivity.this, "La contraseña debe tener por lo menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                    registerUser();
                }
                else{
                    Toast.makeText(MainActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        mButtonSendToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                //finish();
            }
        });
    }
    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put( "Name", Name);
                    map.put( "Email", Email);
                    map.put( "Password", Password);
                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(MainActivity.this, GuiaActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "no se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this , GuiaActivity.class));
            finish();
        }
    }
}



