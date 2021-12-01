package com.minhien.musicappasm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.minhien.musicappasm.R;

import org.jetbrains.annotations.NotNull;

public class FCMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmactivity);

        Button btnToken = findViewById(R.id.btnToken);
        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<String> task) {
                        if (!task.isSuccessful()){
                            Log.d(">>>>>>>>>","get Token error: "+ task.getException());
                            return;
                        }
                        String token = task.getResult();
                        Log.i(">>>>>>>>>","get Token: "+ token);
                    }
                });
            }
        });
    }
}