package com.example.fkarimi.mysecondapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SecondActivity extends AppCompatActivity {
    Button but;
    ImageButton leaf;
    EditText et;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        but = (Button) findViewById(R.id.button3);
        but.setText("Clear");
        et = (EditText) findViewById(R.id.editText2);
        tv = (TextView) findViewById(R.id.textView);
        leaf = (ImageButton) findViewById(R.id.leafButt);


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });
        but.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                but.setText("Long Clicked");
                Intent intent = new Intent(SecondActivity.this, thirdActivity.class);
                startActivity(intent);
                return true;
            }
        });
        leaf.setOnClickListener(new View.  OnClickListener() {
            @Override
            public void onClick(View v) {
               // et.setText("Welcome to your garden");
                Intent intent = new Intent(SecondActivity.this, GardenActivity.class);
                startActivity(intent);
                //Some url endpoint that you may have
                String myUrl = "http://thingtalk.ir/update?key=8NZECX1M7Y8WUMLU&field1="+ et.getText().toString();

                //String to place our result in
                //String result;

                //Instantiate new instance of our class
              //  HttpPostRequest getRequest = new HttpPostRequest();

                //Perform the doInBackground method, passing in our url
                try {
                    new HttpPostRequest().execute(myUrl).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        //menu.add("item");
        //menu.add("item2");
        //MenuItem item3 = menu.add("item3");
        //SubMenu sub = menu.addSubMenu("Sub1");
        //sub.add("ss");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() != R.id.action_Subitem1)
        Toast.makeText(this,item.getTitle(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "That's it", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }



}
