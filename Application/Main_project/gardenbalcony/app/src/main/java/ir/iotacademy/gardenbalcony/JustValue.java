package ir.iotacademy.gardenbalcony;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class JustValue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_value);
        WebView dashboard = (WebView) findViewById(R.id.webdashboard);
        dashboard.getSettings().setJavaScriptEnabled(true);
        dashboard.setWebChromeClient(new WebChromeClient());
        dashboard.loadUrl("file:///android_asset/www/value.html");

    }
}
