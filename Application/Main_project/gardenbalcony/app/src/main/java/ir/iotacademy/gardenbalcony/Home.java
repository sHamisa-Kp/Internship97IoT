package ir.iotacademy.gardenbalcony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    ImageButton clouds;
    ImageButton drop;
    ImageButton grass;
    ImageButton sun;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        clouds = (ImageButton) findViewById(R.id.imagebutton3);
        grass = (ImageButton) findViewById(R.id.imagebutton1);
        drop = (ImageButton) findViewById(R.id.imagebutton4);
        sun  = (ImageButton) findViewById(R.id.imagebutton2);

        clouds.setOnLongClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


            }


        });

    }
}
