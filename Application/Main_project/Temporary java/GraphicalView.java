package ir.iotacademy.gardenbalcony;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
<<<<<<< HEAD
import com.android.volley.toolbox.StringRequest;
=======
>>>>>>> master
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import java.util.HashMap;
import java.util.Map;
<<<<<<< HEAD

=======
<<<<<<< Updated upstream
>>>>>>> master
import java.util.logging.Handler;
import java.util.logging.LogRecord;
=======
import java.util.concurrent.ExecutionException;
>>>>>>> Stashed changes

import java.util.concurrent.ExecutionException;

import static ir.iotacademy.gardenbalcony.R.drawable.day;
import static ir.iotacademy.gardenbalcony.R.drawable.drop;
import static ir.iotacademy.gardenbalcony.R.drawable.mist_off;

public class GraphicalView extends AppCompatActivity {
    Button test;
    ImageView onlamp,offlamp,mist,thirsty0,thirsty1,thirsty2,thirsty3,thirsty8,thirsty9,thirsty10,thirsty11,thirsty12,wf1,wf2,wf3,wf4,wf5,wv1,wv2,wv3,wv4,puddle,motion;
    ImageButton onswitch,offswitch,mistbtn,mistbtn2,go_to_the_right_position,plant;
    ConstraintLayout background;
    TextView datat, datah, dataveg1, dataveg2, dataveg3, dataveg4, dataf1, dataf2, dataf3, dataf4, dataf5, datapr,
    datafm0, datawm, datag;
    String d,d1;
    int water_level = 0;
<<<<<<< HEAD
    int up_pump_status =0, lamp_status=0;
=======
    int up_pump_status = 0;
>>>>>>> master
    String preUrl="http://thingtalk.ir/channels/";
    String preip="http://10.1.248.34:5050/actuators/";
    Map<String,String> map=new HashMap<String, String>();

    // private GyroscopeObserver gyroscopeObserver;
    //View decorView = getWindow().getDecorView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graphical_view);
      /*  go_to_the_right_position = (ImageButton) findViewById(R.id.go_right);


        go_to_the_right_position.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GraphicalView.this, Middle_View.class);
                startActivity(intent);

            }
        });
        */
<<<<<<< Updated upstream

