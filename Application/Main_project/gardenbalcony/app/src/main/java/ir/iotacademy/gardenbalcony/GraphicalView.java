package ir.iotacademy.gardenbalcony;

import android.app.ActionBar;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.*;

import java.util.HashMap;
import java.util.Map;

import static ir.iotacademy.gardenbalcony.R.drawable.day;
import static ir.iotacademy.gardenbalcony.R.drawable.drop;
import static ir.iotacademy.gardenbalcony.R.drawable.mist_off;

public class GraphicalView extends AppCompatActivity {
    Button test;
    ImageView onlamp,offlamp,mist;
    ImageButton onswitch,offswitch,mistbtn,mistbtn2,go_to_the_right_position;
    ConstraintLayout background;
    Drawable day;

    String preUrl="http://thingtalk.ir/channels/";
    String preip="http://10.1.248.33:5050/";
    Map<String,String> map=new HashMap<String, String>();

    // private GyroscopeObserver gyroscopeObserver;
    //View decorView = getWindow().getDecorView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_view);

        onlamp= (ImageView) findViewById(R.id.onlamp);
        offlamp= (ImageView) findViewById(R.id.offlamp);
        onswitch= (ImageButton) findViewById(R.id.on_switch);
        offswitch= (ImageButton) findViewById(R.id.off_switch);



        offswitch.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


                if(onlamp.getVisibility()==View.INVISIBLE&&onswitch.getVisibility()==View.INVISIBLE) {
                    onswitch.setVisibility(View.VISIBLE);
                    offswitch.setVisibility(View.INVISIBLE);
                    onlamp.setVisibility(View.VISIBLE);
                    onlamp.post(new Runnable() {
                        @Override
                        public void run() {

                            ((AnimationDrawable)onlamp.getBackground()).start();

                        }
                    });

                }
            }
        });
        onswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                offswitch.setVisibility(View.VISIBLE);
                onswitch.setVisibility(View.INVISIBLE);
                onlamp.post(new Runnable() {
                    @Override
                    public void run() {

                        ((AnimationDrawable)onlamp.getBackground()).stop();

                    }
                });
                onlamp.setVisibility(View.INVISIBLE);

            }
        });


        mistbtn= (ImageButton) findViewById(R.id.mist_btn);

        mistbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if(!(((AnimationDrawable)mistbtn.getBackground()).isRunning())&&!(((AnimationDrawable)mistbtn2.getBackground()).isRunning())){

                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mistbtn.getBackground()).start();
                        }
                    });
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mistbtn2.getBackground()).start();
                        }
                    });

                }
                else {
                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mistbtn.getBackground()).stop();
                        }
                    });
                    mistbtn.setVisibility(View.INVISIBLE);
                    mistbtn.setVisibility(View.VISIBLE);
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mistbtn2.getBackground()).stop();
                        }
                    });
                    mistbtn2.setVisibility(View.INVISIBLE);
                    mistbtn2.setVisibility(View.VISIBLE);


                }
            }
        });

        mistbtn2= (ImageButton) findViewById(R.id.mist_btn2);

        mistbtn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if(!(((AnimationDrawable)mistbtn.getBackground()).isRunning())&&!(((AnimationDrawable)mistbtn2.getBackground()).isRunning())){

                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mistbtn.getBackground()).start();
                        }
                    });
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mistbtn2.getBackground()).start();
                        }
                    });

                }
                else {
                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mistbtn.getBackground()).stop();
                        }
                    });
                    mistbtn.setVisibility(View.INVISIBLE);
                    mistbtn.setVisibility(View.VISIBLE);
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mistbtn2.getBackground()).stop();
                        }
                    });
                    mistbtn2.setVisibility(View.INVISIBLE);
                    mistbtn2.setVisibility(View.VISIBLE);


                }
            }
        });

        go_to_the_right_position= (ImageButton) findViewById(R.id.go_right);
        background= (ConstraintLayout) findViewById(R.id.backgounrd);

        go_to_the_right_position.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                background.setBackgroundResource(R.drawable.night);
            }
        });


        map.put("T0",preUrl+"629/feed.json?key=G7KHR97UPN9OC5AC&results=1");
    }



}