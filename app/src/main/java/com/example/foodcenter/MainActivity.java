package com.example.foodcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(){

            @Override
            public void run() {
                try
                {

                    sleep(3000);
                    Intent intent = new  Intent(MainActivity.this ,login.class);
                    startActivity(intent);
                    finish();

                }
                catch (InterruptedException e )
                {
                    e.printStackTrace();

                }
            }
        };
        thread.start();



    }


}
