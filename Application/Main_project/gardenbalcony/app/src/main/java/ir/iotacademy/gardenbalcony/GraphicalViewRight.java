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
import android.text.style.LeadingMarginSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import java.util.HashMap;
import java.util.Map;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import java.util.concurrent.ExecutionException;

import static ir.iotacademy.gardenbalcony.R.drawable.day;
import static ir.iotacademy.gardenbalcony.R.drawable.drop;
import static ir.iotacademy.gardenbalcony.R.drawable.mist_off;

public class GraphicalViewRight extends AppCompatActivity {
    Button test;
    ImageView onlamp,offlamp,mist,thirsty0,thirsty1,thirsty2,thirsty3,thirsty8,thirsty9,thirsty10,thirsty11,thirsty12,wf1,wf2,wf3,wf4,wf5,wv1,wv2,wv3,wv4,puddle,motion;
    ImageButton onswitch,offswitch,mistbtn,mistbtn2,go_to_the_right_position,plant, faucet,flower_faucet;
    ConstraintLayout background;
    TextView datat, datah, dataveg1, dataveg2, dataveg3, dataveg4, dataf1, dataf2, dataf3, dataf4, dataf5, datapr,
            datafm0, datawm, datag;
    String d,d1;
    int water_level = 0;
    int up_pump_status =0, lamp_status=0;
    int psf, psv;
    String preUrl="http://thingtalk.ir/channels/";
    String preip="http://10.1.248.34:5050/actuators/";
    Map<String,String> map=new HashMap<String, String>();

    // private GyroscopeObserver gyroscopeObserver;
    //View decorView = getWindow().getDecorView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graphical_view_right);
        go_to_the_right_position = (ImageButton) findViewById(R.id.go_middle_from_right);


