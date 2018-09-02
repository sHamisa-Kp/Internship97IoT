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
    ImageButton sprinkler;

    TextView tv;

    String error="No internet access";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toast.makeText(this,"welcome!",Toast.LENGTH_SHORT).show();

        clouds = (ImageButton) findViewById(R.id.imagebutton3);
        grass = (ImageButton) findViewById(R.id.imagebutton1);
        drop = (ImageButton) findViewById(R.id.imagebutton4);
        sun  = (ImageButton) findViewById(R.id.imagebutton2);
        sprinkler= (ImageButton) findViewById(R.id.imageButton);

        tv= (TextView) findViewById(R.id.textView);



        sprinkler.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GetPompStatus();
                SetPompStatus(SerialClass.Pomp);
                return false;
            }
        });



        sprinkler.setOnClickListener(new View.  OnClickListener() {
            @Override
            public void onClick(View v) {

                GetPompStatus();

                if (SerialClass.Pomp == null) {
                    tv.setText(error);
                } else
                    if(SerialClass.Pomp.equals("0"))
                        tv.setText("off");
                    else if(SerialClass.Pomp.equals("1"))
                        tv.setText("on");
                    else
                        tv.setText("unkonwn value!");
            }

        });



        drop.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                GetHumedity();

                if (SerialClass.Humedity == null) {
                    tv.setText(error);

                    // Toast.makeText(this, "No internet access",Toast.LENGTH_SHORT).show();
                } else
                    tv.setText(SerialClass.Humedity);
            }

        });



        grass.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                GetMoisture();

                if (SerialClass.Moisture == null) {
                    tv.setText(error);

                    // Toast.makeText(this, "No internet access",Toast.LENGTH_SHORT).show();
                } else
                    tv.setText(SerialClass.Moisture);
            }

        });



        clouds.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                GetTemprature();

                if (SerialClass.Temperature == null) {
                    tv.setText(error);

                    // Toast.makeText(this, "No internet access",Toast.LENGTH_SHORT).show();
                } else
                    tv.setText(SerialClass.Temperature+" °C");
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

    public void SetPompStatus(String status){

        if(status.equals("1")) {

            String myUrl = "http://thingtalk.ir/update?key=XAKAVEUUJQ9GZGMT&field1=0";

            //String to place our result in
            // String result;

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
        else if (status.equals("0")){
            String myUrl = "http://thingtalk.ir/update?key=XAKAVEUUJQ9GZGMT&field1=1";

            //String to place our result in
            // String result;

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
    }

    public void GetPompStatus() {

        String url = "http://thingtalk.ir/channels/671/feed.json?key=XAKAVEUUJQ9GZGMT&results=1";
        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
            // Temprature.setText(json);
            if (json != null) {
                String num = getSerial(json);
                SerialClass.Pomp = num;
            } else {
                SerialClass.Pomp= null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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

    public void GetMoisture() {


        String url = "http://thingtalk.ir/channels/670/feed.json?key=LUJ9D21E177HESAW&results=1";
        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
            // Temprature.setText(json);
            if (json != null) {
                String num = getSerial(json);
                SerialClass.Moisture = num;
            } else {
                SerialClass.Moisture = null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void GetTemprature() {
       String url = "http://thingtalk.ir/channels/629/feed.json?key=G7KHR97UPN9OC5AC&results=1";
        HttpGetRequest get = new HttpGetRequest();

        try {
            String json = get.execute(url).get();

            // Temprature.setText(json);
            if(json != null) {

                SerialClass.Temperature = getSerial(json);
            }
            else{
                SerialClass.Temperature = null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void GetLight() {
        String url = "http://thingtalk.ir/channels/672/feed.json?key=B1JQYWFKX2PCRBYF&results=1";
        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
            // Temprature.setText(json);
            if (json != null) {

                SerialClass.Light = getSerial(json);
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



        }
        catch (JSONException e) {
            e.printStackTrace();

        }
        return null;

    }

}
