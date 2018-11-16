package ir.iotacademy.gardenbalcony;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Middle_View extends AppCompatActivity {
    int wl;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle__view);
        ImageButton goLeft,goRight;
        final ImageView source1,source2,source3,source4,source5,source6;



       /* goRight = (ImageButton) findViewById(R.id.go_right2);
        goLeft = (ImageButton) findViewById(R.id.go_left);*/

        source6= (ImageView) findViewById(R.id.source6);
        source5= (ImageView) findViewById(R.id.source5);
        source4= (ImageView) findViewById(R.id.source4);
        source3= (ImageView) findViewById(R.id.source3);
        source2= (ImageView) findViewById(R.id.source2);
        source1= (ImageView) findViewById(R.id.source1);
        tv= (TextView) findViewById(R.id.textvieww1);// GetSendData WL = new GetSendData();
        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, "http://thingtalk.ir/channels/742/feed.json?key=WGWJ660WN7V9394D&results=1", null,
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
                            wl = Integer.parseInt(serial);
                            tv.setText(serial+"%");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Middle_View.this, "Connection Problem", Toast.LENGTH_SHORT).show();

                    }


                }
        );
        queue.add(getRequest);

        if(wl<=5){
            source6.setVisibility(View.VISIBLE);
            source6.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) source6.getBackground()).start();
                }
            });


            source1.setVisibility(View.INVISIBLE);
            source2.setVisibility(View.INVISIBLE);
            source3.setVisibility(View.INVISIBLE);
            source4.setVisibility(View.INVISIBLE);
            source5.setVisibility(View.INVISIBLE);

        }
        else {
            source6.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) source6.getBackground()).stop();
                }
            });

            if (wl <= 20 && wl > 5) {
                source5.setVisibility(View.VISIBLE);
                source1.setVisibility(View.INVISIBLE);
                source2.setVisibility(View.INVISIBLE);
                source3.setVisibility(View.INVISIBLE);
                source4.setVisibility(View.INVISIBLE);
                source6.setVisibility(View.INVISIBLE);

            }
            if (wl <= 40 && wl > 20) {
                source4.setVisibility(View.VISIBLE);
                source1.setVisibility(View.INVISIBLE);
                source2.setVisibility(View.INVISIBLE);
                source3.setVisibility(View.INVISIBLE);
                source5.setVisibility(View.INVISIBLE);
                source6.setVisibility(View.INVISIBLE);

            }
            if (wl <= 60 && wl > 40) {
                source3.setVisibility(View.VISIBLE);
                source1.setVisibility(View.INVISIBLE);
                source2.setVisibility(View.INVISIBLE);
                source5.setVisibility(View.INVISIBLE);
                source4.setVisibility(View.INVISIBLE);
                source6.setVisibility(View.INVISIBLE);

            }
            if (wl <= 80 && wl > 60) {
                source2.setVisibility(View.VISIBLE);
                source1.setVisibility(View.INVISIBLE);
                source5.setVisibility(View.INVISIBLE);
                source3.setVisibility(View.INVISIBLE);
                source4.setVisibility(View.INVISIBLE);
                source6.setVisibility(View.INVISIBLE);

            }
            if (wl <= 100 && wl > 80) {
                source1.setVisibility(View.VISIBLE);
                source5.setVisibility(View.INVISIBLE);
                source2.setVisibility(View.INVISIBLE);
                source3.setVisibility(View.INVISIBLE);
                source4.setVisibility(View.INVISIBLE);
                source6.setVisibility(View.INVISIBLE);

            }
        }
     /*   goRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Middle_View.this, GraphicalViewRight.class);
                startActivity(intent);
            }
        });

        goLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Middle_View.this, GraphicalView.class);
                startActivity(intent);
            }
        });*/
    }



}
