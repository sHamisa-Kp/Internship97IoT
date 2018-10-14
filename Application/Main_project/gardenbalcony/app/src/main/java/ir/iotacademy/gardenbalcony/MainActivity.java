package ir.iotacademy.gardenbalcony;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;




public class MainActivity extends AppCompatActivity  {
    boolean x;
    private static final String TAG = "EmailPassword";

    private EditText email_et;
    private  EditText password_et;
    private Button submit_btn;
    private Button go_for_signup;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_et= (EditText) findViewById(R.id.etEmail);
        password_et= (EditText) findViewById(R.id.etPassword);
        submit_btn= (Button) findViewById(R.id.submit);
        go_for_signup= (Button) findViewById(R.id.go_signup);



        mAuth=FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        go_for_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JustValue.class);
                startActivity(intent);

             // startSignUp();
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=true;
                startSignIn();

            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    updateUI(user);

                }/* else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }*/
                // ...
            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);

        }
    }

    private void startSignUp() {

        String email = email_et.getText().toString().trim();
        String password = password_et.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(MainActivity.this, "fields are empty", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "autho failed", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }
    private void startSignIn(){
        String email=email_et.getText().toString().trim();
        String password=password_et.getText().toString().trim();

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this,"fields are empty",Toast.LENGTH_LONG).show();
        }
        else {



            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    x=task.isSuccessful();
                    if (!task.isSuccessful()) {


                       // Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "sign in problem!", Toast.LENGTH_SHORT).show();
                        updateUI(null);

                    }
                    else{
                        Toast.makeText(MainActivity.this, "else", Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    }
                }

            });
        }
    }

   /* private boolean submitForm() throws ParseException, JSONException {
        if (validateUser()&&validateEmail()&&validatePassword()&&validateRepassword()) {
            //Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
            // return false;


            if ((inputPass.getText().toString().equals(inputRepass.getText().toString())) && checkBox.isChecked()) {
                Toast.makeText(MainActivity.this, "SignUp successfully", Toast.LENGTH_SHORT).show();

                ////////////////////  Function to send information to platform ////////////////////////

                String url_user = "http://thingtalk.ir/update?key=48A91L1B0NQRPR3I&field1=" + inputUser.getText();
                try {
                    new HttpPostRequest().execute(url_user).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                String url_pass = "http://thingtalk.ir/update?key=OXFGAZT46L7TY4RP&field1=" + inputPass.getText();
                try {
                    new HttpPostRequest().execute(url_pass).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                String url_repass = "http://thingtalk.ir/update?key=QEE72RTX5W28YGO7&field1=" + inputRepass.getText();
                try {
                    new HttpPostRequest().execute(url_repass).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                String url_email = "http://thingtalk.ir/update?key=X1I8YN8DQ696PUB4&field1=" + inputEmail.getText();
                try {
                    new HttpPostRequest().execute(url_email).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                Toast.makeText(this, "Please confirm and check again your information", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else{
                Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
            return false;
            }

    }

    private boolean check_beUnique(String input) throws ParseException, JSONException {
        ArrayList usrs=new ArrayList<String>();
        ArrayList emls=new ArrayList<String>();
        int same;
        switch (input){
            case "username":
                usrs=data.get_data("username",null);
                same=0;
                for(int i=0;i<usrs.size();i++){
                    if(usrs.get(i).equals(inputUser.getText()))
                        same++;
                }
                return same == 0;

            case "email":
                emls=data.get_data("email",null);
                same=0;
                for(int i=0;i<usrs.size();i++){
                    if(emls.get(i).equals(inputEmail.getText()))
                        same++;
                }
                return same == 0;
            default:return  false;

        }
    }

////////////////////////// methods/////////////////////////////////////

    private boolean validateUser() throws ParseException, JSONException {
        if (inputUser.getText().toString().trim().equals("")|| !check_beUnique("username")) {
            requestFocus(inputUser);
            return false;
        } else {

        }
        return true;
    }

    private boolean validateEmail() throws ParseException, JSONException {
        String email = inputEmail.getText().toString().trim();
        if (email.equals("") || !isValidEmail(email) || !check_beUnique("email")) {
            requestFocus(inputEmail);
            return false;
        } else {

        }
        return true;
    }

    private boolean validatePassword() {
        if (inputPass.getText().toString().trim().equals("")) {
            requestFocus(inputPass);
            return false;
        } else {

        }
        return true;
    }

    private boolean validateRepassword() {
        if (inputRepass.getText().toString().trim().equals("")) {
            requestFocus(inputRepass);
            return false;
        } else {

        }
        return true;
    }

    private static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;
        private MyTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
          /*  switch (view.getId()){
                case R.id.et_username :
                    try {
                        validateUser();
                    } catch (ParseException | JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.et_email :
                    try {
                        validateEmail();
                    } catch (ParseException | JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.et_pass :
                    validatePassword();
                    break;

                case R.id.et_repass :
                    validateRepassword();
                    break;

            }
        }
    } here*/
}
