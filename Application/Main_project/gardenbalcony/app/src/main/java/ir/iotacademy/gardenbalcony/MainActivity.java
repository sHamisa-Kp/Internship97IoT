package ir.iotacademy.gardenbalcony;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity  {

    private CardView Graphical_view,chart_dashboard,Value_dashboard,Hybrid_dashboard,Error_dashboard;
    private ImageButton notif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Graphical_view= (CardView) findViewById(R.id.graphical);

        Graphical_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GraphicalView.class);
                startActivity(intent);
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
                Intent intent = new Intent(MainActivity.this, MixedDashboard.class);
                startActivity(intent);
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
