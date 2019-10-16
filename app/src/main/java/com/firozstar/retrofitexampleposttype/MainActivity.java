package com.firozstar.retrofitexampleposttype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SignUpResponse signUpResponseData;
    EditText emailId, password, name;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init the EditText and Button
        name = findViewById(R.id.username);
        password = findViewById(R.id.password);
        emailId = findViewById(R.id.email);
        signUp = findViewById(R.id.signUp);

        // implement setOnClickListener event on sign up Button
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validate the fields and call sign method to implement the api
                if (validate(name) && validateEmail() && validate(password)){
                    signUp();
                }
            }
        });
    }

    private boolean validateEmail(){
        String email = emailId.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)){
            emailId.setError("Email is not valid.");
            emailId.requestFocus();
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate(EditText editText){

        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0){
            return true; // returns true if field is not empty
        }

        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    private void signUp(){
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        // enqueue is used for callback response and error
        (Api.getClient().registration(name.getText().toString().trim(),
                emailId.getText().toString().trim(),
                password.getText().toString().trim(),"email")).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpResponseData = response.body();
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("responce", t.getStackTrace().toString().trim());
                progressDialog.dismiss();
            }
        });
    }
}