        go_to_the_right_position.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GraphicalViewRight.this, GraphicalView.class);
                startActivity(intent);

            }
        });


        final  RequestQueue queueR = Volley.newRequestQueue(getApplicationContext());//final  RequestQueue queueveg = Volley.newRequestQueue(getApplicationContext());

        //weather
        map.put("T0", preUrl + "720/feed.json?key=6P4WUZHZZDR6U0TX&results=1");
        map.put("H0", preUrl + "721/feed.json?key=YV8JRH910ZJZQN1I&results=1");
        //vegetables
        map.put("SM0", preUrl + "703/feed.json?key=6W8DYBXG0HDA141O&results=1");
        map.put("SM1", preUrl + "704/feed.json?key=HZ9VM1PH1Q6LQSLZ&results=1");
        map.put("SM2", preUrl + "705/feed.json?key=RUB06UUHPX0K4DDS&results=1");
        map.put("SM3", preUrl + "706/feed.json?key=WQFB2JIGRVXDIAR4&results=1");
        //flowers
        map.put("SM8", preUrl + "715/feed.json?key=3DMD11SS9G5B94I0&results=1");
        map.put("SM9", preUrl + "716/feed.json?key=QLH9NDZ20RQNK96S&results=1");
        map.put("SM10", preUrl + "717/feed.json?key=BVAC00J644INRNG7&results=1");
        map.put("SM11", preUrl + "718/feed.json?key=T42SU8NKLQ4KYF5E&results=1");
        map.put("SM12", preUrl + "719/feed.json?key=OTSO6GP0GO9XUAU3&results=1");
        //photo resistor
        map.put("PR0", preUrl + "743/feed.json?key=ZH7OQMKALAPRZXQJ&results=1");
        //floor humidity
        map.put("FH0", preUrl + "723/feed.json?key=IG7Z0OW1NR2LVGSW&results=1");
        //pump status
        map.put("PSV", preUrl + "671/feed.json?key=XAKAVEUUJQ9GZGMT&results=1");
        map.put("PSF", preUrl + "744/feed.json?key=PD74MGJ9RFR4YMHK&results=1");
        map.put("PST", preUrl + "750/feed.json?key=FGGX36CLKXBW2BN4&results=1");
        //light bulb status
        map.put("LBS", preUrl + "746/feed.json?key=17J1AKN992YL3HUX&results=1");
        //gas sensor
        map.put("G", preUrl + "751/feed.json?key=05VOPP6KT2NA1AZ5&results=1");
        //tap status
        map.put("TS", preUrl + "748/feed.json?key=A1079N4WNZESIRFC&results=1");
        //motion detector
        map.put("MD", preUrl + "752/feed.json?key=ZCLT56CFVCG7DU50&results=1");
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
        dataveg1 = (TextView) findViewById(R.id.Righttextveg1);
        dataveg2 = (TextView) findViewById(R.id.Righttextveg2);
        dataveg3 = (TextView) findViewById(R.id.Righttextveg3);
        dataveg4 = (TextView) findViewById(R.id.Righttextveg4);
        dataf1 = (TextView) findViewById(R.id.RightTextFlower1);
        dataf2 = (TextView) findViewById(R.id.RightTextFlower2);
        dataf3 = (TextView) findViewById(R.id.RightTextFlower3);
        dataf4 = (TextView) findViewById(R.id.RightTextFlower4);
        dataf5 = (TextView) findViewById(R.id.RightTextFlower5);
        datapr = (TextView) findViewById(R.id.Righttextbrightness);
        wf1 = (ImageView) findViewById(R.id.Rightwaterf1);
        wf2 = (ImageView) findViewById(R.id.Rightwaterf2);
        wf3 = (ImageView) findViewById(R.id.Rightwaterf3);
        wf4 = (ImageView) findViewById(R.id.Rightwaterf4);
        wf5 = (ImageView) findViewById(R.id.Rightwaterf5);
        wv1 = (ImageView) findViewById(R.id.Rightwaterv1);
        wv2 = (ImageView) findViewById(R.id.Rightwaterv2);
        wv3 = (ImageView) findViewById(R.id.Rightwaterv3);
        wv4 = (ImageView) findViewById(R.id.Rightwaterv4);
        datafm0 = (TextView) findViewById(R.id.Righttextfloormoisture);
        puddle= (ImageView) findViewById(R.id.Rightpuddlewater);
        mistbtn2 = (ImageButton) findViewById(R.id.Rightmist_btn2);
        mistbtn = (ImageButton) findViewById(R.id.Rightmist_btn);
        datawm = (TextView) findViewById(R.id.textwattmeter1);
        datag = (TextView) findViewById(R.id.textsmoke);
        onlamp = (ImageView) findViewById(R.id.Rightonlamp);
        offlamp = (ImageView) findViewById(R.id.Rightofflamp);
        onswitch = (ImageButton) findViewById(R.id.Righton_switch);
        offswitch = (ImageButton) findViewById(R.id.Rightoff_switch);
        motion= (ImageView) findViewById(R.id.Rightonmotion);
        faucet = (ImageButton) findViewById(R.id.left_faucet);
        flower_faucet = (ImageButton) findViewById(R.id.left_faucet_flowers);





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
                       // Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }

                }
        );
        queueR.add(getRequest);
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
                //        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequest2);


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
                            plant = (ImageButton) findViewById(R.id.RightVegtable1);
                            plant.setVisibility(View.VISIBLE);
                            thirsty0 = (ImageView) findViewById(R.id.Rightthirstyveg1);
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
                                plant = (ImageButton) findViewById(R.id.RightVegtable1);
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
                        Toast.makeText(GraphicalViewRight.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestveg1);


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
                            plant = (ImageButton) findViewById(R.id.RightVegtable2);
                            plant.setVisibility(View.VISIBLE);
                            thirsty1 = (ImageView) findViewById(R.id.Rightthirstyveg2);
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
                                plant = (ImageButton) findViewById(R.id.RightVegtable2);
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
                   //     Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestveg2);


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
                            plant = (ImageButton) findViewById(R.id.RightVegtable3);
                            plant.setVisibility(View.VISIBLE);
                            thirsty2 = (ImageView) findViewById(R.id.Rightthirstyveg3);
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
                                plant = (ImageButton) findViewById(R.id.RightVegtable3);
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
                     //   Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestveg3);


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
                            plant = (ImageButton) findViewById(R.id.RightVegtable4);
                            plant.setVisibility(View.VISIBLE);
                            thirsty3 = (ImageView) findViewById(R.id.Rightthirstyveg4);
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
                                plant = (ImageButton) findViewById(R.id.RightVegtable4);
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
                       // Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestveg4);


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
                            plant = (ImageButton) findViewById(R.id.RightFlower1);
                            plant.setVisibility(View.VISIBLE);
                            thirsty8 = (ImageView) findViewById(R.id.Rightthirstyflower1);
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
                 //       Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestf1);
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
                            plant = (ImageButton) findViewById(R.id.RightFlower2);
                            plant.setVisibility(View.VISIBLE);
                            thirsty9 = (ImageView) findViewById(R.id.Rightthirstyflower2);
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
                   //     Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestf2);
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
                            plant = (ImageButton) findViewById(R.id.RightFlower3);
                            plant.setVisibility(View.VISIBLE);
                            thirsty10 = (ImageView) findViewById(R.id.Rightthirstyflower3);
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
                 //       Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestf3);
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
                            plant = (ImageButton) findViewById(R.id.RightFlower4);
                            plant.setVisibility(View.VISIBLE);
                            thirsty11 = (ImageView) findViewById(R.id.Rightthirstyflower4);
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
                   //     Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestf4);
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
                            plant = (ImageButton) findViewById(R.id.RightFlower5);
                            plant.setVisibility(View.VISIBLE);
                            thirsty12 = (ImageView) findViewById(R.id.Rightthirstyflower5);
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
                 //       Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestf5);

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
                            background = (ConstraintLayout) findViewById(R.id.Rightbackgounrd);

                            if (timeOfDay >= 17 || timeOfDay < 6 && num < 300) {
                                background.setBackgroundResource(R.drawable.night_right);

                            } else if (timeOfDay < 17 && timeOfDay >= 6 && num > 300) {
                                background.setBackgroundResource(R.drawable.sunny_right);

                            } else if (timeOfDay < 17 && timeOfDay >= 6 && num < 300) {
                                background.setBackgroundResource(R.drawable.cloudy_right);

                            } else
                                background.setBackgroundResource(R.drawable.rightbalcony);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                      //  Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestpr);

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
//                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestwl);

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
                             psf = Integer.parseInt(serial);

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
//                        Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestpsf);

        flower_faucet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (water_level <= 5) {
                    Toast.makeText(GraphicalViewRight.this, "Not enough water in tanker", Toast.LENGTH_SHORT).show();
                } else {
                    if (psf == 0) {
                        Animation rotation = AnimationUtils.loadAnimation(GraphicalViewRight.this, R.anim.rotation);
                        flower_faucet.startAnimation(rotation);

                        StringRequest postRequestpsf = new StringRequest(Request.Method.POST, preip + "setPS0ON",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // response
                                        //  Log.d("Response", response);
                                        Toast.makeText(GraphicalViewRight.this, "pump is on", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        //    Log.d("Error.Response", response);
                                        Toast.makeText(GraphicalViewRight.this, "can't connect to pump" + Integer.toString(psf), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        queueR.add(postRequestpsf);

                    } else if (psf == 1) {
                        Animation rotation = AnimationUtils.loadAnimation(GraphicalViewRight.this, R.anim.close_rotation);
                        flower_faucet.startAnimation(rotation);
                        StringRequest postRequestpsfoff = new StringRequest(Request.Method.POST, preip + "setPS0OFF",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // response
                                        //  Log.d("Response", response);
                                        Toast.makeText(GraphicalViewRight.this, "pump is off", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        //    Log.d("Error.Response", response);
                                        Toast.makeText(GraphicalViewRight.this, "can't connect to pump " + Integer.toString(psv), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        queueR.add(postRequestpsfoff);
                    }
                }
            }
        });


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
                            psv = Integer.parseInt(serial);

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
 //                       Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestpsv);

        faucet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(water_level <= 5){ Toast.makeText(GraphicalViewRight.this, "Not enough water in tanker", Toast.LENGTH_SHORT).show();}
              //  else {
                    if (psv == 0) {
                        Animation rotation = AnimationUtils.loadAnimation(GraphicalViewRight.this, R.anim.rotation);
                        faucet.startAnimation(rotation);

                        StringRequest postRequestpsv = new StringRequest(Request.Method.POST, preip + "setPS1ON",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // response
                                        //  Log.d("Response", response);
                                        Toast.makeText(GraphicalViewRight.this, "pump is on", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        //    Log.d("Error.Response", response);
                                        Toast.makeText(GraphicalViewRight.this, "can't connect to pump on" + Integer.toString(psv), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        queueR.add(postRequestpsv);

                    } else if (psv == 1) {
                        Animation rotation = AnimationUtils.loadAnimation(GraphicalViewRight.this, R.anim.close_rotation);
                        faucet.startAnimation(rotation);
                        StringRequest postRequestpsvoff = new StringRequest(Request.Method.POST, preip + "setPS1OFF",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // response
                                        //  Log.d("Response", response);
                                        Toast.makeText(GraphicalViewRight.this, "pump is off", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        //    Log.d("Error.Response", response);
                                        Toast.makeText(GraphicalViewRight.this, "can't connect to pump off" + Integer.toString(psv), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        queueR.add(postRequestpsvoff);
                    }
             //   }
            }
        });


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
                            int num = Integer.parseInt(serial);
                            datafm0.setText(serial);
                            if(num < 400){
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
   //                     Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestfh0);


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
      //                  Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestfh0);


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
                if (!(((AnimationDrawable) mistbtn.getBackground()).isRunning()) && !(((AnimationDrawable) mistbtn2.getBackground()).isRunning()) && up_pump_status==1)
                        Toast.makeText(GraphicalViewRight.this, " it's already on", Toast.LENGTH_SHORT).show();
                else if (!(((AnimationDrawable) mistbtn.getBackground()).isRunning()) && !(((AnimationDrawable) mistbtn2.getBackground()).isRunning()) && up_pump_status==0) {

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
                                    Toast.makeText(GraphicalViewRight.this, "pump is on", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    //    Log.d("Error.Response", response);
                                    Toast.makeText(GraphicalViewRight.this, "can't connect to pump to turn on", Toast.LENGTH_SHORT).show();
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
                    queueR.add(postRequest);


                }
                else if((((AnimationDrawable) mistbtn.getBackground()).isRunning()) && (((AnimationDrawable) mistbtn2.getBackground()).isRunning()) && up_pump_status==0)
                    Toast.makeText(GraphicalViewRight.this, "it's already off", Toast.LENGTH_SHORT).show();

                else {


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
                    StringRequest postRequest = new StringRequest(Request.Method.POST, preip+"setPS2OFF",
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    //  Log.d("Response", response);
                                    Toast.makeText(GraphicalViewRight.this, "pump is off", Toast.LENGTH_SHORT).show();

                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    //    Log.d("Error.Response", response);
                                    Toast.makeText(GraphicalViewRight.this, "can't connect to pump", Toast.LENGTH_SHORT).show();
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
                    queueR.add(postRequest);
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
                                    Toast.makeText(GraphicalViewRight.this, "pump is on", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    //    Log.d("Error.Response", response);
                                    Toast.makeText(GraphicalViewRight.this, "can't connect to pump", Toast.LENGTH_SHORT).show();
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
                    queueR.add(postRequestPSon);

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
                                    Toast.makeText(GraphicalViewRight.this, "pump is off", Toast.LENGTH_SHORT).show();

                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    //    Log.d("Error.Response", response);
                                    Toast.makeText(GraphicalViewRight.this, "can't connect to pump", Toast.LENGTH_SHORT).show();
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
                    queueR.add(postRequestPSoff);


                }
            }
        });

//        //lamp status

        JsonObjectRequest getRequestlbs = new JsonObjectRequest(Request.Method.GET, map.get("LBS"), null,
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
                            lamp_status = num;
                            if(lamp_status == 1){
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestlbs);


        offswitch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if( lamp_status == 1 )
                    Toast.makeText(GraphicalViewRight.this, " Lamp is already on", Toast.LENGTH_SHORT).show();
                else{
                if (onlamp.getVisibility() == View.INVISIBLE && onswitch.getVisibility() == View.INVISIBLE) {

                    onswitch.setVisibility(View.VISIBLE);
                    offswitch.setVisibility(View.INVISIBLE);
                    onlamp.setVisibility(View.VISIBLE);
                    onlamp.post(new Runnable() {
                        @Override
                        public void run() {

                            ((AnimationDrawable) onlamp.getBackground()).start();

                        }
                    });
                    StringRequest postRequestLBSON = new StringRequest(Request.Method.POST, preip + "setLBS1ON",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    //   Log.d("Response", response);
                                    Toast.makeText(GraphicalViewRight.this, "Lamp is on", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Toast.makeText(GraphicalViewRight.this, "problem turning the lamp on", Toast.LENGTH_SHORT).show();

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
                            }
                    );
                    queueR.add(postRequestLBSON);
                }

                }}

        });
        onswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lamp_status == 0)
                    Toast.makeText(GraphicalViewRight.this, " Lamp is already off", Toast.LENGTH_SHORT).show();
                else {
                    offswitch.setVisibility(View.VISIBLE);
                    onswitch.setVisibility(View.INVISIBLE);
                    onlamp.post(new Runnable() {
                        @Override
                        public void run() {

                            ((AnimationDrawable) onlamp.getBackground()).stop();

                        }
                    });
                    onlamp.setVisibility(View.INVISIBLE);
                    StringRequest postRequestLBSoff = new StringRequest(Request.Method.POST, preip + "setLBS1OFF",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    Toast.makeText(GraphicalViewRight.this, "Lamp is off", Toast.LENGTH_SHORT).show();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Toast.makeText(GraphicalViewRight.this, "problem turning the lamp off", Toast.LENGTH_SHORT).show();

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
                            }
                    );
                    queueR.add(postRequestLBSoff);
                } }
        });

        //motion detector
        GetSendData md = new GetSendData();
        d = md.GetData(map.get("MD"));
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
                }
                ,
                new Response.ErrorListener()
                {
                    @Override public void onErrorResponse(VolleyError error) {
                    }
                }
        );queueR.add(getRequestmd);


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
                      //  Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestwm);


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
                        //Toast.makeText(GraphicalView.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
        );queueR.add(getRequestg);
//

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





