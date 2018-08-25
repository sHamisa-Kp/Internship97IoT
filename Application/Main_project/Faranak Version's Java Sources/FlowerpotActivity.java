package com.example.fkarimi.mysecondapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class FlowerpotActivity extends AppCompatActivity {
    TextView Temprature;
    TextView SunLight;
    TextView Moisture;
    ImageButton TempraturePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowerpot);
        Temprature = (TextView) findViewById(R.id.editTextTemp);
        SunLight = (TextView) findViewById(R.id.editTextSunlight);
        Moisture = (TextView) findViewById(R.id.editTextMoisture);
        TempraturePic = (ImageButton) findViewById(R.id.imageButton5);

        /* show temperature */
                GetTemprature();

                if (SerialClass.Temprature == null) {
                    Toast.makeText(this, "No internet access",Toast.LENGTH_SHORT).show();
                }
                else {
                    Temprature.setText(SerialClass.Temprature);
                }

        /* show the Soil Moisture */
        GetMoisture();

        if (SerialClass.Moisture == null) {
            Toast.makeText(this, "No internet access",Toast.LENGTH_SHORT).show();
        }
        else {
                Moisture.setText(SerialClass.Moisture);
        }

        /* show the amount of humedity */
        GetHumedity();

        if (SerialClass.Humedity == null) {
            Toast.makeText(this, "No internet access",Toast.LENGTH_SHORT).show();
        }
        else {
            SunLight.setText(SerialClass.Humedity);
        }
        /* Print the light condition*/

        GetLight();

        if(SerialClass.Light == null){
            Toast.makeText(this, "No internet access", Toast.LENGTH_SHORT).show();
        }
        else{
            //
        }
    }


    public void GetHumedity() {

        String url = "http://thingtalk.ir/channels/669/feed.json?key=7TPW8OQOGN1EMURD&results=1";
        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
            // Temprature.setText(json);
            if (json != null) {
                String num = getSerial(json);
                SerialClass.Humedity = num;
            } else {
                SerialClass.Humedity = null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public void GetMoisture(){


        String url = "http://thingtalk.ir/channels/670/feed.json?key=LUJ9D21E177HESAW&results=1";
        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
            // Temprature.setText(json);
            if(json != null) {
                String num = getSerial(json);
                SerialClass.Moisture = num;
            }
            else{
                SerialClass.Moisture = null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        /*key=G7KHR97UPN9OC5AC

http://thingtalk.ir/channels/670/feed.json?key=LUJ9D21E177HESAW
http://thingtalk.ir/channels/672/feed.json?key=B1JQYWFKX2PCRBYF*/
    }

    public void GetTemprature() {
        String url = "http://thingtalk.ir/channels/629/feed.json?key=G7KHR97UPN9OC5AC&results=1";
        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
           // Temprature.setText(json);
            if(json != null) {
                String num = getSerial(json);
                SerialClass.Temprature = num;
            }
            else{
                SerialClass.Temprature = null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void GetLight() {
        String url = "http://thingtalk.ir/channels/671/feed.json?key=XAKAVEUUJQ9GZGMT&results=1";
        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
            // Temprature.setText(json);
            if (json != null) {
                String num = getSerial(json);
                SerialClass.Light = num;
            } else {
                SerialClass.Light = null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getSerial(String json)
    {
        try {

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("feeds");
            JSONObject data = array.getJSONObject(0);
            String serial = data.getString("field1");
            if(serial.indexOf('.') != -1 )
            {
                serial = serial.substring(0, serial.indexOf('.'));
            }
            int num = Integer.parseInt(serial)+1;

            //String url = "http://thingtalk.ir/update?key=8NZECX1M7Y8WUMLU&field1="+String.valueOf(num);
          //  new HttpPostRequest().execute(url).get();
            return serial;



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



}



