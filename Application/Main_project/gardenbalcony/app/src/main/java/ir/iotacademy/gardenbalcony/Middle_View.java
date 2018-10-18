package ir.iotacademy.gardenbalcony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Middle_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle__view);
        ImageButton goLeft,goRight;
        goRight = (ImageButton) findViewById(R.id.go_right2);
        goLeft = (ImageButton) findViewById(R.id.go_left);

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
