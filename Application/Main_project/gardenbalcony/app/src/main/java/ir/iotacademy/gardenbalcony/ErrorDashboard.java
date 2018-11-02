package ir.iotacademy.gardenbalcony;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

public class ErrorDashboard extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_value);
        WebView dashboard = (WebView) findViewById(R.id.webdashboard);
        dashboard.getSettings().setJavaScriptEnabled(true);
        dashboard.getSettings().setBuiltInZoomControls(true);
        dashboard.setWebChromeClient(new WebChromeClient());
        dashboard.loadUrl("file:///android_asset/www/View4/view4.html");

    }

}
