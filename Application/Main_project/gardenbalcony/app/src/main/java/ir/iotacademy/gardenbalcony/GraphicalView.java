package ir.iotacademy.gardenbalcony;

import android.app.ActionBar;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

import java.util.HashMap;
import java.util.Map;

import static ir.iotacademy.gardenbalcony.R.drawable.day;
import static ir.iotacademy.gardenbalcony.R.drawable.drop;
import static ir.iotacademy.gardenbalcony.R.drawable.mist_off;

public class GraphicalView extends AppCompatActivity {
    Button test;
    ImageView onlamp,offlamp,mist,thirsty0,thirsty1,thirsty2,thirsty3,thirsty8,thirsty9,thirsty10,thirsty11,thirsty12,wf1,wf2,wf3,wf4,wf5,wv1,wv2,wv3,wv4,puddle,motion;
    ImageButton onswitch,offswitch,mistbtn,mistbtn2,go_to_the_right_position,plant;
    ConstraintLayout background;
    TextView data;
    String d,d1;

    String preUrl="http://thingtalk.ir/channels/";
    String preip="http://10.1.248.34:5050/actuators/";
    Map<String,String> map=new HashMap<String, String>();

    // private GyroscopeObserver gyroscopeObserver;
    //View decorView = getWindow().getDecorView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_view);

        go_to_the_right_position = (ImageButton) findViewById(R.id.go_right);


        go_to_the_right_position.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GraphicalView.this, Middle_View.class);
                startActivity(intent);

            }
        });


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
        //water level
        map.put("WL", preUrl + "742/feed.json?key=WGWJ660WN7V9394D&results=1");