       final  RequestQueue queue = Volley.newRequestQueue(getApplicationContext());//final  RequestQueue queueveg = Volley.newRequestQueue(getApplicationContext());

=======
       final  RequestQueue queue = Volley.newRequestQueue(getApplicationContext());//final  RequestQueue queueveg = Volley.newRequestQueue(getApplicationContext());
>>>>>>> Stashed changes
        //weather
        map.put("T0", preUrl + "629/feed.json?key=G7KHR97UPN9OC5AC&results=1");
        map.put("H0", preUrl + "669/feed.json?key=7TPW8OQOGN1EMURD&results=1");
        //vegetables
        map.put("SM0", preUrl + "670/feed.json?key=LUJ9D21E177HESAW&results=1");
        map.put("SM1", preUrl + "700/feed.json?key=Q2JH4OBED4QQAA74&results=1");
        map.put("SM2", preUrl + "701/feed.json?key=P6LY6LV7CPYYSJUP&results=1");
        map.put("SM3", preUrl + "702/feed.json?key=PVWFQI4XLXLWL9DL&results=1");
        //flowers
        map.put("SM8", preUrl + "710/feed.json?key=FYMFZRW8E2YLIKUK&results=1");
        map.put("SM9", preUrl + "711/feed.json?key=OXXNS5C338I0TCIR&results=1");
        map.put("SM10", preUrl + "712/feed.json?key=MZEZGGZL09ZB48L1&results=1");
        map.put("SM11", preUrl + "713/feed.json?key=7R9CHM056LSY23ZO&results=1");
        map.put("SM12", preUrl + "714/feed.json?key=ZPX8TF59L251UFVE&results=1");
        //photo resistor
        map.put("PR0", preUrl + "672/feed.json?key=B1JQYWFKX2PCRBYF&results=1");
        //floor humidity
        map.put("FH0", preUrl + "722/feed.json?key=3ZIOUDCBO1X4W0B7&results=1");
        //pump status
        map.put("PSV", preUrl + "671/feed.json?key=XAKAVEUUJQ9GZGMT&results=1");
        map.put("PSF", preUrl + "744/feed.json?key=PD74MGJ9RFR4YMHK&results=1");
        map.put("PST", preUrl + "750/feed.json?key=FGGX36CLKXBW2BN4&results=1");
        //light bulb status
        map.put("LBS", preUrl + "745/feed.json?key=2ZRJZIFPTQF79NOH&results=1");
        //gas sensor
        map.put("G", preUrl + "747/feed.json?key=65ZJD9TRET64FJ03&results=1");
        //tap status
        map.put("TS", preUrl + "748/feed.json?key=A1079N4WNZESIRFC&results=1");
        //motion detector
        map.put("MD", preUrl + "749/feed.json?key=V197BB4SL21A2IKG&results=1");
        //watt meter
        map.put("WM", preUrl + "753/feed.json?key=OUAV3VIB076Y5UO0&results=1");
        //water level
        map.put("WL", preUrl + "742/feed.json?key=WGWJ660WN7V9394D&results=1");
//        Thread t = new Thread() {
//
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(12000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
<<<<<<< HEAD
//<<<<<<< Updated upstream
//
//
//
//
//       //Temperature
//        data = (TextView) findViewById(R.id.temperature);
//        GetSendData t0 = new GetSendData();
//        data.setText(t0.GetData(map.get("T0")) + "°C");
//
//
//        //Humidity
//        data = (TextView) findViewById(R.id.humidity);
//        GetSendData h0 = new GetSendData();
//        data.setText(h0.GetData(map.get("H0")) + "%");
//=======
////
//>>>>>>> Stashed changes
//
//
        datat = (TextView) findViewById(R.id.temperature);
        datah = (TextView) findViewById(R.id.humidity);
        dataveg1 = (TextView) findViewById(R.id.textveg1);
        dataveg2 = (TextView) findViewById(R.id.textveg2);
        dataveg3 = (TextView) findViewById(R.id.textveg3);
        dataveg4 = (TextView) findViewById(R.id.textveg4);
        dataf1 = (TextView) findViewById(R.id.textflower1);
        dataf2 = (TextView) findViewById(R.id.textflower2);
        dataf3 = (TextView) findViewById(R.id.textflower3);
        dataf4 = (TextView) findViewById(R.id.textflower4);
        dataf5 = (TextView) findViewById(R.id.textflower5);
        datapr = (TextView) findViewById(R.id.textbrightness);
        wf1 = (ImageView) findViewById(R.id.waterf1);
        wf2 = (ImageView) findViewById(R.id.waterf2);
        wf3 = (ImageView) findViewById(R.id.waterf3);
        wf4 = (ImageView) findViewById(R.id.waterf4);
        wf5 = (ImageView) findViewById(R.id.waterf5);
        wv1 = (ImageView) findViewById(R.id.waterv1);
        wv2 = (ImageView) findViewById(R.id.waterv2);
        wv3 = (ImageView) findViewById(R.id.waterv3);
        wv4 = (ImageView) findViewById(R.id.waterv4);
        datafm0 = (TextView) findViewById(R.id.textfloormoisture);
        puddle= (ImageView) findViewById(R.id.puddlewater);
        mistbtn2 = (ImageButton) findViewById(R.id.mist_btn2);
        mistbtn = (ImageButton) findViewById(R.id.mist_btn);
        datawm = (TextView) findViewById(R.id.textwattmeter1);
        datag = (TextView) findViewById(R.id.textsmoke);
        onlamp = (ImageView) findViewById(R.id.onlamp);
        offlamp = (ImageView) findViewById(R.id.offlamp);
        onswitch = (ImageButton) findViewById(R.id.on_switch);
        offswitch = (ImageButton) findViewById(R.id.off_switch);
        motion= (ImageView) findViewById(R.id.onmotion);
=======
<<<<<<< Updated upstream
>>>>>>> master




<<<<<<< HEAD

        //Temperature

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, map.get("T0"), null,
                 new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                         try {
                             JSONArray array = response.getJSONArray("feeds");
                             JSONObject data = array.getJSONObject(0);
                             String serial = data.getString("field1");
                             if(serial.indexOf('.') != -1 )
                             {
                                 serial = serial.substring(0, serial.indexOf('.'));
                             }
                             int num = Integer.parseInt(serial)+1;
                             datat.setText(serial + "°C");
                         }
                         catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }
                ,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }

=======
       //Temperature
        data = (TextView) findViewById(R.id.temperature);
        GetSendData t0 = new GetSendData();
        data.setText(t0.GetData(map.get("T0")) + "°C");


        //Humidity
        data = (TextView) findViewById(R.id.humidity);
        GetSendData h0 = new GetSendData();
        data.setText(h0.GetData(map.get("H0")) + "%");
=======
//
>>>>>>> Stashed changes

