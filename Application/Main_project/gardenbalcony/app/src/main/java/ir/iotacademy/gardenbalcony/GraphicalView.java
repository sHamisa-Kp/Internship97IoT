package ir.iotacademy.gardenbalcony;

import android.app.ActionBar;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class GraphicalView extends AppCompatActivity {
    Button test;
    ImageView onlamp,offlamp,mist;
    ImageButton onswitch,offswitch,mistbtn;
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

        mist= (ImageView) findViewById(R.id.mist);
        mistbtn= (ImageButton) findViewById(R.id.mist_btn);

        mistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mist.getVisibility()==View.INVISIBLE){
                    mist.setVisibility(View.VISIBLE);
                    mist.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mist.getBackground()).start();
                        }
                    });

                }
                else {
                    mist.post(new Runnable() {
                        @Override
                        public void run() {
                            ((AnimationDrawable)mist.getBackground()).stop();
                        }
                    });
                    mist.setVisibility(View.INVISIBLE);

                }
            }
        });



        //test = (Button) findViewById(R.id.button5);
        // Initialize GyroscopeObserver.
        // Set the maximum radian the device should rotate to show image's bounds.
        // It should be set between 0 and π/2.
        // The default value is π/9.

        // Set GyroscopeObserver for PanoramaImageView.
        // panoramaImageView.setGyroscopeObserver(gyroscopeObserver);

// Hide the status bar.
        // int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        // decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        // ActionBar actionBar = getActionBar();
        //actionBar.hide();
        /*test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GraphicalView.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });*/
    }




}