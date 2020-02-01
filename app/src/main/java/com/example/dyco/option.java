package com.example.dyco;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class option extends AppCompatActivity {
    Button log_out,delete_acc;
    TextView email_address;
    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.option);

        log_out = findViewById(R.id.log_out);
        delete_acc = (Button) findViewById(R.id.delete_account);
        email_address = findViewById(R.id.Email);
        email_address.setText("Welcome, "+user.getEmail());

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(option.this, "Log out Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(option.this, "Account Deleted", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(option.this, login.class);
                            option.this.startActivity(intent);

                        } else {
                            Toast.makeText(option.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