//
        datat = (TextView) findViewById(R.id.temperature);
        datah = (TextView) findViewById(R.id.humidity);
        dataveg1 = (TextView) findViewById(R.id.textveg1);
        dataveg2 = (TextView) findViewById(R.id.textveg2);
        dataveg3 = (TextView) findViewById(R.id.textveg3);
        dataveg4 = (TextView) findViewById(R.id.textveg4);
        dataf1 = (TextView) findViewById(R.id.textflower1);
        dataf2 = (TextView) findViewById(R.id.textflower2);
        dataf3 = (TextView) findViewById(R.id.textflower3);
        dataf4 = (TextView) findViewById(R.id.textflower4);
        dataf5 = (TextView) findViewById(R.id.textflower5);
        datapr = (TextView) findViewById(R.id.textbrightness);
        wf1 = (ImageView) findViewById(R.id.waterf1);
        wf2 = (ImageView) findViewById(R.id.waterf2);
        wf3 = (ImageView) findViewById(R.id.waterf3);
        wf4 = (ImageView) findViewById(R.id.waterf4);
        wf5 = (ImageView) findViewById(R.id.waterf5);
        wv1 = (ImageView) findViewById(R.id.waterv1);
        wv2 = (ImageView) findViewById(R.id.waterv2);
        wv3 = (ImageView) findViewById(R.id.waterv3);
        wv4 = (ImageView) findViewById(R.id.waterv4);
        datafm0 = (TextView) findViewById(R.id.textfloormoisture);
        puddle= (ImageView) findViewById(R.id.puddlewater);
        mistbtn2 = (ImageButton) findViewById(R.id.mist_btn2);
        mistbtn = (ImageButton) findViewById(R.id.mist_btn);
        datawm = (TextView) findViewById(R.id.textwattmeter1);
        datag = (TextView) findViewById(R.id.textsmoke);




        //Temperature

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, map.get("T0"), null,
                 new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                         try {
                             JSONArray array = response.getJSONArray("feeds");
                             JSONObject data = array.getJSONObject(0);
                             String serial = data.getString("field1");
                             if(serial.indexOf('.') != -1 )
                             {
                                 serial = serial.substring(0, serial.indexOf('.'));
                             }
                             int num = Integer.parseInt(serial)+1;
                             datat.setText(serial + "°C");
                         }
                         catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }
                ,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }

>>>>>>> master
                }
        );
                                queue.add(getRequest);
