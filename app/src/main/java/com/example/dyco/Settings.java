package com.example.dyco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settings extends AppCompatActivity {
    RelativeLayout log_out,delete_acc,userDetails,update,share,help,language,liveChat,contactUs,rateUs,aboutUs;

    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private StorageReference reference = firebaseStorage.getReference();

    public static String documentId;
    CircleImageView userImage;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        log_out = findViewById(R.id.rl9);
        delete_acc = findViewById(R.id.rl10);
        userDetails = findViewById(R.id.UserDetails);
        update =findViewById(R.id.rl1);
        share =findViewById(R.id.rl2);
        help = findViewById(R.id.rl3);
        language =findViewById(R.id.rl4);
        liveChat = findViewById(R.id.rl5);
        contactUs = findViewById(R.id.rl6);
        rateUs =findViewById(R.id.rl7);
        aboutUs =findViewById(R.id.rl8);
        userImage = findViewById(R.id.userPicDetails);
        userName = findViewById(R.id.userNameDetails);

        db.collection("users")
                .whereEqualTo("userUid",firebaseAuth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> documentSnapshots = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot documentSnapshot : documentSnapshots){
                            documentId = documentSnapshot.getId();
                        }
                        db.collection("users")
                                .document(documentId)
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        userName.setText(documentSnapshot.get("name").toString());
                                        Glide.with(Settings.this).load(Uri.parse(documentSnapshot.get("profilePic").toString())).into(userImage);

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        userDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,UserProfile.class);
                startActivity(intent);
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedPrefs.saveSharedSetting(Settings.this,"sharedPrefs","true");
                Toast.makeText(Settings.this, "Log out Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        });

        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Settings.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                            SharedPrefs.saveSharedSetting(Settings.this,"sharedPrefs","true");
                            Intent intent = new Intent(Settings.this, login.class);
                            Settings.this.startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(Settings.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,WebPageView.class);
                intent.putExtra("Data","https://500-error.netlify.com");
                startActivity(intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,WebPageView.class);
                intent.putExtra("Data","https://500-error.netlify.com");
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,WebPageView.class);
                intent.putExtra("Data","https://500-error.netlify.com");
                startActivity(intent);
            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,WebPageView.class);
                intent.putExtra("Data","https://500-error.netlify.com");
                startActivity(intent);
            }
        });

        liveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,WebPageView.class);
                intent.putExtra("Data","https://500-error.netlify.com");
                startActivity(intent);
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://e-bin.netlify.com";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try{
                    Settings.this.startActivity(intent);
                }catch (Exception e){
                    intent.setPackage(null);
                    Settings.this.startActivity(intent);
                }
            }
        });

        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,WebPageView.class);
                intent.putExtra("Data","https://tiny.cc/70pajz");
                startActivity(intent);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://e-bin.netlify.com";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try{
                    Settings.this.startActivity(intent);
                }catch (Exception e){
                    intent.setPackage(null);
                    Settings.this.startActivity(intent);
                }
            }
        });

    }
}
