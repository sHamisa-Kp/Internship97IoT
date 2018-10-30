package ir.iotacademy.gardenbalcony;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class GraphicalViewRight extends AppCompatActivity {
    Button test;
    ImageView onlamp,offlamp,mist,thirsty0,thirsty1,thirsty2,thirsty3,thirsty8,thirsty9,thirsty10,thirsty11,thirsty12,wf1,wf2,wf3,wf4,wf5,wv1,wv2,wv3,wv4,puddle,motion;
    ImageButton onswitch,offswitch,mistbtn,mistbtn2,go_to_the_right_position,plant;
    ConstraintLayout background;
    TextView data;
    String d,d1;


    String preUrl="http://thingtalk.ir/channels/";
    String preip="http://10.1.248.34:5050/actuators/";
    Map<String,String> map=new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_view_right);
        ImageButton goMidd = (ImageButton) findViewById(R.id.go_middle_from_right);

        //weather
        map.put("T0", preUrl + "720/feed.json?key=6P4WUZHZZDR6U0TX&results=1");
        map.put("H0", preUrl + "721/feed.json?key=YV8JRH910ZJZQN1I&results=1");
        //vegetables
        map.put("SM0", preUrl + "703/feed.json?key=6W8DYBXG0HDA141O&results=1");
        map.put("SM1", preUrl + "704/feed.json?key=HZ9VM1PH1Q6LQSLZ&results=1");
        map.put("SM2", preUrl + "705/feed.json?key=RUB06UUHPX0K4DDS&results=1");
        map.put("SM3", preUrl + "706/feed.json?key=WQFB2JIGRVXDIAR4&results=1");
        //flowers
        map.put("SM8", preUrl + "715/feed.json?key=3DMD11SS9G5B94I0&results=1");
        map.put("SM9", preUrl + "716/feed.json?key=QLH9NDZ20RQNK96S&results=1");
        map.put("SM10", preUrl + "717/feed.json?key=BVAC00J644INRNG7&results=1");
        map.put("SM11", preUrl + "718/feed.json?key=T42SU8NKLQ4KYF5E&results=1");
        map.put("SM12", preUrl + "719/feed.json?key=OTSO6GP0GO9XUAU3&results=1");
        //photo resistor
        map.put("PR0", preUrl + "743/feed.json?key=ZH7OQMKALAPRZXQJ&results=1");
        //floor humidity
        map.put("FH0", preUrl + "723/feed.json?key=IG7Z0OW1NR2LVGSW&results=1");
        //pump status
        map.put("PSV", preUrl + "671/feed.json?key=XAKAVEUUJQ9GZGMT&results=1");
        map.put("PSF", preUrl + "744/feed.json?key=PD74MGJ9RFR4YMHK&results=1");
        map.put("PST", preUrl + "750/feed.json?key=FGGX36CLKXBW2BN4&results=1");
        //light bulb status
        map.put("LBS", preUrl + "746/feed.json?key=17J1AKN992YL3HUX&results=1");
        //gas sensor
        map.put("G", preUrl + "751/feed.json?key=05VOPP6KT2NA1AZ5&results=1");
        //tap status
        map.put("TS", preUrl + "748/feed.json?key=A1079N4WNZESIRFC&results=1");
        //motion detector
        map.put("MD", preUrl + "752/feed.json?key=ZCLT56CFVCG7DU50&results=1");
        //watt meter
        map.put("WM", preUrl + "753/feed.json?key=OUAV3VIB076Y5UO0&results=1");
        //water level
        map.put("WL", preUrl + "742/feed.json?key=WGWJ660WN7V9394D&results=1");


        goMidd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraphicalViewRight.this, Middle_View.class);
                startActivity(intent);
            }
        });

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(6000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


        //Temperature
        data = (TextView) findViewById(R.id.temperature);
        GetSendData t0 = new GetSendData();
        data.setText(t0.GetData(map.get("T0")) + "°C");


        //Humidity
        data = (TextView) findViewById(R.id.humidity);
        GetSendData h0 = new GetSendData();
        data.setText(h0.GetData(map.get("H0")) + "%");


        //Vegetables
        data = (TextView) findViewById(R.id.Righttextveg1);
        GetSendData sm0 = new GetSendData();
        d = sm0.GetData(map.get("SM0"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightVegtable1);
        plant.setVisibility(View.VISIBLE);
        thirsty0 = (ImageView) findViewById(R.id.Rightthirstyveg1);
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
            plant = (ImageButton) findViewById(R.id.RightVegtable1);
            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.Righttextveg2);
        GetSendData sm1 = new GetSendData();
        d = sm1.GetData(map.get("SM1"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightVegtable2);
        plant.setVisibility(View.VISIBLE);
        thirsty1 = (ImageView) findViewById(R.id.Rightthirstyveg2);
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
            plant = (ImageButton) findViewById(R.id.RightVegtable2);
            plant.setVisibility(View.INVISIBLE);
        }

        data = (TextView) findViewById(R.id.Righttextveg3);
        GetSendData sm2 = new GetSendData();
        d = sm2.GetData(map.get("SM2"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightVegtable3);
        plant.setVisibility(View.VISIBLE);
        thirsty2 = (ImageView) findViewById(R.id.Rightthirstyveg3);
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

        data = (TextView) findViewById(R.id.Righttextveg4);
        GetSendData sm3 = new GetSendData();
        d = sm3.GetData(map.get("SM3"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightVegtable4);
        plant.setVisibility(View.VISIBLE);
        thirsty3 = (ImageView) findViewById(R.id.Rightthirstyveg4);
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


        data = (TextView) findViewById(R.id.RightTextFlower1);
        GetSendData sm8 = new GetSendData();
        d = sm8.GetData(map.get("SM8"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightFlower1);
        plant.setVisibility(View.VISIBLE);
        thirsty8 = (ImageView) findViewById(R.id.Rightthirstyflower1);
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

        data = (TextView) findViewById(R.id.RightTextFlower2);
        GetSendData sm9 = new GetSendData();
        d = sm9.GetData(map.get("SM9"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightFlower2);
        plant.setVisibility(View.VISIBLE);
        thirsty9 = (ImageView) findViewById(R.id.Rightthirstyflower2);
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

        data = (TextView) findViewById(R.id.RightTextFlower3);
        GetSendData sm10 = new GetSendData();
        d = sm10.GetData(map.get("SM10"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightFlower3);
        plant.setVisibility(View.VISIBLE);
        thirsty10 = (ImageView) findViewById(R.id.Rightthirstyflower3);
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

        data = (TextView) findViewById(R.id.RightTextFlower4);
        GetSendData sm11 = new GetSendData();
        d = sm11.GetData(map.get("SM11"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightFlower4);
        plant.setVisibility(View.VISIBLE);
        thirsty11 = (ImageView) findViewById(R.id.Rightthirstyflower4);
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

        data = (TextView) findViewById(R.id.RightTextFlower5);
        GetSendData sm12 = new GetSendData();
        d = sm12.GetData(map.get("SM12"));
        data.setText(d + "%");
        plant = (ImageButton) findViewById(R.id.RightFlower5);
        plant.setVisibility(View.VISIBLE);
        thirsty12 = (ImageView) findViewById(R.id.Rightthirstyflower5);
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
        data = (TextView) findViewById(R.id.Righttextbrightness);
        GetSendData pr = new GetSendData();
        d = pr.GetData(map.get("PR0"));
        data.setText(d);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        background = (ConstraintLayout) findViewById(R.id.Rightbackgounrd);
//
//        if (timeOfDay >= 17 || timeOfDay < 6 && Integer.parseInt(d) < 300) {
//            background.setBackgroundResource(R.drawable.night_right);
//        }
//         else if (timeOfDay < 17 && timeOfDay >= 6 && Integer.parseInt(d) > 300) {
//             background.setBackgroundResource(R.drawable.cloudy_right);
//        }
//        else if (timeOfDay < 17 && timeOfDay >= 6 && Integer.parseInt(d) < 300) {
//              background.setBackgroundResource(R.drawable.cloudy_right);
//        }
//         else {
//            background.setBackgroundResource(R.drawable.rightbalcony);
//        }

        //watering
        GetSendData ts = new GetSendData();
        d1 = pr.GetData(map.get("WL"));

        //flower
        GetSendData psf = new GetSendData();
        d = pr.GetData(map.get("PSF"));


        wf1 = (ImageView) findViewById(R.id.Rightwaterf1);
        wf2 = (ImageView) findViewById(R.id.Rightwaterf2);
        wf3 = (ImageView) findViewById(R.id.Rightwaterf3);
        wf4 = (ImageView) findViewById(R.id.Rightwaterf4);
        wf5 = (ImageView) findViewById(R.id.Rightwaterf5);
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


        wv1 = (ImageView) findViewById(R.id.Rightwaterv1);
        wv2 = (ImageView) findViewById(R.id.Rightwaterv2);
        wv3 = (ImageView) findViewById(R.id.Rightwaterv3);
        wv4 = (ImageView) findViewById(R.id.Rightwaterv4);

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
        data = (TextView) findViewById(R.id.Righttextfloormoisture);
        GetSendData fh = new GetSendData();
        d = fh.GetData(map.get("FH0"));
        data.setText("");

        puddle= (ImageView) findViewById(R.id.Rightpuddlewater);

        if(Integer.parseInt(d)==1){
            puddle.setVisibility(View.VISIBLE);
        }
        else
            puddle.setVisibility(View.INVISIBLE);



        //top pump status



        mistbtn = (ImageButton) findViewById(R.id.Rightmist_btn);

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

        mistbtn2 = (ImageButton) findViewById(R.id.Rightmist_btn2);

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




        onlamp = (ImageView) findViewById(R.id.Rightonlamp);
        offlamp = (ImageView) findViewById(R.id.Rightofflamp);
        onswitch = (ImageButton) findViewById(R.id.Righton_switch);
        offswitch = (ImageButton) findViewById(R.id.Rightoff_switch);


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

        motion= (ImageView) findViewById(R.id.Rightonmotion);
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


    }
});
        }
        } catch (InterruptedException e) {
        }
        }
        };
        t.start();


    }
}
