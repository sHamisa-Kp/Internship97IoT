package ir.iotacademy.gardenbalcony;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Middle_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle__view);
        ImageButton goLeft,goRight;
        final ImageView source1,source2,source3,source4,source5,source6;
        String wl;

        goRight = (ImageButton) findViewById(R.id.go_right2);
        goLeft = (ImageButton) findViewById(R.id.go_left);


        GetSendData WL = new GetSendData();
        wl=WL.GetData("http://thingtalk.ir/channels/742/feed.json?key=WGWJ660WN7V9394D&results=1");

        source6= (ImageView) findViewById(R.id.source6);
        if(Integer.parseInt(wl)==0){
            source6.setVisibility(View.VISIBLE);
            source6.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) source6.getBackground()).start();
                }
            });

            //plant.setVisibility(View.INVISIBLE);

        }

        goRight.setOnClickListener(new View.OnClickListener() {
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
        });
    }



}
