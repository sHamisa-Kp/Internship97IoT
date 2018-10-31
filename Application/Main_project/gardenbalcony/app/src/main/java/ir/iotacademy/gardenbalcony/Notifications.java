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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Notifications extends AppCompatActivity {

    String preUrl="http://thingtalk.ir/channels/";
    Map<String,String> map=new HashMap<String, String>();

   String d;

    int light0;
    int light1;

    int maxairhumidity;
    int minairhumidity;

    int maxsoilmoisture_flower;
    int minsoilmoisture_flower;

    int maxsoilmoisture_veg;
    int minsoilmoisture_veg;

    int gas;

    int floormoisture;

    int motiondetect;

    int level;




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

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

        GetSendData data = new GetSendData();



        light0=Integer.parseInt(data.GetData(map.get("LBS")));


    /*    int maxairhumidity;
        int minairhumidity;

        int maxsoilmoisture_flower;
        int minsoilmoisture_flower;

        int maxsoilmoisture_veg;
        int minsoilmoisture_veg;

        int gas;

        int floormoisture;

        int motiondetect;

        int level;*/

        ImageButton canselminlight = (ImageButton) findViewById(R.id.canselminlight);
        ImageButton canselmaxlight = (ImageButton) findViewById(R.id.canselmaxlight);
        ImageButton canselminairhumidity = (ImageButton) findViewById(R.id.canselminairhumidity);
        ImageButton canselmaxairhumidity = (ImageButton) findViewById(R.id.canselmaxairhumidity);
        ImageButton canselminsoilmoisture_flower = (ImageButton) findViewById(R.id.canselminsoilmoisture_flower);
        ImageButton canselmaxsoilmoisture_flower = (ImageButton) findViewById(R.id.canselmaxsoilmoisture_flower);
        ImageButton canselminsoilmoisture_veg = (ImageButton) findViewById(R.id.canselminsoilmoisture_veg);
        ImageButton canselmaxsoilmoisture_veg = (ImageButton) findViewById(R.id.canselmaxsoilmoisture_veg);

        if (light0 > 3){
            createNotificationMaxLight();
        }
        if (light0<2){
            createNotificationMinLight();
        }
        if (maxairhumidity>3){
            createNotificationMaxAirHumidity();
        }
        if (minairhumidity<2){
            
            createNotificationMinAirHumidity();
        }
        if (maxsoilmoisture_flower>2){
            createNotificationMaxSoilMoisture_Flower();
        }
        if (minsoilmoisture_flower<1){
            createNotificationMinSoilMoisture_Flower();
        }
        if (maxsoilmoisture_veg>4){
            createNotificationMaxSoilMoisture_Veg();
        }
        if (minsoilmoisture_veg<2){
            createNotificationMinSoilMoisture_Veg();
        }
        if (gas>5){
            createNotificationGas();
        }
        if (floormoisture>3){
            createNotificationFloorMoisture();
        }
        if (motiondetect==1){
            createNotificationMotionDetect();
        }
        if (level>3){
            createNotificationLevel();
        }
        canselminlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxLight(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Wonder")
                .setContentText("Light for your garden is in its maximum")
                .setSmallIcon(R.drawable.sun)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinLight(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Light for your garden is in its minimum")
                .setSmallIcon(R.drawable.cloudy)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxAirHumidity(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Air humidity for your garden is in its maximum")
                .setSmallIcon(R.drawable.maxairhumidity)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinAirHumidity(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Air humidity for your garden is in its minimum")
                .setSmallIcon(R.drawable.minairhumidity)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxSoilMoisture_Flower(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Air humidity for your garden is in its minimum")
                .setSmallIcon(R.drawable.minairhumidity)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinSoilMoisture_Flower(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Soil moisture for your flower is in its minimum")
                .setSmallIcon(R.drawable.flower2)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMaxSoilMoisture_Veg(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Soil moisture for your flowers is in its maximum")
                .setSmallIcon(R.drawable.veg1)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMinSoilMoisture_Veg(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Soil moisture for your vegtables is in its minimum")
                .setSmallIcon(R.drawable.veg2)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationGas(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Gas in circumstance id too much")
                .setSmallIcon(R.drawable.co2)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationFloorMoisture(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Floor moisture is in its maximum")
                .setSmallIcon(R.drawable.floormoisture)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationMotionDetect(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("Some one has detected!")
                .setSmallIcon(R.drawable.motiondetect)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotificationLevel() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("WONDER")
                .setContentText("level for your water source is few")
                .setSmallIcon(R.drawable.waterlevel)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
}
