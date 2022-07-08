package com.hgarcia.controlgastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        /*
        // status bar is hidden, so hide that too if necessary.
        //Asi tambien se puede ocultar la barra de titulo
        ActionBar actionBar = getActionBar();
        actionBar.hide();
         */

        Handler handler =new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mostrarMainActivity();
            }
        };
        handler.postDelayed(runnable,4000);
    }

    private void mostrarMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
        finish();//Cierra el activity
    }

}
