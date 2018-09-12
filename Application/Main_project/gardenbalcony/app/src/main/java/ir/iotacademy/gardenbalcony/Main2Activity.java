package ir.iotacademy.gardenbalcony;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private Button signup;
    private Button back_to_signin;
    private TextView email_mkacc;
    private TextView password_mkacc;
    private TextView repassword_mkacc;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        signup= (Button) findViewById(R.id.subtn);
        back_to_signin= (Button) findViewById(R.id.btsibtn);
        email_mkacc= (TextView) findViewById(R.id.em);
        password_mkacc= (TextView) findViewById(R.id.ps);
        repassword_mkacc= (TextView) findViewById(R.id.rps);

        mAuth = FirebaseAuth.getInstance();

        back_to_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignUp();
            }
        });





    }


    private void startSignUp(){
        String email=email_mkacc.getText().toString();
        String password=password_mkacc.getText().toString();
        String repassword=repassword_mkacc.getText().toString();

        if(password.equals(repassword)){

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){

            Toast.makeText(Main2Activity.this,"fields are empty",Toast.LENGTH_LONG).show();
        }
        else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(Main2Activity.this, "sth went wrong!",Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }}else {
            Toast.makeText(Main2Activity.this, "pass and repass aren't equal", Toast.LENGTH_SHORT).show();
        }
        }
    }

