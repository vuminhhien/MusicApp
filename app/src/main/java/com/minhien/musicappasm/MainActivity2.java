package com.minhien.musicappasm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.minhien.musicappasm.activity.LoginActivity;
import com.minhien.musicappasm.activity.SocketActivity;
import com.minhien.musicappasm.activity.SplashActivity;

public class MainActivity2 extends AppCompatActivity {
    Button btnASM, btnSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnASM = findViewById(R.id.ASM);
        btnSocket = findViewById(R.id.Socket);

        btnASM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnSocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, SocketActivity.class);
                startActivity(i);
            }
        });
    }
}