package com.example.reto5grupo35.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto5grupo35.Controlador.MainActivity;
import com.example.reto5grupo35.R;



public class SplashScreen extends AppCompatActivity {

    private Thread hilo1;
    private ProgressBar progressBar;
    private ImageView chaqueta1;
    private int number = 0;
    private Handler handler = new Handler();
    private TextView porcentaje;
    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        chaqueta1 =(ImageView)findViewById(R.id.chaqueta1);
        chaqueta1.setImageResource(R.drawable.estampado);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        porcentaje=(TextView)findViewById(R.id.porcentaje) ;

        hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (number <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            porcentaje.setText(number + " %");
                            progressBar.setProgress(number);
                        }
                    });
                    try {
                        Thread.sleep(50);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (number == 100) {
                        pasarPantalla = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(pasarPantalla);
                    }
                    number++;
                }
            }
        });
        hilo1.start();
    }
}