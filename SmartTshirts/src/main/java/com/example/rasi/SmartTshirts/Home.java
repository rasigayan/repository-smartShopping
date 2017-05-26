package com.example.rasi.SmartTshirts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;


public class Home extends Activity {

    Button btnsgn,btncart,btnshop,btncontactUs,btnSearch,btnFeed;
    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new UserSessionManager(getApplicationContext());

        btnsgn = (Button) findViewById(R.id.btnLoginMain);
        btncart = (Button) findViewById(R.id.btnCart);
        btnshop = (Button) findViewById(R.id.btnShop);
        btncontactUs = (Button) findViewById(R.id.btnContactUs);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnFeed = (Button) findViewById(R.id.btnFeed);



        if(session.isUserLoggedIn()){

            if(session.checkLogin())
                finish();

            // get user data from session
            HashMap<String, String> user = session.getUserDetails();

            // get name
            String name = user.get(UserSessionManager.KEY_NAME);

            btnsgn.setText(name);

        }
        else {

            btnsgn.setBackgroundResource(R.drawable.signinbutton);
        }


        btnsgn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, WelcomeScreen.class));
                    }
                }
        );

        btncart.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, ShoppingCartActivity.class));
                    }
                }
        );

        btnshop.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, CatalogActivity.class));
                    }
                }
        );

        btncontactUs.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, Contact_Us.class));
                    }
                }
        );

        btnSearch.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, Search.class));
                    }
                }
        );

        btnFeed.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, FeedBack.class));
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
