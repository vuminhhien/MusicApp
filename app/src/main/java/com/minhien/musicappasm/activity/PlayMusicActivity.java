package com.minhien.musicappasm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.minhien.musicappasm.R;

import soup.neumorphism.NeumorphImageView;

public class PlayMusicActivity extends AppCompatActivity {

    NeumorphImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        back = findViewById(R.id.imageMenu);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlayMusicActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}