package com.ejemplo.pruebaparaunity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button start;
    private EditText etUname, etPass;
    private PreferenceHelper preferenceHelper;
    private Jugador player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceHelper = new PreferenceHelper(this);
        //DUMMY PLAYER
        player = new Jugador();
        player.setTime(65);
        player.setLevel(0);
        player.setGifts(5);
        player.setRenos(5);

        Log.i("playerTimeMain", String.valueOf(player.getTime()));

        if (preferenceHelper.getIsLogin()) {
            Intent intent = new Intent(LoginActivity.this, GameActivity.class);
            intent.putExtra("username", player.getUsername());
            intent.putExtra("time", player.getTime());
            intent.putExtra("level", player.getLevel());
            intent.putExtra("gifts", player.getGifts());
            intent.putExtra("reindeers", player.getRenos());
            intent.putExtra("password", player.getPassword());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        etUname = (EditText) findViewById(R.id.username_text);
        etPass = (EditText) findViewById(R.id.password_text);

        Button unityButton = (Button) findViewById(R.id.button);
        unityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loginUser();
                Intent intent = new Intent(LoginActivity.this, GameActivity.class);
                intent.putExtra("username", player.getUsername());
                intent.putExtra("time", player.getTime());
                intent.putExtra("level", player.getLevel());
                intent.putExtra("gifts", player.getGifts());
                intent.putExtra("reindeers", player.getRenos());
                intent.putExtra("password", player.getPassword());
                startActivity(intent);
            }
        });
    }

    private void loginUser() {

        final String username = etUname.getText().toString().trim();
        final String password = etPass.getText().toString().trim();


        RestAPI api = RestAPI.retrofit.create(RestAPI.class);

        final Call<Jugador> call = api.userLogin(username, password);

        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        Jugador jsonresponse = response.body();

                        saveInfo(jsonresponse);

                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, GameActivity.class);
                        intent.putExtra("username", jsonresponse.getUsername());
                        ;
                        intent.putExtra("time", jsonresponse.getTime());
                        intent.putExtra("level", jsonresponse.getLevel());
                        intent.putExtra("gifts", jsonresponse.getGifts());
                        intent.putExtra("reindeers", jsonresponse.getRenos());
                        intent.putExtra("password", jsonresponse.getPassword());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                final Toast toast = Toast.makeText(getApplicationContext(), "There was a failrue with the server.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    private void saveInfo(Jugador user){

        preferenceHelper.putIsLogin(true);
        try {
            JSONObject jsonObject = new JSONObject(user.toString());
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {

                    //No estoy seguro de que esto este bien. Lo pongo todos como string
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    preferenceHelper.putUsername(dataobj.getString("username"));
                    preferenceHelper.putPass(dataobj.getString("pass"));
                    preferenceHelper.putTime(dataobj.getString("time"));
                    preferenceHelper.putLevel(dataobj.getString("level"));
                    preferenceHelper.putGifts(dataobj.getString("gifts"));
                    preferenceHelper.putReindeers(dataobj.getString("reindeers"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
