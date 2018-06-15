package com.example.dog.gettingstartedfb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


    public void addOnClick(View view)
    {
        startActivity(new Intent(getBaseContext(), AddToDataBase.class));
    }


}
