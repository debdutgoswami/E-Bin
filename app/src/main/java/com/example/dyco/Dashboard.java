package com.example.dyco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Dashboard extends AppCompatActivity {

    CompactCalendarView calendarView;
    TextView textView;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());

    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private StorageReference reference = firebaseStorage.getReference();

    List<QrCodeData> qrCodeDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        calendarView =findViewById(R.id.calendarView);
        textView = findViewById(R.id.text1);
        //calendarView.setUseThreeLetterAbbreviation(true);
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());

       /* db.collection("qrData")
                .whereArrayContains("userId",firebaseAuth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        //qrCodeDataList.clear();
                        for(DocumentSnapshot snapshot : snapshotList){
                            QrCodeData qrCodeData = new QrCodeData(snapshot.getString("userUid"),snapshot.getString("timeSpan"),"random");
                            qrCodeDataList.add(qrCodeData);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Dashboard.this,"QR code getting Failed",Toast.LENGTH_SHORT).show();
                    }
                });

                */

        db.collection("Dashboard"+firebaseAuth.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                           int eventColor = Integer.parseInt(documentSnapshot.get("colorR").toString());
                           long eventTime = Long.parseLong(documentSnapshot.get("timeInMillsR").toString());
                           Event event = new Event(eventColor,eventTime);
                           calendarView.addEvent(event);
                        }
                    }
                });

        textView.setText(dateFormat.format(new Date()));

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                    textView.setText(dateFormat.format(firstDayOfNewMonth));
            }
        });

    }
}