// humedity
        JsonObjectRequest getRequest2 = new JsonObjectRequest(Request.Method.GET, map.get("H0"), null,
            new Response.Listener<JSONObject>() {
               @Override
              public void onResponse(JSONObject response) {
                   try {
                       JSONArray array = response.getJSONArray("feeds");
                       JSONObject data = array.getJSONObject(0);
                       String serial = data.getString("field1");
                       if(serial.indexOf('.') != -1 )
                       {
                           serial = serial.substring(0, serial.indexOf('.'));
                       }
                       int num = Integer.parseInt(serial)+1;
                       datah.setText(serial + "%");
                   }
                   catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
            }
                ,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequest2);


    JsonObjectRequest getRequestveg1 = new JsonObjectRequest(Request.Method.GET, map.get("SM0"), null,
            new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                     try {
                         JSONArray array = response.getJSONArray("feeds");
                         JSONObject data = array.getJSONObject(0);
                         String serial = data.getString("field1");
                         if(serial.indexOf('.') != -1 )
                         {
                             serial = serial.substring(0, serial.indexOf('.'));
                         }
                         int num = Integer.parseInt(serial)+1;
                         dataveg1.setText(serial + "%");
                         plant = (ImageButton) findViewById(R.id.veg1);
                         plant.setVisibility(View.VISIBLE);
                         thirsty0 = (ImageView) findViewById(R.id.thirstyveg1);
                         thirsty0.post(new Runnable() {
                             @Override
                             public void run() {
                                 ((AnimationDrawable) thirsty0.getBackground()).stop();
                             }
                         });

                         if (num < 50) {

                             thirsty0.setVisibility(View.VISIBLE);
                             thirsty0.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     ((AnimationDrawable) thirsty0.getBackground()).start();
                                 }
                             });
                             plant = (ImageButton) findViewById(R.id.veg1);
                             plant.setVisibility(View.INVISIBLE);
                         }

                     }
                     catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
            }
            ,
            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                }
            }
    );queue.add(getRequestveg1);


    JsonObjectRequest getRequestveg2 = new JsonObjectRequest(Request.Method.GET, map.get("SM1"), null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray array = response.getJSONArray("feeds");
                        JSONObject data = array.getJSONObject(0);
                        String serial = data.getString("field1");
                        if(serial.indexOf('.') != -1 )
                        {
                            serial = serial.substring(0, serial.indexOf('.'));
                        }
                        int num = Integer.parseInt(serial)+1;
                        dataveg2.setText(serial + "%");
                        plant = (ImageButton) findViewById(R.id.veg2);
                        plant.setVisibility(View.VISIBLE);
                        thirsty1 = (ImageView) findViewById(R.id.thirstyveg2);
                        thirsty1.post(new Runnable() {
                            @Override
                            public void run() {
                                ((AnimationDrawable) thirsty1.getBackground()).stop();
                            }
                        });
                        if (num < 50) {
                            thirsty1.setVisibility(View.VISIBLE);
                            thirsty1.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) thirsty1.getBackground()).start();
                                }
                            });
                            plant = (ImageButton) findViewById(R.id.veg2);
                            plant.setVisibility(View.INVISIBLE);
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            ,
            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                }
            }
    );queue.add(getRequestveg2);


    JsonObjectRequest getRequestveg3 = new JsonObjectRequest(Request.Method.GET, map.get("SM2"), null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray array = response.getJSONArray("feeds");
                        JSONObject data = array.getJSONObject(0);
                        String serial = data.getString("field1");
                        if(serial.indexOf('.') != -1 )
                        {
                            serial = serial.substring(0, serial.indexOf('.'));
                        }
                        int num = Integer.parseInt(serial)+1;
                        dataveg3.setText(serial + "%");
                        plant = (ImageButton) findViewById(R.id.veg3);
                        plant.setVisibility(View.VISIBLE);
                        thirsty2 = (ImageView) findViewById(R.id.thirstyveg3);
                        thirsty2.post(new Runnable() {
                            @Override
                            public void run() {
                                ((AnimationDrawable) thirsty2.getBackground()).stop();
                            }
                        });
                        if (num < 50) {
                            thirsty2.setVisibility(View.VISIBLE);
                            thirsty2.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) thirsty2.getBackground()).start();
                                }
                            });
                            plant = (ImageButton) findViewById(R.id.veg3);
                            plant.setVisibility(View.INVISIBLE);
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            ,
            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                }
            }
    );queue.add(getRequestveg3);


    JsonObjectRequest getRequestveg4 = new JsonObjectRequest(Request.Method.GET, map.get("SM3"), null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray array = response.getJSONArray("feeds");
                        JSONObject data = array.getJSONObject(0);
                        String serial = data.getString("field1");
                        if(serial.indexOf('.') != -1 )
                        {
                            serial = serial.substring(0, serial.indexOf('.'));
                        }
                        int num = Integer.parseInt(serial)+1;
                        dataveg4.setText(serial + "%");
                        plant = (ImageButton) findViewById(R.id.veg4);
                        plant.setVisibility(View.VISIBLE);
                        thirsty3 = (ImageView) findViewById(R.id.thirstyveg4);
                        thirsty3.post(new Runnable() {
                            @Override
                            public void run() {
                                ((AnimationDrawable) thirsty3.getBackground()).stop();
                            }
                        });
                        if (num < 50) {
                            thirsty3.setVisibility(View.VISIBLE);
                            thirsty3.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) thirsty3.getBackground()).start();
                                }
                            });
                            plant = (ImageButton) findViewById(R.id.veg4);
                            plant.setVisibility(View.INVISIBLE);
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            ,
            new Response.ErrorListener()
            {
                @Override public void onErrorResponse(VolleyError error) {
                    Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                }
            }
    );queue.add(getRequestveg4);


        // Flowers

        JsonObjectRequest getRequestf1 = new JsonObjectRequest(Request.Method.GET, map.get("SM8"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;
                            dataf1.setText(serial + "%");
                            plant = (ImageButton) findViewById(R.id.flower1);
                            plant.setVisibility(View.VISIBLE);
                            thirsty8 = (ImageView) findViewById(R.id.thirstyflower1);
                            thirsty8.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) thirsty8.getBackground()).stop();
                                }
                            });

                            if (num < 50) {

                                thirsty8.setVisibility(View.VISIBLE);
                                thirsty8.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) thirsty8.getBackground()).start();
                                    }
                                });

                                plant.setVisibility(View.INVISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestf1);
// flower2
        JsonObjectRequest getRequestf2 = new JsonObjectRequest(Request.Method.GET, map.get("SM9"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;
                            dataf2.setText(serial + "%");
                            plant = (ImageButton) findViewById(R.id.flower2);
                            plant.setVisibility(View.VISIBLE);
                            thirsty9 = (ImageView) findViewById(R.id.thirstyflower2);
                            thirsty9.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) thirsty9.getBackground()).stop();
                                }
                            });

                            if (num < 50) {

                                thirsty9.setVisibility(View.VISIBLE);
                                thirsty9.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) thirsty9.getBackground()).start();
                                    }
                                });

                                plant.setVisibility(View.INVISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestf2);
