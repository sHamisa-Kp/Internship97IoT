package ir.iotacademy.gardenbalcony;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;




public class MainActivity extends AppCompatActivity {
//
//    Button login ;
//    CheckBox checkBox ;
//    EditText inputPass ;
//    EditText inputRepass ;
//    EditText inputUser ;
//    EditText inputEmail ;
//    Button signup ;
//    Data data=new Data();


  //  @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        checkBox = (CheckBox) findViewById(R.id.checkbox1) ;
//
//        login = (Button) findViewById(R.id.btn_login) ;
//        signup =  (Button) findViewById(R.id.btn_signUp);
//
//        inputUser = (EditText) findViewById(R.id.et_username);
//        inputEmail = (EditText) findViewById(R.id.et_email);
//        inputPass = (EditText) findViewById(R.id.et_pass) ;
//        inputRepass = (EditText) findViewById(R.id.et_repass);
//
//        inputUser.addTextChangedListener(new MyTextWatcher(inputUser));
//        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
//        inputPass.addTextChangedListener(new MyTextWatcher(inputPass));
//        inputRepass.addTextChangedListener(new MyTextWatcher(inputRepass));
//        //Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
//
//        //Go to login screen from sign up screen
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent1);
//            }
//        });
//
//        signup.setOnClickListener(new View.OnClickListener() {
//            boolean tf;
//            @Override
//            public void onClick(View view) {
//                try {
//                    tf=submitForm();
//                } catch (ParseException | JSONException e) {
//                    e.printStackTrace();
//                }
//                if(tf){
//                    Intent intent2 = new Intent(MainActivity.this, Home.class) ;
//                    startActivity(intent2);
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//
//        });
        //
    }
//
//
//    private boolean submitForm() throws ParseException, JSONException {
//        if (validateUser()&&validateEmail()&&validatePassword()&&validateRepassword()) {
//            //Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
//            // return false;
//
//
//            if ((inputPass.getText().toString().equals(inputRepass.getText().toString())) && checkBox.isChecked()) {
//                Toast.makeText(MainActivity.this, "SignUp successfully", Toast.LENGTH_SHORT).show();
//
//                ////////////////////  Function to send information to platform ////////////////////////
//
//                String url_user = "http://thingtalk.ir/update?key=48A91L1B0NQRPR3I&field1=" + inputUser.getText();
//                try {
//                    new HttpPostRequest().execute(url_user).get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//                String url_pass = "http://thingtalk.ir/update?key=OXFGAZT46L7TY4RP&field1=" + inputPass.getText();
//                try {
//                    new HttpPostRequest().execute(url_pass).get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//                String url_repass = "http://thingtalk.ir/update?key=QEE72RTX5W28YGO7&field1=" + inputRepass.getText();
//                try {
//                    new HttpPostRequest().execute(url_repass).get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//                String url_email = "http://thingtalk.ir/update?key=X1I8YN8DQ696PUB4&field1=" + inputEmail.getText();
//                try {
//                    new HttpPostRequest().execute(url_email).get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//                return true;
//            } else {
//                Toast.makeText(this, "Please confirm and check again your information", Toast.LENGTH_LONG).show();
//                return false;
//            }
//        }
//        else{
//                Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
//            return false;
//            }
//
//    }
//
//    private boolean check_beUnique(String input) throws ParseException, JSONException {
//        ArrayList usrs=new ArrayList<String>();
//        ArrayList emls=new ArrayList<String>();
//        int same;
//        switch (input){
//            case "username":
//                usrs=data.get_data("username",null);
//                same=0;
//                for(int i=0;i<usrs.size();i++){
//                    if(usrs.get(i).equals(inputUser.getText()))
//                        same++;
//                }
//                return same == 0;
//
//            case "email":
//                emls=data.get_data("email",null);
//                same=0;
//                for(int i=0;i<usrs.size();i++){
//                    if(emls.get(i).equals(inputEmail.getText()))
//                        same++;
//                }
//                return same == 0;
//            default:return  false;
//
//        }
//    }
//
//////////////////////////// methods/////////////////////////////////////
//
//    private boolean validateUser() throws ParseException, JSONException {
//        if (inputUser.getText().toString().trim().equals("")|| !check_beUnique("username")) {
//            requestFocus(inputUser);
//            return false;
//        } else {
//
//        }
//        return true;
//    }
//
//    private boolean validateEmail() throws ParseException, JSONException {
//        String email = inputEmail.getText().toString().trim();
//        if (email.equals("") || !isValidEmail(email) || !check_beUnique("email")) {
//            requestFocus(inputEmail);
//            return false;
//        } else {
//
//        }
//        return true;
//    }
//
//    private boolean validatePassword() {
//        if (inputPass.getText().toString().trim().equals("")) {
//            requestFocus(inputPass);
//            return false;
//        } else {
//
//        }
//        return true;
//    }
//
//    private boolean validateRepassword() {
//        if (inputRepass.getText().toString().trim().equals("")) {
//            requestFocus(inputRepass);
//            return false;
//        } else {
//
//        }
//        return true;
//    }
//
//    private static boolean isValidEmail(String email){
//        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }
//
//    private void requestFocus(View view){
//        if (view.requestFocus()){
//            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        }
//    }
//
//    private class MyTextWatcher implements TextWatcher {
//
//        private View view;
//        private MyTextWatcher(View view){
//            this.view = view;
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//          /*  switch (view.getId()){
//                case R.id.et_username :
//                    try {
//                        validateUser();
//                    } catch (ParseException | JSONException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//
//                case R.id.et_email :
//                    try {
//                        validateEmail();
//                    } catch (ParseException | JSONException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//
//                case R.id.et_pass :
//                    validatePassword();
//                    break;
//
//                case R.id.et_repass :
//                    validateRepassword();
//                    break;
//
//            }*/
//        }
//    }
}
