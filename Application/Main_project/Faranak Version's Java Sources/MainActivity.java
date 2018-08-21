package com.example.fkarimi.mysecondapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button Bot, Bot2;
    @Override
    protected void onResume(){
        Toast.makeText(this, "On Resume", Toast.LENGTH_SHORT).show();
        tv.setText(new Date().toString());
        super.onResume();
    }


    @Override
    protected void onStop(){
        Toast.makeText(this, " On Stop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onRestart(){
        Toast.makeText(this, "On Restart", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "On Create", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView4);
        tv.setText(new Date().toString());
        tv.setTextColor(Color.LTGRAY);
        Bot = (Button) findViewById(R.id.button14);
        Bot2 = (Button) findViewById(R.id.button2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hi there", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Bot2.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Bot2.setText("Clicked");
            }
        });

        Bot2.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                Bot2.setText("Long Clicked");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }
    public void buttonClicked(View v1){
        if(v1.getId() == R.id.button14) {
            Random rand = new Random();
            tv.setTextColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
