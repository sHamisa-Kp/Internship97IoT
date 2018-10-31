package ir.iotacademy.gardenbalcony;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity  {

    private CardView Graphical_view,chart_dashboard,Value_dashboard,Hybrid_dashboard,Error_dashboard;
    private ImageButton notif;


    String preUrl="http://thingtalk.ir/channels/";
    String preip="http://10.1.248.34:5050/actuators/";
    Map<String,String> map=new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Graphical_view= (CardView) findViewById(R.id.graphical);
        final TextView Text = (TextView) findViewById(R.id.graphicalText);
        Graphical_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GetSendData t0 = new GetSendData();
                //map.put("T0", preUrl + "629/feed.json?key=G7KHR97UPN9OC5AC&results=1");
                //if(t0.GetData(map.get("T0")) != "No") {
                    Intent intent = new Intent(MainActivity.this, GraphicalViewRight.class);
                    startActivity(intent);
                //}
                //else {
       //             Toast toast = Toast.makeText(MainActivity.this, "Can not access the sever", Toast.LENGTH_LONG);
               // Text.setText("No internet access");
                //}
            }
        });

        Value_dashboard= (CardView) findViewById(R.id.value);

        Value_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JustValue.class);
                startActivity(intent);
            }
        });

        chart_dashboard= (CardView) findViewById(R.id.charts);

       chart_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartDashboard.class);
                startActivity(intent);
            }
        });

        Hybrid_dashboard= (CardView) findViewById(R.id.hybrid);

        Hybrid_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MixedDashboard.class);
//                startActivity(intent);

                Intent i = new Intent(MainActivity.this, MixedDashboard.class);
                i.putExtra(MixedDashboard.WEBSITE_ADDRESS, "https://themesdesign.in/upcube/layouts/vertical/index.html");
                startActivity(i);
            }
        });

        Error_dashboard= (CardView) findViewById(R.id.error);

        Error_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ErrorDashboard.class);
                startActivity(intent);
            }
        });

        chart_dashboard= (CardView) findViewById(R.id.charts);

        chart_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartDashboard.class);
                startActivity(intent);
            }
        });


        notif= (ImageButton) findViewById(R.id.notification);
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Notifications.class);
                startActivity(intent);
            }
        });








    }








}
