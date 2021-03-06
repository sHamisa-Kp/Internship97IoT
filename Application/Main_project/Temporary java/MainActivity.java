package ir.iotacademy.gardenbalcony;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity  {

    private CardView Graphical_view_right,Graphical_view_left,Graphical_view_middle,chart_dashboard,Value_dashboard,Hybrid_dashboard,Error_dashboard;
    private ImageButton notif;
    TextView NIC;


<<<<<<< Updated upstream
=======
    String preUrl="http://thingtalk.ir/channels/";
    String preip="http://10.1.248.34:5050/actuators/";
    Map<String,String> map=new HashMap<String, String>();
    boolean connected = false;
>>>>>>> Stashed changes

    String preUrl="http://thingtalk.ir/channels/";
    String preip="http://10.1.248.34:5050/actuators/";
    Map<String,String> map=new HashMap<String, String>();
    boolean connected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Graphical_view_right= (CardView) findViewById(R.id.graphicalright);
        final TextView Text = (TextView) findViewById(R.id.graphicalText);
        LinearLayout background= (LinearLayout) findViewById(R.id.backgounrd);

        Graphical_view_right.setOnClickListener(new View.OnClickListener() {
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

        Graphical_view_middle= (CardView) findViewById(R.id.graphicalmiddle);


        Graphical_view_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GetSendData t0 = new GetSendData();
                //map.put("T0", preUrl + "629/feed.json?key=G7KHR97UPN9OC5AC&results=1");
                //if(t0.GetData(map.get("T0")) != "No") {
                Intent intent = new Intent(MainActivity.this, Middle_View.class);
                startActivity(intent);
                //}
                //else {
                //             Toast toast = Toast.makeText(MainActivity.this, "Can not access the sever", Toast.LENGTH_LONG);
                // Text.setText("No internet access");
                //}
            }
        });

        Graphical_view_left= (CardView) findViewById(R.id.graphicalleft);


        Graphical_view_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GetSendData t0 = new GetSendData();
                //map.put("T0", preUrl + "629/feed.json?key=G7KHR97UPN9OC5AC&results=1");
                //if(t0.GetData(map.get("T0")) != "No") {
                Intent intent = new Intent(MainActivity.this, GraphicalView.class);
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
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
<<<<<<< HEAD



=======



>>>>>>> master
    }



    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        Graphical_view_right= (CardView) findViewById(R.id.graphicalright);

        if(connected == false){
               // Toast.makeText(MainActivity.this, "You must have internet access", Toast.LENGTH_LONG).show();
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("No Wi-Fi");
            alertDialog.setMessage("You must have internet access to continue");
            alertDialog.setIcon(R.drawable.wifi);
//            alertDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
//            alertDialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.avatar);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
          //  Graphical_view_right.setEnabled(false);
        }
    }
}
