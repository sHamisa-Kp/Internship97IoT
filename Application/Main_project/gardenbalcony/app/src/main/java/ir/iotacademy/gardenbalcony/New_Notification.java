package ir.iotacademy.gardenbalcony;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ir.iotacademy.gardenbalcony.R.drawable.*;
import static ir.iotacademy.gardenbalcony.R.id.relativeLayout;
import static ir.iotacademy.gardenbalcony.R.id.relativeLayoutcard;

public class New_Notification extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    String preUrl = "http://thingtalk.ir/channels/";
    Map<String, String> map = new HashMap<String, String>();
    boolean connected = false;
    String d;
    int datawl = 10;
    int datalbs;
    int datann, datamd, datafm, datag, datat0, datat1, average, averageh, datah0, datah1, datavsm, datafsm;

    private CardViewAdapter mAdapter;

    private ArrayList<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__notification);

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
        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        Thread thread = new Thread() {
            int t = 0;
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        if(t < 34)
                            Thread.sleep(700);
                        else
                            Thread.sleep(25000);
                        runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void run() {
        mItems = new ArrayList<>(34);
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
                                datalbs = Integer.parseInt(serial);
                                t++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(New_Notification.this, "Connection Problem", Toast.LENGTH_SHORT).show();

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
                                t++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(New_Notification.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                        }


                    }
            );
            queue.add(getRequest1);

            if (datalbs == 1) {
                 createNotificationDayOnLamp(i);
                if (i == 0)
                    mItems.add(String.format("the left balcony lamp is on,it's not dark!"));

                if (i == 1)
                    mItems.add(String.format("the right balcony lamp is on,it's not dark!"));
                //  tvlampon.setText("the right balcony lamp is on,it's not dark!");
                // lamponday.setVisibility(View.VISIBLE);
            }
        }

                                //water tank start
        JsonObjectRequest getRequest2 = new JsonObjectRequest(Request.Method.GET, map.get("WL"), null,
                new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {try {JSONArray array = response.getJSONArray("feeds");
                                                JSONObject data = array.getJSONObject(0);
                                                String serial = data.getString("field1");
                                                if (serial.indexOf('.') != -1) {
                                                    serial = serial.substring(0, serial.indexOf('.'));
                                                }
                                                datawl = Integer.parseInt(serial);

                                                t++;
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }}
                                        }
                ,
                new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {//Toast.makeText(Notification.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                                            }


                                        }
        );queue.add(getRequest2);
                                if (datawl < 5) {
                                    createNotificationLowWaterLevel();
                                    mItems.add(String.format("the water source will be empty soon!"));
                                }
                                if (datawl > 95) {
                                    createNotificationHighWaterLevel();
                                    mItems.add(String.format("the water source will be full soon!"));
                                }
                                //source end

    //motion detection start
     for (int i = 0; i < 2; i++) {
         JsonObjectRequest getRequestmd = new JsonObjectRequest(Request.Method.GET, map.get("MD" + Integer.toString(i)), null,
                 new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {try {
                                                    JSONArray array = response.getJSONArray("feeds");
                                                    JSONObject data = array.getJSONObject(0);
                                                    String serial = data.getString("field1");
                                                    if (serial.indexOf('.') != -1) {
                                                        serial = serial.substring(0, serial.indexOf('.'));
                                                        }
                                                        datamd = Integer.parseInt(serial);
                                                        t++;
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                            ,
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                 //   Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                                                }


                                            }
                                    );
                                    queue.add(getRequestmd);

                                    if (datamd == 1) {
                                        createNotificationMotionDetection(i);
                                        if (i == 0)
                                            mItems.add(String.format("something is moving in left balcony!"));
                                        if (i == 1)
                                            mItems.add(String.format("something is moving in Right balcony!"));
                                    }
                                }

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
                                                        datafm = Integer.parseInt(serial);
                                                            t++;
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                            ,
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }


                                            }
                                    );
                                    queue.add(getRequest4);

                                    if (datafm < 400) {
                                        createNotificationFloorMoisture(i);
                                        if (i == 0)
                                            mItems.add(String.format("the floor is wet in left balcony!"));
                                        if (i == 1)
                                            mItems.add(String.format("the floor is wet in right balcony!"));
                                    }
                                }

                                //floor moisture end


                                //gas start
                                for (int i = 0; i < 2; i++) {
                                    JsonObjectRequest getRequestg = new JsonObjectRequest(Request.Method.GET, map.get("G" + Integer.toString(i)), null,
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
                                                        datag = Integer.parseInt(serial);
                                                        t++;
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                            ,
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }


                                            }
                                    );
                                    queue.add(getRequestg);

                                    if (datag > 50) {
                                        createNotificationGas(i);
                                        if (i == 0)
                                            mItems.add(String.format("there is too much smoke in left balcony!"));
                                        if (i == 1)
                                            mItems.add(String.format("there is too much smoke in right balcony!"));
                                    }
                                }
                                //gas end

                                //trmperature start

                                JsonObjectRequest getRequestT0 = new JsonObjectRequest(Request.Method.GET, map.get("T0"), null,
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
                                                    datat0 = Integer.parseInt(serial);
                                                    t++;

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        ,
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                            //    Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                                            }


                                        }
                                );queue.add(getRequestT0);

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
                                                    datat1 = Integer.parseInt(serial);
                                                    t++;

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        ,
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                              //  Toast.makeText(Notifications.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                                            }


                                        }
                                );queue.add(getRequest7);
                                average = datat0 + datat1 / 2;
                                if (average > 35) {
                                    createNotificationMaxTemperature();
                                    mItems.add(String.format("the weather is too warm in balconies!!"));

                                }
                                if (average < 5) {
                                    createNotificationMinTemperature();
                                    mItems.add(String.format("the weather is too warm in balconies!!"));
                                }
                                //trmperature end


                                //weather humidity start

                                JsonObjectRequest getRequesth0 = new JsonObjectRequest(Request.Method.GET, map.get("H0"), null,
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
                                                    datah0 = Integer.parseInt(serial);

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        ,
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }


                                        }
                                );queue.add(getRequesth0);

                                JsonObjectRequest getRequesth1 = new JsonObjectRequest(Request.Method.GET, map.get("H1"), null,
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
                                                    datah1 = Integer.parseInt(serial);
                                                    t++;

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        ,
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                            }
                                        }
                                );queue.add(getRequesth1);

                                averageh = (datah0 + datah1) / 2;
                                if (averageh > 85) {
                                    createNotificationMaxHumidity();
                                    mItems.add(String.format("humidity is too high in balconies!"));
                                }
                                if (averageh < 5) {
                                    createNotificationMinHumidity();
                                    mItems.add(String.format("humidity is too low in balconies!"));
                                }
                                //weather humidity end


                                //vegetables start
                                for (int i = 0; i < 8; i++) {
                                    JsonObjectRequest getRequestvsm = new JsonObjectRequest(Request.Method.GET, map.get("SM" + Integer.toString(i)), null,
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
                                                        datavsm = Integer.parseInt(serial);
                                                        t++;

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                            ,
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }


                                            }
                                    );queue.add(getRequestvsm);

                                    if (datavsm < 50) {
                                        createNotificationMinVegetableSoilMoisture(i);
                                        mItems.add(String.format("vegtable " + Integer.toString(i + 1) + "needs watering"));

                                    }

                                    if (datavsm > 95) {
                                        createNotificationMaxVegetableSoilMoisture(i);
                                        mItems.add(String.format("vegtable " + Integer.toString(i + 1) + "has more than enough water!"));
                                    }
                                }

                                //vegetables end

                                //flowers start
                                for (int i = 8; i < 18; i++) {
                                    JsonObjectRequest getRequestfsm = new JsonObjectRequest(Request.Method.GET, map.get("SM" + Integer.toString(i)), null,
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
                                                        datafsm = Integer.parseInt(serial);

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                            ,
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }


                                            }
                                    );queue.add(getRequestfsm);

                                    if (datafsm < 50) {
                                        createNotificationMinFlowerSoilMoisture(i - 7);
                                        mItems.add(String.format("Flower" + Integer.toString(i - 7) + " needs watering"));
                                    }
                                    if (datafsm > 95) {
                                        createNotificationMaxFlowerSoilMoisture(i - 7);
                                        mItems.add(String.format("flower" + Integer.toString(i - 7) + "already has enough water"));
                                    }
                                }



                            //flowers end

                                OnItemTouchListener itemTouchListener = new OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                Toast.makeText(New_Notification.this, "Tapped " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButton1Click(View view, int position) {
                Toast.makeText(New_Notification.this, "Clicked Button1 in " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButton2Click(View view, int position) {
                Toast.makeText(New_Notification.this, "Clicked Button2 in " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }
        };

        mAdapter = new CardViewAdapter(mItems, itemTouchListener);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(New_Notification.this));
        recyclerView.setAdapter(mAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
//                                    Toast.makeText(MainActivity.this, mItems.get(position) + " swiped left", Toast.LENGTH_SHORT).show();
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
//                                    Toast.makeText(MainActivity.this, mItems.get(position) + " swiped right", Toast.LENGTH_SHORT).show();
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        recyclerView.addOnItemTouchListener(swipeTouchListener);


    }
});
        }
        } catch (InterruptedException e) {
        }
        }
        };
        thread.start();


        }

    /**
     * Interface for the touch events in each item
     */
    public interface OnItemTouchListener {
        /**
         * Callback invoked when the user Taps one of the RecyclerView items
         *
         * @param view     the CardView touched
         * @param position the index of the item touched in the RecyclerView
         */
        void onCardViewTap(View view, int position);

        /**
         * Callback invoked when the Button1 of an item is touched
         *
         * @param view     the Button touched
         * @param position the index of the item touched in the RecyclerView
         */
        void onButton1Click(View view, int position);

        /**
         * Callback invoked when the Button2 of an item is touched
         *
         * @param view     the Button touched
         * @param position the index of the item touched in the RecyclerView
         */
        void onButton2Click(View view, int position);
    }

    /**
     * A simple adapter that loads a CardView layout with one TextView and two Buttons, and
     * listens to clicks on the Buttons or on the CardView
     */

    public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
        private List<String> cards;
        private OnItemTouchListener onItemTouchListener;

        public CardViewAdapter(ArrayList<String> cards, OnItemTouchListener onItemTouchListener) {
            this.cards = cards;
            this.onItemTouchListener = onItemTouchListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.title.setText(cards.get(i));
            if(cards.get(i).indexOf("lamp")>=0)
                viewHolder.button2.setImageResource(R.drawable.light_bulb_error);
            else if (cards.get(i).indexOf("empty")>=0)
                viewHolder.button2.setImageResource(R.drawable.source_error_notif);
            else if(cards.get(i).indexOf("full")>=0)
                viewHolder.button2.setImageResource(R.drawable.source_error_empty_notif);
            else if(cards.get(i).indexOf("moving") >= 0)
                viewHolder.button2.setImageResource(R.drawable.on2_md);
            else if(cards.get(i).indexOf("floor") >= 0)
                viewHolder.button2.setImageResource(R.drawable.puddle);
            else if(cards.get(i).indexOf("smoke") >= 0)
                viewHolder.button2.setImageResource(R.drawable.smoke);
            else if(cards.get(i).indexOf("warm") >= 0)
                viewHolder.button2.setImageResource(R.drawable.maxairhumidity_notif);
            else if(cards.get(i).indexOf("cold") >= 0)
                viewHolder.button2.setImageResource(R.drawable.minairhumidity_notif);
            else if(cards.get(i).indexOf("too low") >= 0)
                viewHolder.button2.setImageResource(R.drawable.humidity_low);
            else if(cards.get(i).indexOf("high") >= 0)
                viewHolder.button2.setImageResource(R.drawable.humidity_high);
            else if(cards.get(i).indexOf("enough") >= 0 && cards.get(i).indexOf("vegtable") >= 0)
                viewHolder.button2.setImageResource(R.drawable.veg1_notif);
            else if(cards.get(i).indexOf("watering") >= 0 && cards.get(i).indexOf("vegtable") >=0)
                viewHolder.button2.setImageResource(R.drawable.veg2_notif);
            else if(cards.get(i).indexOf("flower") >=0)
                viewHolder.button2.setImageResource(R.drawable.flower1_notif);
            else if(cards.get(i).indexOf("Flower") >=0) {
                viewHolder.button2.setImageResource(R.drawable.flower2_notif);
//                viewHolder.relativeLayout.setBackgroundColor(R.color.colorAccent);
            }







        }

        @Override
        public int getItemCount() {
            return cards == null ? 0 : cards.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private ImageButton button1;
            private ImageButton button2;
            private RelativeLayout relativeLayout;

            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.card_view_title);
                button1 = (ImageButton) itemView.findViewById(R.id.canselmaxhumidity);
                button2 = (ImageButton) itemView.findViewById(R.id.humidity_high);
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutcard);


                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemTouchListener.onButton1Click(v, getLayoutPosition());
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemTouchListener.onButton2Click(v, getLayoutPosition());
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemTouchListener.onCardViewTap(v, getLayoutPosition());
                    }
                });
            }
        }
    }
    // Lamp notif
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationDayOnLamp (int where){
        Intent intent = new Intent(this, New_Notification.class);
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

    //****** water tank notifs ********
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationHighWaterLevel () {
        Intent intent = new Intent(this, New_Notification.class);
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

    // ****** motiondetection notif*******


    //motion detection

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMotionDetection (int where){
        Intent intent = new Intent(this, New_Notification.class);
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
// ****** floormoisture notif **********
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationFloorMoisture (int where){
        Intent intent = new Intent(this, New_Notification.class);
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


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationGas (int where){
        Intent intent = new Intent(this, New_Notification.class);
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

    //  ********** weather temperature *************

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxTemperature () {
        Intent intent = new Intent(this, New_Notification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the weather is too cold in balconies!")
                .setSmallIcon(R.drawable.minairhumidity_notif)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinTemperature () {
        Intent intent = new Intent(this, New_Notification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("the weather is too warm in balconies!")
                .setSmallIcon(R.drawable.maxairhumidity_notif)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    // *************** weather humidity notif **********

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxHumidity () {
        Intent intent = new Intent(this, New_Notification.class);
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
        Intent intent = new Intent(this, New_Notification.class);
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


    //************* vegetables *************

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinVegetableSoilMoisture (int which){
        Intent intent = new Intent(this, New_Notification.class);
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
        Intent intent = new Intent(this, New_Notification.class);
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
        Intent intent = new Intent(this, New_Notification.class);
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
        Intent intent = new Intent(this, New_Notification.class);
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