//

        JsonObjectRequest getRequestf3 = new JsonObjectRequest(Request.Method.GET, map.get("SM10"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;
                            dataf3.setText(serial + "%");
                            plant = (ImageButton) findViewById(R.id.flower3);
                            plant.setVisibility(View.VISIBLE);
                            thirsty10 = (ImageView) findViewById(R.id.thirstyflower3);
                            thirsty10.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) thirsty10.getBackground()).stop();
                                }
                            });

                            if (num < 50) {

                                thirsty10.setVisibility(View.VISIBLE);
                                thirsty10.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) thirsty10.getBackground()).start();
                                    }
                                });

                                plant.setVisibility(View.INVISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestf3);
    // flower4
        JsonObjectRequest getRequestf4 = new JsonObjectRequest(Request.Method.GET, map.get("SM11"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;
                            dataf4.setText(serial + "%");
                            plant = (ImageButton) findViewById(R.id.flower4);
                            plant.setVisibility(View.VISIBLE);
                            thirsty11 = (ImageView) findViewById(R.id.thirstyflower4);
                            thirsty11.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) thirsty11.getBackground()).stop();
                                }
                            });

                            if (num < 50) {

                                thirsty11.setVisibility(View.VISIBLE);
                                thirsty11.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) thirsty11.getBackground()).start();
                                    }
                                });

                                plant.setVisibility(View.INVISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestf4);
        //flowers5
        JsonObjectRequest getRequestf5 = new JsonObjectRequest(Request.Method.GET, map.get("SM12"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;
                            dataf5.setText(serial + "%");
                            plant = (ImageButton) findViewById(R.id.flower5);
                            plant.setVisibility(View.VISIBLE);
                            thirsty12 = (ImageView) findViewById(R.id.thirstyflower5);
                            thirsty12.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) thirsty12.getBackground()).stop();
                                }
                            });

                            if (num < 50) {

                                thirsty12.setVisibility(View.VISIBLE);
                                thirsty12.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) thirsty12.getBackground()).start();
                                    }
                                });

                                plant.setVisibility(View.INVISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestf5);

        //photoResistor
        JsonObjectRequest getRequestpr = new JsonObjectRequest(Request.Method.GET, map.get("PR0"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;
                            Calendar c = Calendar.getInstance();
                            int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
                            datapr.setText(serial);
                            background = (ConstraintLayout) findViewById(R.id.backgounrd);

                            if (timeOfDay >= 17 || timeOfDay < 6 && num < 300) {
                                background.setBackgroundResource(R.drawable.night);

                            } else if (timeOfDay < 17 && timeOfDay >= 6 && num > 300) {
                                background.setBackgroundResource(R.drawable.day);

                            } else if (timeOfDay < 17 && timeOfDay >= 6 && num < 300) {
                                background.setBackgroundResource(R.drawable.cloudy_left);

                            } else
                                background.setBackgroundResource(R.drawable.balcony);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestpr);

//        watering

        JsonObjectRequest getRequestwl = new JsonObjectRequest(Request.Method.GET, map.get("WL"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial);
                         water_level = num;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestwl);

        JsonObjectRequest getRequestpsf = new JsonObjectRequest(Request.Method.GET, map.get("PSF"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;

                            if (serial.equals("1") && water_level > 5) {
                                wf1.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf1.getBackground()).start();
                                    }
                                });

                                wf2.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf2.getBackground()).start();
                                    }
                                });

                                wf3.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf3.getBackground()).start();
                                    }
                                });

                                wf4.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf4.getBackground()).start();
                                    }
                                });

                                wf5.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf5.getBackground()).start();
                                    }
                                });
                            } else
                            { wf1.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) wf1.getBackground()).stop();
                                }
                            });

                                wf2.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf2.getBackground()).stop();
                                    }
                                });

                                wf3.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf3.getBackground()).stop();
                                    }
                                });

                                wf4.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf4.getBackground()).stop();
                                    }
                                });

                                wf5.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wf5.getBackground()).stop();
                                    }
                                });

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestpsf);

        JsonObjectRequest getRequestpsv = new JsonObjectRequest(Request.Method.GET, map.get("PSV"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;

                            if (serial.equals("1") &&  water_level > 5) {
                                wv1.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wv1.getBackground()).start();
                                    }
                                });

                                wv2.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wv2.getBackground()).start();
                                    }
                                });

                                wv3.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wv3.getBackground()).start();
                                    }
                                });

                                wv4.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wv4.getBackground()).start();
                                    }
                                });


                            } else
                            { wv1.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((AnimationDrawable) wv1.getBackground()).stop();
                                }
                            });

                                wv2.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wv2.getBackground()).stop();
                                    }
                                });

                                wv3.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wv3.getBackground()).stop();
                                    }
                                });

                                wv4.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) wv4.getBackground()).stop();
                                    }
                                });


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestpsv);


