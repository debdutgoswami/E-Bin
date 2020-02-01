package com.example.dyco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QrScan extends AppCompatActivity {

    public static TextView scanCode;
    Button scan,done;
    ProgressBar progressBar;

    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private StorageReference reference = firebaseStorage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);
        scanCode = findViewById(R.id.qrCodeRead);
        scan = findViewById(R.id.scanButton);
        done = findViewById(R.id.scanDone);
        progressBar = findViewById(R.id.QrProgressbar);

    }

    @Override
    protected void onStart() {
        super.onStart();
    scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QrScan.this,ScanCodeActivity.class);
                startActivity(intent);
            }
        });


        //final String ScanRandomNumber = "1244589749";

        done.setOnClickListener(new View.OnClickListener() {
            String str = scanCode.getText().toString();
            final String userUid = str.substring(0,(str.length()-18));
            final String ScanRandomNumber = str.substring(userUid.length()+8,str.length());
            String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                db.collection("qrData")
                        .document(userUid+timeStamp)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String randomNumber = documentSnapshot.getString("random");
                                EventColor eventColor = new EventColor(""+Color.GREEN, ""+Calendar.getInstance().getTimeInMillis());
                                if(randomNumber.equals(ScanRandomNumber)){
                                    db.collection("Dashboard"+userUid)
                                            .document(timeStamp)
                                            .set(eventColor)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    progressBar.setVisibility(View.GONE);
                                                    onBackPressed();
                                                }
                                            });
                                }
                                else{
                                    Toast.makeText(QrScan.this,"Not Updated Correctly",Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }
        });
    }
}
