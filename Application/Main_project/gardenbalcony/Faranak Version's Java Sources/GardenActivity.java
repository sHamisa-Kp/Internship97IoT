package com.example.fkarimi.mysecondapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class GardenActivity extends AppCompatActivity {
    ImageButton Flower;
    EditText Welocome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
        Flower = (ImageButton) findViewById(R.id.FlowerImage);
        Welocome = (EditText) findViewById(R.id.WelcomeText);
//        if( Integer.parseInt(SerialClass.Temprature) < 26)
//        {
//            Welocome.setText("Cold");
//        }
        Flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GardenActivity.this, FlowerpotActivity.class);
                startActivity(intent);
            }
        });
    }
    public String getSerial(String json)
    {
        try {

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("feeds");
            JSONObject data = array.getJSONObject(0);
            String serial = data.getString("field1");
            int num = Integer.parseInt(serial)+1;
            String url = "http://thingtalk.ir/update?key=HER9J6H518ONQRVW&field1="+String.valueOf(num);
            new HttpPostRequest().execute(url).get();
            return serial;



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