//        //floor humidity



        JsonObjectRequest getRequestfh0 = new JsonObjectRequest(Request.Method.GET, map.get("FH0"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial) + 1;
<<<<<<< HEAD
                            datafm0.setText(serial);
=======

>>>>>>> master
                            if(num == 1){
                                puddle.setVisibility(View.VISIBLE);
                            }
                            else
                                puddle.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestfh0);


        JsonObjectRequest getRequestpst = new JsonObjectRequest(Request.Method.GET, map.get("PST"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial);
                                up_pump_status = num;
                            if(water_level > 5 && up_pump_status == 1){
                                mistbtn.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) mistbtn.getBackground()).start();
                                    }
                                });
                                mistbtn2.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) mistbtn2.getBackground()).start();
                                    }
                                });
                            }
                            if(water_level <= 5 || up_pump_status == 0){
                                mistbtn.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) mistbtn.getBackground()).stop();
                                    }
                                });
                                mistbtn.setVisibility(View.INVISIBLE);
                                mistbtn.setVisibility(View.VISIBLE);
                                mistbtn2.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AnimationDrawable) mistbtn2.getBackground()).stop();
                                    }
                                });
                                mistbtn2.setVisibility(View.INVISIBLE);
                                mistbtn2.setVisibility(View.VISIBLE);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestfh0);

//
//        //top pump status

//        GetSendData pst = new GetSendData();
//        d = pst.GetData(map.get("PST"));
//

//

        mistbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                //GetSendData pst = new GetSendData();
                //d = pst.GetData(map.get("PST"));
<<<<<<< HEAD
                if (!(((AnimationDrawable) mistbtn.getBackground()).isRunning()) && !(((AnimationDrawable) mistbtn2.getBackground()).isRunning())){//&& water_level > 5 && up_pump_status==0) {
=======
                if (!(((AnimationDrawable) mistbtn.getBackground()).isRunning()) && !(((AnimationDrawable) mistbtn2.getBackground()).isRunning())&& water_level > 5 && up_pump_status==0) {
>>>>>>> master

                    //pst.SetActuator(preip+"setPS2ON",d);

                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).start();
                        }
                    });
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).start();
                        }
                    });

                    StringRequest postRequest = new StringRequest(Request.Method.POST, preip+"setPS2ON",
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                  //  Log.d("Response", response);
                                    Toast.makeText(GraphicalView.this, "pump is on", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    //    Log.d("Error.Response", response);
                                    Toast.makeText(GraphicalView.this, "can't connect to pump", Toast.LENGTH_SHORT).show();
                                    mistbtn.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            ((AnimationDrawable) mistbtn.getBackground()).stop();
                                        }
                                    });
                                    mistbtn.setVisibility(View.INVISIBLE);
                                    mistbtn.setVisibility(View.VISIBLE);
                                    mistbtn2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((AnimationDrawable) mistbtn2.getBackground()).stop();
                                        }
                                    });
                                    mistbtn2.setVisibility(View.INVISIBLE);
                                    mistbtn2.setVisibility(View.VISIBLE);
                                }
                            }
                    );
                    queue.add(postRequest);


                } else {


                 //   pst.SetActuator(preip+"setPS2OFF",d);
                    mistbtn.post(new Runnable() {

                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).stop();
                        }
                    });
                    mistbtn.setVisibility(View.INVISIBLE);
                    mistbtn.setVisibility(View.VISIBLE);
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).stop();
                        }
                    });
                    mistbtn2.setVisibility(View.INVISIBLE);
                    mistbtn2.setVisibility(View.VISIBLE);
<<<<<<< HEAD
                    StringRequest postRequest = new StringRequest(Request.Method.POST, preip+"setPS2OFF",
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    //  Log.d("Response", response);
                                    Toast.makeText(GraphicalView.this, "pump is off", Toast.LENGTH_SHORT).show();

                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    //    Log.d("Error.Response", response);
                                    Toast.makeText(GraphicalView.this, "can't connect to pump", Toast.LENGTH_SHORT).show();
                                    mistbtn.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((AnimationDrawable) mistbtn.getBackground()).start();
                                        }
                                    });
                                    mistbtn2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((AnimationDrawable) mistbtn2.getBackground()).start();
                                        }
                                    });

                                }
                            }
                    );
                    queue.add(postRequest);
