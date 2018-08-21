package ir.iotacademy.gardenbalcony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Home extends AppCompatActivity {

    ImageButton clouds;
    ImageButton drop;
    ImageButton grass;
    ImageButton sun;
    TextView tv;
    String error="No internet access";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        clouds = (ImageButton) findViewById(R.id.imagebutton3);
        grass = (ImageButton) findViewById(R.id.imagebutton1);
        drop = (ImageButton) findViewById(R.id.imagebutton4);
        sun  = (ImageButton) findViewById(R.id.imagebutton2);

        tv= (TextView) findViewById(R.id.textView);



        clouds.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                GetTemprature();

                if (SerialClass.Temperature == null) {
                    tv.setText(error);

                   // Toast.makeText(this, "No internet access",Toast.LENGTH_SHORT).show();
                }
                else {
                    tv.setText(SerialClass.Temperature);
                }
            }
        });



        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetLight();

                if(SerialClass.Light == null){
                    tv.setText(error);
                    //Toast.makeText(this, "No internet access", Toast.LENGTH_SHORT).show();
                }
                else{
                    tv.setText(SerialClass.Light);
                }
            }
        });

    }


    public void GetTemprature() {
        String url = "http://thingtalk.ir/channels/629/feed.json?key=G7KHR97UPN9OC5AC&results=1";
        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
            // Temprature.setText(json);
            if(json != null) {
                String num = getSerial(json);
                SerialClass.Temperature = num;
            }
            else{
                SerialClass.Temperature = null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, SerialClass.Temperature, Toast.LENGTH_SHORT).show();
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
            }
            else {
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

            return serial;



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
