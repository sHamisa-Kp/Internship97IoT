package ir.iotacademy.gardenbalcony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GraphicalViewRight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_view_right);
        ImageButton goMidd = (ImageButton) findViewById(R.id.go_middle_from_right);
        goMidd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraphicalViewRight.this, Middle_View.class);
                startActivity(intent);
            }
        });
    }
}
