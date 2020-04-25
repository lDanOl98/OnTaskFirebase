package com.mauriciomolina.ontaskfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class GuiaActivity extends AppCompatActivity {


    private Button mButtonSingOut;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);
        mAuth = FirebaseAuth.getInstance();
        mButtonSingOut =(Button)findViewById(R.id.btnSingOut);
        mButtonSingOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(GuiaActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
