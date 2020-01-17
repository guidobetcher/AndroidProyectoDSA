package com.ejemplo.pruebaparaunity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.proyecto.juegoprueba.UnityPlayerActivity;

public class GameActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        Log.d("Unity", "Unity started");
        setContentView(R.layout.activity_game);

        Button startButton = (Button) findViewById(R.id.start_game);


        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, UnityPlayerActivity.class);
                startActivity(intent);
                //loginUser();
            }
        });
    }
}