//        Thread t = new Thread() {
//
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(6000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {


//
//        //Temperature
        data = (TextView) findViewById(R.id.temperature);
        GetSendData t0 = new GetSendData();
        data.setText(t0.GetData(map.get("T0")) + "°C");


        //Humidity
        data = (TextView) findViewById(R.id.humidity);
        GetSendData h0 = new GetSendData();
        data.setText(h0.GetData(map.get("H0")) + "%");



        //Vegetables
        data = (TextView) findViewById(R.id.textveg1);
        GetSendData sm0 = new GetSendData();
        d = sm0.GetData(map.get("SM0"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.veg1);
        plant.setVisibility(View.VISIBLE);
        thirsty0 = (ImageView) findViewById(R.id.thirstyveg1);
        thirsty0.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty0.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty0.setVisibility(View.VISIBLE);
            thirsty0.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty0.getBackground()).start();
                }
            });
            plant = (ImageButton) findViewById(R.id.veg1);
            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.textveg2);
        GetSendData sm1 = new GetSendData();
        d = sm1.GetData(map.get("SM1"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.veg2);
        plant.setVisibility(View.VISIBLE);
        thirsty1 = (ImageView) findViewById(R.id.thirstyveg2);
        thirsty1.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty1.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty1.setVisibility(View.VISIBLE);
            thirsty1.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty1.getBackground()).start();
                }
            });
            plant = (ImageButton) findViewById(R.id.veg2);
            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.textveg3);
        GetSendData sm2 = new GetSendData();
        d = sm2.GetData(map.get("SM2"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.veg3);
        plant.setVisibility(View.VISIBLE);
        thirsty2 = (ImageView) findViewById(R.id.thirstyveg3);
        thirsty2.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty2.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty2.setVisibility(View.VISIBLE);
            thirsty2.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty2.getBackground()).start();
                }
            });

            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.textveg4);
        GetSendData sm3 = new GetSendData();
        d = sm3.GetData(map.get("SM3"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.veg4);
        plant.setVisibility(View.VISIBLE);
        thirsty3 = (ImageView) findViewById(R.id.thirstyveg4);
        thirsty3.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty3.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty3.setVisibility(View.VISIBLE);
            thirsty3.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty3.getBackground()).start();
                }
            });

            plant.setVisibility(View.INVISIBLE);
        }

        //Flowers
        data = (TextView) findViewById(R.id.textflower1);
        GetSendData sm8 = new GetSendData();
        d = sm8.GetData(map.get("SM8"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.flower1);
        plant.setVisibility(View.VISIBLE);
        thirsty8 = (ImageView) findViewById(R.id.thirstyflower1);
        thirsty8.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty8.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty8.setVisibility(View.VISIBLE);
            thirsty8.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty8.getBackground()).start();
                }
            });

            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.textflower2);
        GetSendData sm9 = new GetSendData();
        d = sm9.GetData(map.get("SM9"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.flower2);
        plant.setVisibility(View.VISIBLE);
        thirsty9 = (ImageView) findViewById(R.id.thirstyflower2);
        thirsty9.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty9.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty9.setVisibility(View.VISIBLE);
            thirsty9.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty9.getBackground()).start();
                }
            });

            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.textflower3);
        GetSendData sm10 = new GetSendData();
        d = sm10.GetData(map.get("SM10"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.flower3);
        plant.setVisibility(View.VISIBLE);
        thirsty10 = (ImageView) findViewById(R.id.thirstyflower3);
        thirsty10.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty10.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty10.setVisibility(View.VISIBLE);
            thirsty10.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty10.getBackground()).start();
                }
            });

            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.textflower4);
        GetSendData sm11 = new GetSendData();
        d = sm11.GetData(map.get("SM11"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.flower4);
        plant.setVisibility(View.VISIBLE);
        thirsty11 = (ImageView) findViewById(R.id.thirstyflower4);
        thirsty11.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty11.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty11.setVisibility(View.VISIBLE);
            thirsty11.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty11.getBackground()).start();
                }
            });

            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.textflower5);
        GetSendData sm12 = new GetSendData();
        d = sm12.GetData(map.get("SM12"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.flower5);
        plant.setVisibility(View.VISIBLE);
        thirsty12 = (ImageView) findViewById(R.id.thirstyflower5);
        thirsty12.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) thirsty12.getBackground()).stop();
            }
        });

        if (Integer.parseInt(d) < 50) {

            thirsty12.setVisibility(View.VISIBLE);
            thirsty12.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) thirsty12.getBackground()).start();
                }
            });

            plant.setVisibility(View.INVISIBLE);
        }


        //photoResistor
        data = (TextView) findViewById(R.id.textbrightness);
        GetSendData pr = new GetSendData();
        d = pr.GetData(map.get("PR0"));
        data.setText(d);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        background = (ConstraintLayout) findViewById(R.id.backgounrd);

        if (timeOfDay >= 17 || timeOfDay < 6 && Integer.parseInt(d) < 300) {
            background.setBackgroundResource(R.drawable.night);

        } else if (timeOfDay < 17 && timeOfDay >= 6 && Integer.parseInt(d) > 300) {
            background.setBackgroundResource(R.drawable.day);

        } else if (timeOfDay < 17 && timeOfDay >= 6 && Integer.parseInt(d) < 300) {
            background.setBackgroundResource(R.drawable.cloudy_left);

        } else
            background.setBackgroundResource(R.drawable.balcony);


        //watering
        GetSendData ts = new GetSendData();
        d1 = pr.GetData(map.get("WL"));

        //flower
        GetSendData psf = new GetSendData();
        d = pr.GetData(map.get("PSF"));


        wf1 = (ImageView) findViewById(R.id.waterf1);
        wf2 = (ImageView) findViewById(R.id.waterf2);
        wf3 = (ImageView) findViewById(R.id.waterf3);
        wf4 = (ImageView) findViewById(R.id.waterf4);
        wf5 = (ImageView) findViewById(R.id.waterf5);
        if (d.equals("1") && Integer.parseInt(d1)>5) {
            wf1.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf1.getBackground()).start();
                }
            });

            wf2.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf2.getBackground()).start();
                }
            });

            wf3.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf3.getBackground()).start();
                }
            });

            wf4.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf4.getBackground()).start();
                }
            });

            wf5.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf5.getBackground()).start();
                }
            });
        } else
        { wf1.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) wf1.getBackground()).stop();
            }
        });

            wf2.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf2.getBackground()).stop();
                }
            });

            wf3.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf3.getBackground()).stop();
                }
            });

            wf4.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf4.getBackground()).stop();
                }
            });

            wf5.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wf5.getBackground()).stop();
                }
            });

        }

        //vegetables
        GetSendData psv = new GetSendData();
        d = pr.GetData(map.get("PSV"));


        wv1 = (ImageView) findViewById(R.id.waterv1);
        wv2 = (ImageView) findViewById(R.id.waterv2);
        wv3 = (ImageView) findViewById(R.id.waterv3);
        wv4 = (ImageView) findViewById(R.id.waterv4);

        if (d.equals("1") && Integer.parseInt(d1)>5) {
            wv1.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wv1.getBackground()).start();
                }
            });

            wv2.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wv2.getBackground()).start();
                }
            });

            wv3.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wv3.getBackground()).start();
                }
            });

            wv4.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wv4.getBackground()).start();
                }
            });


        } else
        { wv1.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) wv1.getBackground()).stop();
            }
        });

            wv2.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wv2.getBackground()).stop();
                }
            });

            wv3.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wv3.getBackground()).stop();
                }
            });

            wv4.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) wv4.getBackground()).stop();
                }
            });


        }

        //floor humidity
        data = (TextView) findViewById(R.id.textfloormoisture);
        GetSendData fh = new GetSendData();
        d = fh.GetData(map.get("FH0"));
        data.setText("");

        puddle= (ImageView) findViewById(R.id.puddlewater);

        if(Integer.parseInt(d)==1){
            puddle.setVisibility(View.VISIBLE);
        }
        else
            puddle.setVisibility(View.INVISIBLE);



        //top pump status

        mistbtn2 = (ImageButton) findViewById(R.id.mist_btn2);
        mistbtn = (ImageButton) findViewById(R.id.mist_btn);
        GetSendData pst = new GetSendData();
        d = pst.GetData(map.get("PST"));

        if(Integer.parseInt(d1)>5&&Integer.parseInt(d)==1){
            mistbtn.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) mistbtn.getBackground()).start();
                }
            });
            mistbtn2.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) mistbtn2.getBackground()).start();
                }
            });
        }
        if(Integer.parseInt(d1)<=5&&Integer.parseInt(d)==0){
            mistbtn.post(new Runnable() {

                @Override
                public void run() {
                    ((AnimationDrawable) mistbtn.getBackground()).stop();
                }
            });
            mistbtn.setVisibility(View.INVISIBLE);
            mistbtn.setVisibility(View.VISIBLE);
            mistbtn2.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) mistbtn2.getBackground()).stop();
                }
            });
            mistbtn2.setVisibility(View.INVISIBLE);
            mistbtn2.setVisibility(View.VISIBLE);

        }


        mistbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                GetSendData pst = new GetSendData();
                d = pst.GetData(map.get("PST"));
                if (!(((AnimationDrawable) mistbtn.getBackground()).isRunning()) && !(((AnimationDrawable) mistbtn2.getBackground()).isRunning())&&Integer.parseInt(d1)>5&&Integer.parseInt(d)==0) {


                    pst.SetActuator(preip+"setPS2ON",d);

                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).start();
                        }
                    });
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).start();
                        }
                    });

                } else {


                    pst.SetActuator(preip+"setPS2OFF",d);
                    mistbtn.post(new Runnable() {

                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).stop();
                        }
                    });
                    mistbtn.setVisibility(View.INVISIBLE);
                    mistbtn.setVisibility(View.VISIBLE);
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).stop();
                        }
                    });
                    mistbtn2.setVisibility(View.INVISIBLE);
                    mistbtn2.setVisibility(View.VISIBLE);


                }
            }
        });



        mistbtn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                GetSendData pst = new GetSendData();
                d = pst.GetData(map.get("PST"));
                if (!(((AnimationDrawable) mistbtn.getBackground()).isRunning()) && !(((AnimationDrawable) mistbtn2.getBackground()).isRunning())&&Integer.parseInt(d1)>5&&Integer.parseInt(d)==0) {

                    pst.SetActuator(preip+"setPS2ON",d);

                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).start();
                        }
                    });
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).start();
                        }
                    });

                } else {

                    pst.SetActuator(preip+"setPS2OFF",d);
                    mistbtn.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn.getBackground()).stop();
                        }
                    });
                    mistbtn.setVisibility(View.INVISIBLE);
                    mistbtn.setVisibility(View.VISIBLE);
                    mistbtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable) mistbtn2.getBackground()).stop();
                        }
                    });
                    mistbtn2.setVisibility(View.INVISIBLE);
                    mistbtn2.setVisibility(View.VISIBLE);


                }
            }
        });

        //lamp status




        onlamp = (ImageView) findViewById(R.id.onlamp);
        offlamp = (ImageView) findViewById(R.id.offlamp);
        onswitch = (ImageButton) findViewById(R.id.on_switch);
        offswitch = (ImageButton) findViewById(R.id.off_switch);

        GetSendData lbs = new GetSendData();
        d = lbs.GetData(map.get("LBS"));
        if(Integer.parseInt(d)==1){

            onswitch.setVisibility(View.VISIBLE);
            offswitch.setVisibility(View.INVISIBLE);
            onlamp.setVisibility(View.VISIBLE);
            onlamp.post(new Runnable() {
                @Override
                public void run() {

                    ((AnimationDrawable) onlamp.getBackground()).start();

                }
            });
        }
        if(Integer.parseInt(d)==0){
            offswitch.setVisibility(View.VISIBLE);
            onswitch.setVisibility(View.INVISIBLE);
            onlamp.post(new Runnable() {
                @Override
                public void run() {

                    ((AnimationDrawable) onlamp.getBackground()).stop();

                }
            });
            onlamp.setVisibility(View.INVISIBLE);
        }

        offswitch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                GetSendData lbs = new GetSendData();
                d = lbs.GetData(map.get("LBS"));

                if (onlamp.getVisibility() == View.INVISIBLE && onswitch.getVisibility() == View.INVISIBLE&&Integer.parseInt(d)==0 ) {

                    lbs.SetActuator(preip+"setLBS0ON",d);

                    onswitch.setVisibility(View.VISIBLE);
                    offswitch.setVisibility(View.INVISIBLE);
                    onlamp.setVisibility(View.VISIBLE);
                    onlamp.post(new Runnable() {
                        @Override
                        public void run() {

                            ((AnimationDrawable) onlamp.getBackground()).start();

                        }
                    });

                }}

        });
        onswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetSendData lbs = new GetSendData();
                d = lbs.GetData(map.get("LBS"));
                lbs.SetActuator(preip+"setLBS0OFF",d);

                offswitch.setVisibility(View.VISIBLE);
                onswitch.setVisibility(View.INVISIBLE);
                onlamp.post(new Runnable() {
                    @Override
                    public void run() {

                        ((AnimationDrawable) onlamp.getBackground()).stop();

                    }
                });
                onlamp.setVisibility(View.INVISIBLE);

            }
        });

        //motion detector
        GetSendData md = new GetSendData();
        d = md.GetData(map.get("MD"));

        motion= (ImageView) findViewById(R.id.onmotion);
        if(d.equals("1")){

            motion.post(new Runnable() {
                @Override
                public void run() {

                    ((AnimationDrawable) motion.getBackground()).start();

                }
            });
            motion.setVisibility(View.VISIBLE);

        }
        else {
            motion.post(new Runnable() {
                @Override
                public void run() {

                    ((AnimationDrawable) motion.getBackground()).stop();

                }
            });
            motion.setVisibility(View.INVISIBLE);
        }

        //watt meter
        data= (TextView) findViewById(R.id.textwattmeter1);

        GetSendData wm = new GetSendData();
        d = wm.GetData(map.get("WM"));
        data.setText(d);

        //gas
        data= (TextView) findViewById(R.id.textsmoke);

        GetSendData g = new GetSendData();
        d = g.GetData(map.get("G"));
        data.setText(d);

//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//        t.start();
    }
}





