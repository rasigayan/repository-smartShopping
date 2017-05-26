package com.example.rasi.SmartTshirts;

/**
 * Created by Tharindu on 14-Apr-15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    Button btnLogin,btnRegister,btnHome,btnGst;

    EditText txtUsername, txtPassword;

    MyDBHandler dbHandler;

    // User Session Manager Class
    UserSessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //tbs
        dbHandler = new MyDBHandler(this, null, null, 1);

        // User Session Manager
        session = new UserSessionManager(getApplicationContext());

        // get Email, Password input text
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnRegister = (Button) findViewById(R.id.btnReg);
        btnHome = (Button) findViewById(R.id.btnhme);
        btnGst = (Button) findViewById(R.id.btnGuest);


    Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();


        // User Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);


        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();


                // Validate if username, password is filled
                if(username.trim().length() > 0 && password.trim().length() > 0){

                    //tbs
                    CustomerDetails customerDetails = dbHandler.findCustomer(username,password);
                        // For testing puspose username, password is checked with static data
                        // username = admin
                        // password = admin

                        if (customerDetails != null) {
                            String email = customerDetails.get_email().toString();
                            String un = customerDetails.get_userName().toString();

                            // Creating user login session
                            // Statically storing name="Android Example"
                            // and email="androidexample84@gmail.com"
                            session.createUserLoginSession(un, email);

                            // Starting MainActivity
                            Intent i = new Intent(getApplicationContext(), WelcomeScreen.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                            finish();

                        } else {

                            // username / password doesn't match&
                            Toast.makeText(getApplicationContext(),
                                    "Username/Password is incorrect",
                                    Toast.LENGTH_LONG).show();

                        }


                }else{

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username and password",
                            Toast.LENGTH_LONG).show();

                }

            }
        });


        btnGst.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Get username, password from EditText
                String username = "Guest";
                String password = "Guest";

                    //tbs

                    String email = "Logged as a Guest User";
                    String un = username;

                    // For testing puspose username, password is checked with static data
                    // username = admin
                    // password = admin

                    if(username.equals("Guest") && password.equals("Guest")){

                        // Creating user login session
                        // Statically storing name="Android Example"
                        // and email="androidexample84@gmail.com"
                        session.createUserLoginSession(un,email);

                        // Starting MainActivity
                        Intent i = new Intent(getApplicationContext(), WelcomeScreen.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        finish();

                    }else{

                        // username / password doesn't match&
                        Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();

                    }



            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                startActivity(new Intent(LoginActivity.this, Home.class));
            }
        });
    }

    public void btnRegisterClicked(View view){
        startActivity(new Intent(LoginActivity.this, Registration.class));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, Home.class));
    }
}