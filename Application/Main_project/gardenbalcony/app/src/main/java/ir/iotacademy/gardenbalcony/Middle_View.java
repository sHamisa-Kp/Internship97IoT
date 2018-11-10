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
        int wl;

       /* goRight = (ImageButton) findViewById(R.id.go_right2);
        goLeft = (ImageButton) findViewById(R.id.go_left);*/

        source6= (ImageView) findViewById(R.id.source6);
        source5= (ImageView) findViewById(R.id.source5);
        source4= (ImageView) findViewById(R.id.source4);
        source3= (ImageView) findViewById(R.id.source3);
        source2= (ImageView) findViewById(R.id.source2);
        source1= (ImageView) findViewById(R.id.source1);

        GetSendData WL = new GetSendData();
        wl=Integer.parseInt(WL.GetData("http://thingtalk.ir/channels/742/feed.json?key=WGWJ660WN7V9394D&results=1"));


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
