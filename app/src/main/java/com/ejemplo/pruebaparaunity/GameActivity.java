package com.ejemplo.pruebaparaunity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.proyecto.juegoprueba.UnityPlayerActivity;

public class GameActivity extends UnityPlayerActivity {

    private PreferenceHelper preferenceHelper;
    private Jugador player;
    private EditText giftUsed;
    private EditText reindeerUsed;
    public GameAttributes gameAttributes;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        Log.d("Unity", "Unity started");
        setContentView(R.layout.activity_game);

        preferenceHelper = new PreferenceHelper(this);
        player = new Jugador();
        gameAttributes = new GameAttributes();

        Bundle datos = this.getIntent().getExtras();
        player.setLevel(datos.getInt("level"));
        player.setGifts(datos.getInt("gifts"));
        player.setRenos(datos.getInt("reindeers"));
        player.setTime(datos.getFloat("time"));

        gameAttributes.setTime(player.getTime());
        Log.i("gameAttributesTime", String.valueOf(gameAttributes.getTime()));
        gameAttributes.setGifts(player.getGifts());
        Log.i("gameAttributesGifts", String.valueOf(gameAttributes.getGifts()));
        gameAttributes.setLevel(player.getLevel());
        Log.i("gameAttributesLevel", String.valueOf(gameAttributes.getLevel()));

        Button startButton = (Button) findViewById(R.id.start_game);
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        giftUsed = (EditText) findViewById(R.id.gifts_used);
        reindeerUsed = (EditText) findViewById(R.id.reindeer_used);


        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String numReindeers = reindeerUsed.getText().toString();
                String numGifts = giftUsed.getText().toString();
                player.setRenos(player.getRenos() - Integer.parseInt(numReindeers));
                Log.i("playerReindeers", String.valueOf(player.getRenos()));
                player.setTime(Float.parseFloat(numReindeers)*15 + player.getTime());
                Log.i("playerTime", String.valueOf(player.getTime()));
                player.setGifts(player.getGifts() - Integer.parseInt(numGifts));
                Log.i("playerGifts", String.valueOf(player.getGifts()));

                gameAttributes.setTime(player.getTime() + Float.parseFloat(numReindeers)*15);
                Log.i("gameAttributesTime", String.valueOf(gameAttributes.getTime()));
                gameAttributes.setGifts(Integer.parseInt(numGifts));
                Log.i("gameAttributesGifts", String.valueOf(gameAttributes.getGifts()));
                gameAttributes.setLevel(player.getLevel());
                Log.i("gameAttributesLevel", String.valueOf(gameAttributes.getLevel()));

                Intent intent = new Intent(GameActivity.this, UnityPlayerActivity.class);
                intent.putExtra("time", gameAttributes.getTime());
                intent.putExtra("level", gameAttributes.getLevel());
                intent.putExtra("gifts", gameAttributes.getGifts());
                startActivity(intent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preferenceHelper.putIsLogin(false);
                Intent intent = new Intent(GameActivity.this, LoginActivity.class);
                startActivity(intent);
                GameActivity.this.finish();
            }
        });
    }

    public GameAttributes myStats(){
        Log.i("gameAttributesTime", String.valueOf(gameAttributes.getTime()));
        Log.i("gameAttributesGifts", String.valueOf(gameAttributes.getGifts()));
        Log.i("gameAttributesLevel", String.valueOf(gameAttributes.getLevel()));

        return gameAttributes;
    }
}
