package com.example.dyco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Feedback extends AppCompatActivity {

    TextView clickHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        clickHere =findViewById(R.id.ClickMe);
    }

    @Override
    protected void onStart() {
        super.onStart();
        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feedback.this,WebPageView.class);
                intent.putExtra("Data","https://docs.google.com/forms/d/e/1FAIpQLSdmUI3ZdfSigoacaJFT1hl8M1v9xpfU2P4vYJYxzCui9QXzzQ/viewform");
                startActivity(intent);
            }
        });
    }
}
