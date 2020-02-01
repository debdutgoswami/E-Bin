package com.example.dyco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountType extends AppCompatActivity {

    CircleImageView users,employee,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);
        users = findViewById(R.id.user);
        employee = findViewById(R.id.employee);
        admin = findViewById(R.id.admin);
    }

    @Override
    protected void onStart() {
        super.onStart();

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = Boolean.valueOf(SharedPrefs.readSharedSetting(AccountType.this,"sharedPrefs","true"));
                if(check){
                    startActivity(new Intent(AccountType.this, login.class));
                }
                else {
                    startActivity(new Intent(AccountType.this, MainActivity.class));
                    finish();
                }
            }

        });

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = Boolean.valueOf(SharedPrefs.readSharedSetting(AccountType.this,"sharedPrefs","true"));
                if(check){
                    startActivity(new Intent(AccountType.this, login.class));
                }
                else {
                    startActivity(new Intent(AccountType.this, MainActivity.class));
                    finish();
                }
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = Boolean.valueOf(SharedPrefs.readSharedSetting(AccountType.this,"sharedPrefs","true"));
                if(check){
                    startActivity(new Intent(AccountType.this, login.class));
                }
                else {
                    startActivity(new Intent(AccountType.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