=======
>>>>>>> master
                }
            }
        });



        mistbtn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
             //   GetSendData pst = new GetSendData();
           //     d = pst.GetData(map.get("PST"));
                if (!(((AnimationDrawable) mistbtn.getBackground()).isRunning()) && !(((AnimationDrawable) mistbtn2.getBackground()).isRunning()) ) {

                    //     pst.SetActuator(preip+"setPS2ON",d)
                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).start();
                        }
                    });
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).start();
                        }
                    });
                    StringRequest postRequestPSon = new StringRequest(Request.Method.POST, preip+"setPS2ON",
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    //  Log.d("Response", response);
                                    Toast.makeText(GraphicalView.this, "pump is on", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    //    Log.d("Error.Response", response);
                                    Toast.makeText(GraphicalView.this, "can't connect to pump", Toast.LENGTH_SHORT).show();
                                    mistbtn.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            ((AnimationDrawable) mistbtn.getBackground()).stop();
                                        }
                                    });
                                    mistbtn.setVisibility(View.INVISIBLE);
                                    mistbtn.setVisibility(View.VISIBLE);
                                    mistbtn2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((AnimationDrawable) mistbtn2.getBackground()).stop();
                                        }
                                    });
                                    mistbtn2.setVisibility(View.INVISIBLE);
                                    mistbtn2.setVisibility(View.VISIBLE);
                                }
                            }
                    );
                    queue.add(postRequestPSon);

                } else {

//                    pst.SetActuator(preip+"setPS2OFF",d);
                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).stop();
                        }
                    });
                    mistbtn.setVisibility(View.INVISIBLE);
                    mistbtn.setVisibility(View.VISIBLE);
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).stop();
                        }
                    });
                    mistbtn2.setVisibility(View.INVISIBLE);
                    mistbtn2.setVisibility(View.VISIBLE);
                    mistbtn.post(new Runnable() {

                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).stop();
                        }
                    });
                    mistbtn.setVisibility(View.INVISIBLE);
                    mistbtn.setVisibility(View.VISIBLE);
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).stop();
                        }
                    });
                    mistbtn2.setVisibility(View.INVISIBLE);
                    mistbtn2.setVisibility(View.VISIBLE);
                    StringRequest postRequestPSoff = new StringRequest(Request.Method.POST, preip+"setPS2OFF",
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    //  Log.d("Response", response);
                                    Toast.makeText(GraphicalView.this, "pump is off", Toast.LENGTH_SHORT).show();

                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    //    Log.d("Error.Response", response);
                                    Toast.makeText(GraphicalView.this, "can't connect to pump", Toast.LENGTH_SHORT).show();
                                    mistbtn.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((AnimationDrawable) mistbtn.getBackground()).start();
                                        }
                                    });
                                    mistbtn2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((AnimationDrawable) mistbtn2.getBackground()).start();
                                        }
                                    });

                                }
                            }
                    );
                    queue.add(postRequestPSoff);


                }
            }
        });
//
//        //lamp status
<<<<<<< HEAD

        JsonObjectRequest getRequestlbs = new JsonObjectRequest(Request.Method.GET, map.get("LBS"), null,
=======
//
//
//
//
//        onlamp = (ImageView) findViewById(R.id.onlamp);
//        offlamp = (ImageView) findViewById(R.id.offlamp);
//        onswitch = (ImageButton) findViewById(R.id.on_switch);
//        offswitch = (ImageButton) findViewById(R.id.off_switch);
//
//        GetSendData lbs = new GetSendData();
//        d = lbs.GetData(map.get("LBS"));
//        if(Integer.parseInt(d)==1){
//
//            onswitch.setVisibility(View.VISIBLE);
//            offswitch.setVisibility(View.INVISIBLE);
//            onlamp.setVisibility(View.VISIBLE);
//            onlamp.post(new Runnable() {
//                @Override
//                public void run() {
//
//                    ((AnimationDrawable) onlamp.getBackground()).start();
//
//                }
//            });
//        }
//        if(Integer.parseInt(d)==0){
//            offswitch.setVisibility(View.VISIBLE);
//            onswitch.setVisibility(View.INVISIBLE);
//            onlamp.post(new Runnable() {
//                @Override
//                public void run() {
//
//                    ((AnimationDrawable) onlamp.getBackground()).stop();
//
//                }
//            });
//            onlamp.setVisibility(View.INVISIBLE);
//        }
//
//        offswitch.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//                GetSendData lbs = new GetSendData();
//                d = lbs.GetData(map.get("LBS"));
//
//                if (onlamp.getVisibility() == View.INVISIBLE && onswitch.getVisibility() == View.INVISIBLE&&Integer.parseInt(d)==0 ) {
//
//                    lbs.SetActuator(preip+"setLBS0ON",d);
//
//                    onswitch.setVisibility(View.VISIBLE);
//                    offswitch.setVisibility(View.INVISIBLE);
//                    onlamp.setVisibility(View.VISIBLE);
//                    onlamp.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            ((AnimationDrawable) onlamp.getBackground()).start();
//
//                        }
//                    });
//
//                }}
//
//        });
//        onswitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                GetSendData lbs = new GetSendData();
//                d = lbs.GetData(map.get("LBS"));
//                lbs.SetActuator(preip+"setLBS0OFF",d);
//
//                offswitch.setVisibility(View.VISIBLE);
//                onswitch.setVisibility(View.INVISIBLE);
//                onlamp.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        ((AnimationDrawable) onlamp.getBackground()).stop();
//
//                    }
//                });
//                onlamp.setVisibility(View.INVISIBLE);
//
//            }
//        });
//
//        //motion detector
//        GetSendData md = new GetSendData();
//        d = md.GetData(map.get("MD"));
//
//        motion= (ImageView) findViewById(R.id.onmotion);
//        if(d.equals("1")){
//
//            motion.post(new Runnable() {
//                @Override
//                public void run() {
//
//                    ((AnimationDrawable) motion.getBackground()).start();
//
//                }
//            });
//            motion.setVisibility(View.VISIBLE);
//
//        }
//        else {
//            motion.post(new Runnable() {
//                @Override
//                public void run() {
//
//                    ((AnimationDrawable) motion.getBackground()).stop();
//
//                }
//            });
//            motion.setVisibility(View.INVISIBLE);
//        }

        //watt meter
        JsonObjectRequest getRequestwm = new JsonObjectRequest(Request.Method.GET, map.get("WM"), null,
>>>>>>> master
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial);
<<<<<<< HEAD
                            lamp_status = num;
                            if(num == 1){
                                onswitch.setVisibility(View.VISIBLE);
                                offswitch.setVisibility(View.INVISIBLE);
                                onlamp.setVisibility(View.VISIBLE);
                                onlamp.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        ((AnimationDrawable) onlamp.getBackground()).start();

                                    }
                                });
                            }
                            if(num == 0){
                                offswitch.setVisibility(View.VISIBLE);
                                onswitch.setVisibility(View.INVISIBLE);
                                onlamp.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        ((AnimationDrawable) onlamp.getBackground()).stop();

                                    }
                                });
                                onlamp.setVisibility(View.INVISIBLE);
                            }
