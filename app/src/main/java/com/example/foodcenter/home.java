package com.example.foodcenter;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gamebutton();

    }


void gamebutton(){
    findViewById(R.id.lsbutton).setOnClickListener(new View.OnClickListener(){
@Override
        public void onClick(View v){
    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.vrlearning.VRforKIDS");
    if(LaunchIntent != null){
        startActivity(LaunchIntent);
    }
}

                                                   });
}



}

