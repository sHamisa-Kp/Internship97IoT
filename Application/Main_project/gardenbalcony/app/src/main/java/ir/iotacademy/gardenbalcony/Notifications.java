package ir.iotacademy.gardenbalcony;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Notifications extends AppCompatActivity {

    String preUrl = "http://thingtalk.ir/channels/";
    Map<String, String> map = new HashMap<String, String>();
    boolean connected = false;

    String d;

    int datan;
    int datann;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //weather
        map.put("T0", preUrl + "629/feed.json?key=G7KHR97UPN9OC5AC&results=1");
        map.put("T1", preUrl + "720/feed.json?key=6P4WUZHZZDR6U0TX&results=1");

        map.put("H0", preUrl + "669/feed.json?key=7TPW8OQOGN1EMURD&results=1");
        map.put("H1", preUrl + "721/feed.json?key=YV8JRH910ZJZQN1I&results=1");

        //vegetables
        map.put("SM0", preUrl + "670/feed.json?key=LUJ9D21E177HESAW&results=1");
        map.put("SM1", preUrl + "700/feed.json?key=Q2JH4OBED4QQAA74&results=1");
        map.put("SM2", preUrl + "701/feed.json?key=P6LY6LV7CPYYSJUP&results=1");
        map.put("SM3", preUrl + "702/feed.json?key=PVWFQI4XLXLWL9DL&results=1");

        map.put("SM4", preUrl + "703/feed.json?key=6W8DYBXG0HDA141O&results=1");
        map.put("SM5", preUrl + "704/feed.json?key=HZ9VM1PH1Q6LQSLZ&results=1");
        map.put("SM6", preUrl + "705/feed.json?key=RUB06UUHPX0K4DDS&results=1");
        map.put("SM7", preUrl + "706/feed.json?key=WQFB2JIGRVXDIAR4&results=1");

        //flowers
        map.put("SM8", preUrl + "710/feed.json?key=FYMFZRW8E2YLIKUK&results=1");
        map.put("SM9", preUrl + "711/feed.json?key=OXXNS5C338I0TCIR&results=1");
        map.put("SM10", preUrl + "712/feed.json?key=MZEZGGZL09ZB48L1&results=1");
        map.put("SM11", preUrl + "713/feed.json?key=7R9CHM056LSY23ZO&results=1");
        map.put("SM12", preUrl + "714/feed.json?key=ZPX8TF59L251UFVE&results=1");

        map.put("SM13", preUrl + "715/feed.json?key=3DMD11SS9G5B94I0&results=1");
        map.put("SM14", preUrl + "716/feed.json?key=QLH9NDZ20RQNK96S&results=1");
        map.put("SM15", preUrl + "717/feed.json?key=BVAC00J644INRNG7&results=1");
        map.put("SM16", preUrl + "718/feed.json?key=T42SU8NKLQ4KYF5E&results=1");
        map.put("SM17", preUrl + "719/feed.json?key=OTSO6GP0GO9XUAU3&results=1");

        //photo resistor
        map.put("PR0", preUrl + "672/feed.json?key=B1JQYWFKX2PCRBYF&results=1");

        map.put("PR1", preUrl + "743/feed.json?key=ZH7OQMKALAPRZXQJ&results=1");

        //floor humidity
        map.put("FH0", preUrl + "722/feed.json?key=3ZIOUDCBO1X4W0B7&results=1");

        map.put("FH1", preUrl + "723/feed.json?key=IG7Z0OW1NR2LVGSW&results=1");

        //light bulb status
        map.put("LBS0", preUrl + "745/feed.json?key=2ZRJZIFPTQF79NOH&results=1");

        map.put("LBS1", preUrl + "746/feed.json?key=17J1AKN992YL3HUX&results=1");
        //gas sensor
        map.put("G0", preUrl + "747/feed.json?key=65ZJD9TRET64FJ03&results=1");

        map.put("G1", preUrl + "751/feed.json?key=05VOPP6KT2NA1AZ5&results=1");

        //motion detector
        map.put("MD0", preUrl + "749/feed.json?key=V197BB4SL21A2IKG&results=1");

        map.put("MD1", preUrl + "752/feed.json?key=ZCLT56CFVCG7DU50&results=1");

        //watt meter
        map.put("WM", preUrl + "753/feed.json?key=OUAV3VIB076Y5UO0&results=1");

        //water level
        map.put("WL", preUrl + "742/feed.json?key=WGWJ660WN7V9394D&results=1");









    /*     maxairhumidity;
         minairhumidity;

         maxsoilmoisture_flower;
         minsoilmoisture_flower;

         maxsoilmoisture_veg;
        minsoilmoisture_veg;

        gas;

        int floormoisture;

        int motiondetect;

        int level;*/

        ImageButton canselminhumidity = (ImageButton) findViewById(R.id.canselminhumidity);
        final FrameLayout minairhumidity = (FrameLayout) findViewById(R.id.minweatherhumidity);
        TextView tvminhumidity = (TextView) findViewById(R.id.tvminhumidity);

        ImageButton canselmaxhumidity = (ImageButton) findViewById(R.id.canselmaxhumidity);
        final FrameLayout maxairhumidity = (FrameLayout) findViewById(R.id.maxweatherhumidity);
        TextView tvmaxhumidity = (TextView) findViewById(R.id.tvmaxhumidity);

        ImageButton canselminairtemperature = (ImageButton) findViewById(R.id.canselminairtemperature);
        final FrameLayout minairtemperature = (FrameLayout) findViewById(R.id.minairtemperature);
        TextView tvmintemperature = (TextView) findViewById(R.id.tvmintemperature);

        ImageButton canselmaxairtemperature = (ImageButton) findViewById(R.id.canselmaxairtemperature);
        final FrameLayout maxairtemperature = (FrameLayout) findViewById(R.id.maxairtemperature);
        TextView tvmaxtemperature = (TextView) findViewById(R.id.tvmaxtemperature);

        ImageButton canselminsoilmoisture_flower = (ImageButton) findViewById(R.id.canselminsoilmoisture_flower);
        final FrameLayout minsoilmoisture_flower = (FrameLayout) findViewById(R.id.minsoilmoisture_flower);
        TextView tvminsoilmoitureflower = (TextView) findViewById(R.id.tvminsoilmoisture_flower);

        ImageButton canselmaxsoilmoisture_flower = (ImageButton) findViewById(R.id.canselmaxsoilmoisture_flower);
        final FrameLayout maxsoilmoisture_flower = (FrameLayout) findViewById(R.id.maxsoilmoisture_flower);
        TextView tvmaxsoilmoistureflower = (TextView) findViewById(R.id.tvmaxsoilmoisture_flower);

        ImageButton canselminsoilmoisture_veg = (ImageButton) findViewById(R.id.canselminsoilmoisture_veg);
        final FrameLayout minsoilmoisture_veg = (FrameLayout) findViewById(R.id.minsoilmoisture_veg);
        TextView tvminsoilmoistureveg = (TextView) findViewById(R.id.tvminsoilmoisture_veg);

        ImageButton canselmaxsoilmoisture_veg = (ImageButton) findViewById(R.id.canselmaxsoilmoisture_veg);
        final FrameLayout maxsoilmoisture_veg = (FrameLayout) findViewById(R.id.maxsoilmoisture_veg);
        TextView tvmaxsoilmoistureveg = (TextView) findViewById(R.id.tvmaxsoilmoisture_veg);

        ImageButton canselgas = (ImageButton) findViewById(R.id.canselgas);
        final FrameLayout gas = (FrameLayout) findViewById(R.id.gas);
        TextView tvgas = (TextView) findViewById(R.id.tvgas);

        ImageButton canselfloormoisture = (ImageButton) findViewById(R.id.canselfloormoisture);
        final FrameLayout floormoisture = (FrameLayout) findViewById(R.id.floormoisture);
        TextView tvfloormoisture = (TextView) findViewById(R.id.tvfloormoisture);

        ImageButton canselmotiondetector = (ImageButton) findViewById(R.id.canselmotiondetect);
        final FrameLayout motiondecetor = (FrameLayout) findViewById(R.id.motiondetect);
        TextView tvmotiondection = (TextView) findViewById(R.id.tvmotiondetect);

        ImageButton canselhighsourcelevel = (ImageButton) findViewById(R.id.canselhighsourcelevel);
        final FrameLayout highsourcelevel = (FrameLayout) findViewById(R.id.highlevel);
        TextView tvhighsourcelevel = (TextView) findViewById(R.id.tvhighlevel);

        ImageButton cansellowsourcelevel = (ImageButton) findViewById(R.id.cansellowsourcelevel);
        final FrameLayout lowsourcelevel = (FrameLayout) findViewById(R.id.lowlevel);
        TextView tvlowsourcelevel = (TextView) findViewById(R.id.tvlowlevel);

        ImageButton canselonlamp = (ImageButton) findViewById(R.id.canselonlamp);
        final FrameLayout lamponday = (FrameLayout) findViewById(R.id.lamponday);
        TextView tvlampon = (TextView) findViewById(R.id.tvlampon);


        final  RequestQueue queue = Volley.newRequestQueue(getApplicationContext());



        //lamp start
        for (int i = 0; i < 2; i++) {
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, map.get("LBS" + Integer.toString(i)), null,
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
                                datan = Integer.parseInt(serial);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                        }


                    }
            );
            queue.add(getRequest);



            JsonObjectRequest getRequest1 = new JsonObjectRequest(Request.Method.GET, map.get("PR" + Integer.toString(i)), null,
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
                                datann = Integer.parseInt(serial);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                        }


                    }
            );
            queue.add(getRequest1);

            if (datan == 1 && datann > 300) {
                createNotificationDayOnLamp(i);
                if (i == 0)
                    tvlampon.setText("the left balcony lamp is on,it's not dark!");
                if (i == 1)
                    tvlampon.setText("the right balcony lamp is on,it's not dark!");
                lamponday.setVisibility(View.VISIBLE);
            }
        }



        canselonlamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lamponday.setVisibility(View.GONE);

            }
        });

        //lamp end

        //source start

        JsonObjectRequest getRequest2 = new JsonObjectRequest(Request.Method.GET, map.get("WL"), null,
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
                            datan = Integer.parseInt(serial);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }


                }
        );
        queue.add(getRequest2);

        if (datan > 95) {
            createNotificationHighWaterLevel();
            tvhighsourcelevel.setText("the water source will be full soon!");
            highsourcelevel.setVisibility(View.VISIBLE);
        }


        canselhighsourcelevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highsourcelevel.setVisibility(View.GONE);

            }
        });


        if (datan < 5) {
            createNotificationLowWaterLevel();
            tvlowsourcelevel.setText("the water source will be empty soon!");
            lowsourcelevel.setVisibility(View.VISIBLE);
        }

        cansellowsourcelevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowsourcelevel.setVisibility(View.GONE);

            }
        });

        //source end

        //motion detection start
        for (int i = 0; i < 2; i++) {
            JsonObjectRequest getRequest3 = new JsonObjectRequest(Request.Method.GET, map.get("MD" + Integer.toString(i)), null,
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
                                datan = Integer.parseInt(serial);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                        }


                    }
            );
            queue.add(getRequest3);

            if (datan == 1) {
                createNotificationMotionDetection(i);
                if (i == 0)
                    tvmotiondection.setText("something is moving in left balcony!");
                if (i == 1)
                    tvmotiondection.setText("tsomething is moving in right balcony!");
                motiondecetor.setVisibility(View.VISIBLE);
            }
        }

        canselmotiondetector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motiondecetor.setVisibility(View.GONE);

            }
        });

        //motion detection end

        //floor moisture start
        for (int i = 0; i < 2; i++) {
            JsonObjectRequest getRequest4 = new JsonObjectRequest(Request.Method.GET, map.get("FH" + Integer.toString(i)), null,
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
                                datan = Integer.parseInt(serial);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                        }


                    }
            );
            queue.add(getRequest4);

            if (datan == 1) {
                createNotificationFloorMoisture(i);
                if (i == 0)
                    tvfloormoisture.setText("the floor is wet in left balcony!");
                if (i == 1)
                    tvfloormoisture.setText("the floor is wet in right balcony!");
                floormoisture.setVisibility(View.VISIBLE);
            }
        }
        canselfloormoisture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floormoisture.setVisibility(View.GONE);

            }
        });
        //floor moisture end


        //gas start
        for (int i = 0; i < 2; i++) {
            JsonObjectRequest getRequest5 = new JsonObjectRequest(Request.Method.GET, map.get("G" + Integer.toString(i)), null,
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
                                datan = Integer.parseInt(serial);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                        }


                    }
            );
            queue.add(getRequest5);

            if (datan > 50) {
                createNotificationGas(i);
                if (i == 0)
                    tvgas.setText("there is too much smoke in left balcony!");
                if (i == 1)
                    tvgas.setText("there is too much smoke in right balcony!");
                gas.setVisibility(View.VISIBLE);
            }
        }
        canselgas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gas.setVisibility(View.GONE);

            }
        });

        //gas end

        //trmperature start
        int average;

        JsonObjectRequest getRequest6 = new JsonObjectRequest(Request.Method.GET, map.get("T0"), null,
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
                            datan = Integer.parseInt(serial);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }


                }
        );queue.add(getRequest6);

        JsonObjectRequest getRequest7 = new JsonObjectRequest(Request.Method.GET, map.get("T0"), null,
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
                            datann = Integer.parseInt(serial);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }


                }
        );queue.add(getRequest7);
        average = datan + datann / 2;
        if (average > 35) {
            createNotificationMaxTemperature();
            tvmaxtemperature.setText("the weather is too warm in balconies!");
            maxairtemperature.setVisibility(View.VISIBLE);
        }

        canselmaxairtemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxairtemperature.setVisibility(View.GONE);

            }
        });

        if (average < 3) {
            createNotificationMinTemperature();
            tvmintemperature.setText("the weather is too cold in balconies!");
            minairtemperature.setVisibility(View.VISIBLE);
        }

        canselminairtemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minairtemperature.setVisibility(View.GONE);

            }
        });
        //trmperature end

        //weather humidity start

        JsonObjectRequest getRequest8 = new JsonObjectRequest(Request.Method.GET, map.get("H0"), null,
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
                            datan = Integer.parseInt(serial);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }


                }
        );queue.add(getRequest8);

        JsonObjectRequest getRequest9 = new JsonObjectRequest(Request.Method.GET, map.get("H1"), null,
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
                            datann = Integer.parseInt(serial);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }


                }
        );queue.add(getRequest9);

        average = datan + datann / 2;
        if (average > 85) {
            createNotificationMaxHumidity();
            tvmaxhumidity.setText("the weather humidity is too high in balconies!");
            maxairhumidity.setVisibility(View.VISIBLE);
        }
        canselmaxhumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxairhumidity.setVisibility(View.GONE);

            }
        });
        if (average < 25) {
            createNotificationMinHumidity();
            tvminhumidity.setText("the weather humidity is too low in balconies!");
            minairhumidity.setVisibility(View.VISIBLE);
        }
        canselminhumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minairhumidity.setVisibility(View.GONE);

            }
        });

        //weather humidity end

        //vegetables start
        for (int i = 0; i < 8; i++) {
            JsonObjectRequest getRequest10 = new JsonObjectRequest(Request.Method.GET, map.get("SM" + Integer.toString(i)), null,
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
                                datan = Integer.parseInt(serial);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                        }


                    }
            );queue.add(getRequest10);

            if (datan < 50) {
                createNotificationMinVegetableSoilMoisture(i);
                tvminsoilmoistureveg.setText("the soil moisture of vegetable '" + Integer.toString(i) + "' is low!");
                minsoilmoisture_veg.setVisibility(View.VISIBLE);
            }

            if (datan > 95) {
                createNotificationMaxVegetableSoilMoisture(i);
                tvmaxsoilmoistureveg.setText("the soil moisture of vegetable '" + Integer.toString(i) + "' is high!");
                maxsoilmoisture_veg.setVisibility(View.VISIBLE);
            }
        }
        canselminsoilmoisture_veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minsoilmoisture_veg.setVisibility(View.GONE);

            }
        });
        canselmaxsoilmoisture_veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxsoilmoisture_veg.setVisibility(View.GONE);

            }
        });
        //vegetables end
        //flowers start
        for (int i = 8; i < 18; i++) {
            JsonObjectRequest getRequest11 = new JsonObjectRequest(Request.Method.GET, map.get("SM" + Integer.toString(i)), null,
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
                                datan = Integer.parseInt(serial);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                        }


                    }
            );queue.add(getRequest11);

            if (datan < 50) {
                createNotificationMinFlowerSoilMoisture(i - 8);
                tvminsoilmoitureflower.setText("the soil moisture of flower '" + Integer.toString(i - 8) + "' is low!");
                minsoilmoisture_flower.setVisibility(View.VISIBLE);
            }
            if (datan > 95) {
                createNotificationMaxFlowerSoilMoisture(i - 8);
                tvmaxsoilmoistureflower.setText("the soil moisture of flower '" + Integer.toString(i - 8) + "' is high!");
                maxsoilmoisture_flower.setVisibility(View.VISIBLE);
            }
        }
        canselminsoilmoisture_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minsoilmoisture_flower.setVisibility(View.GONE);

            }
        });
        canselmaxsoilmoisture_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxsoilmoisture_flower.setVisibility(View.GONE);

            }
        });

    }
    //flowers end

    //lamp

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationDayOnLamp (int where){
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        if (where == 0) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Wonder")

                    .setContentText("the left balcony lamp is on,it's not dark!")
                    .setSmallIcon(R.drawable.light_bulb_error)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
        }
        if (where == 1) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Wonder")

                    .setContentText("the right balcony lamp is on,it's not dark!")
                    .setSmallIcon(R.drawable.light_bulb_error)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
        }

    }

    //source

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationHighWaterLevel () {
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the water source will be full soon!")
                .setSmallIcon(R.drawable.source_error_notif)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationLowWaterLevel () {
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the water source will be empty soon!")
                .setSmallIcon(R.drawable.source_error)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    //motion detection

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMotionDetection (int where){
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        if (where == 0) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Wonder")

                    .setContentText("something is moving in left balcony!")
                    .setSmallIcon(R.drawable.on2_md)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
        }
        if (where == 1) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Wonder")

                    .setContentText("something is moving in right balcony!")
                    .setSmallIcon(R.drawable.on2_md)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationFloorMoisture (int where){
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        if (where == 0) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Wonder")

                    .setContentText("the floor is wet in left balcony!")
                    .setSmallIcon(R.drawable.puddle)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
        }
        if (where == 1) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Wonder")

                    .setContentText("the floor is wet in right balcony!")
                    .setSmallIcon(R.drawable.puddle)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
        }
    }
    //gas

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationGas (int where){
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        if (where == 0) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Wonder")

                    .setContentText("there is too much smoke in left balcony!")
                    .setSmallIcon(R.drawable.smoke)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
        }
        if (where == 1) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Wonder")

                    .setContentText("there is too much smoke in right balcony!")
                    .setSmallIcon(R.drawable.smoke)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
        }
    }
    //weather temperature

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxTemperature () {
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the weather is too cold in balconies!")
                .setSmallIcon(R.drawable.minairhumidity)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinTemperature () {
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the weather is too warm in balconies!")
                .setSmallIcon(R.drawable.maxairhumidity)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    //weather humidity

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxHumidity () {
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the weather humidity is too high in balconies!")
                .setSmallIcon(R.drawable.humidity_high)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinHumidity () {
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the weather humidity is too low in balconies!")
                .setSmallIcon(R.drawable.humidity_low)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    //vegetables

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinVegetableSoilMoisture (int which){
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the soil moisture of vegetable '" + Integer.toString(which) + "' is low!")
                .setSmallIcon(R.drawable.veg2)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxVegetableSoilMoisture (int which){
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the soil moisture of vegetable '" + Integer.toString(which) + "' is high!")
                .setSmallIcon(R.drawable.veg1)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    //flowers

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinFlowerSoilMoisture (int which){
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the soil moisture of flower '" + Integer.toString(which) + "' is low!")
                .setSmallIcon(R.drawable.flower2)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxFlowerSoilMoisture(int which) {
        Intent intent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the soil moisture of flower '" + Integer.toString(which) + "' is high!")
                .setSmallIcon(R.drawable.flower1)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

}