=======
                            datawm.setText(serial);
>>>>>>> master
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
<<<<<<< HEAD
                        //Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestlbs);


        offswitch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (onlamp.getVisibility() == View.INVISIBLE && onswitch.getVisibility() == View.INVISIBLE && lamp_status == 0 ) {

                    onswitch.setVisibility(View.VISIBLE);
                    offswitch.setVisibility(View.INVISIBLE);
                    onlamp.setVisibility(View.VISIBLE);
                    onlamp.post(new Runnable() {
                        @Override
                        public void run() {

                            ((AnimationDrawable) onlamp.getBackground()).start();

                        }
                    });
                    StringRequest postRequestLBSON = new StringRequest(Request.Method.POST, preip +"setLBS0ON",
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                 //   Log.d("Response", response);
                                    Toast.makeText(GraphicalView.this,"Lamp is on", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                Toast.makeText(GraphicalView.this,"problem turning the lamp on", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    queue.add(postRequestLBSON);

                }}

        });
        onswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                offswitch.setVisibility(View.VISIBLE);
                onswitch.setVisibility(View.INVISIBLE);
                onlamp.post(new Runnable() {
=======
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestwm);


        //gas
        JsonObjectRequest getRequestg = new JsonObjectRequest(Request.Method.GET, map.get("G"), null,
                new Response.Listener<JSONObject>() {
>>>>>>> master
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial);
                            datag.setText(serial);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
<<<<<<< HEAD
                });
                onlamp.setVisibility(View.INVISIBLE);
                StringRequest postRequestLBSoff = new StringRequest(Request.Method.POST, preip + "setLBS0OFF",
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Toast.makeText(GraphicalView.this,"Lamp is off", Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(GraphicalView.this,"problem turning the lamp off", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
                queue.add(postRequestLBSoff);
            }
        });

        //motion detector
      //  GetSendData md = new GetSendData();
        //d = md.GetData(map.get("MD"));
        JsonObjectRequest getRequestmd = new JsonObjectRequest(Request.Method.GET, map.get("MD"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial);
                            if(serial.equals("1")){

                                motion.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        ((AnimationDrawable) motion.getBackground()).start();

                                    }
                                });
                                motion.setVisibility(View.VISIBLE);

                            }
                            else {
                                motion.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        ((AnimationDrawable) motion.getBackground()).stop();

                                    }
                                });
                                motion.setVisibility(View.INVISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
=======
>>>>>>> master
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
<<<<<<< HEAD
                    }
                }
        );queue.add(getRequestmd);


        //watt meter
        JsonObjectRequest getRequestwm = new JsonObjectRequest(Request.Method.GET, map.get("WM"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial);
                            datawm.setText(serial);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queue.add(getRequestwm);


        //gas
        JsonObjectRequest getRequestg = new JsonObjectRequest(Request.Method.GET, map.get("G"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("feeds");
                            JSONObject data = array.getJSONObject(0);
                            String serial = data.getString("field1");
                            if (serial.indexOf('.') != -1) {
                                serial = serial.substring(0, serial.indexOf('.'));
                            }
                            int num = Integer.parseInt(serial);
                            datag.setText(serial);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
=======
                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
>>>>>>> master
        );queue.add(getRequestg);


//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//        t.start();




    }
}





