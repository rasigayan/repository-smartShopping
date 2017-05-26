package com.example.rasi.SmartTshirts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Registration extends Activity {

    private static final String TAG = "tbsMessage";

    EditText un,pw,email,mb;
    TextView txtVwUsers;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.i(TAG,"On create");

        un = (EditText) findViewById(R.id.txtUname);
        pw = (EditText) findViewById(R.id.txtpw);
        email = (EditText) findViewById(R.id.txtEmail);
        mb = (EditText) findViewById(R.id.txtmb);
        txtVwUsers = (TextView) findViewById(R.id.txtUsers);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    public void saveButtonClicked(View view){

        if(un.getText().toString().trim().length() > 0 && pw.getText().toString().trim().length() > 0 && email.getText().toString().trim().length() > 0 && mb.getText().toString().trim().length() > 0) {
            Log.i(TAG, "Save button clicked");
            CustomerDetails customerDetails = new CustomerDetails(un.getText().toString(), pw.getText().toString(), email.getText().toString(), mb.getText().toString());
            dbHandler.addCustomer(customerDetails);
            Toast.makeText(getApplicationContext(), "User Account Added Successfully", Toast.LENGTH_LONG).show();
            printDatabase();
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Please fill all the details",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void deleteButtonClicked(View view) {
        String userName = un.getText().toString();
        String pww = pw.getText().toString();
        if(userName.trim().length() > 0 && pww.trim().length() > 0) {

           CustomerDetails customerDetails = dbHandler.findCustomer(userName,pww);
            if (customerDetails != null) {

                dbHandler.deleteCustomer(userName,pww);
                Toast.makeText(getApplicationContext(), "User Account Deleted", Toast.LENGTH_SHORT).show();
                printDatabase();
            }
            else {
                Toast.makeText(getApplicationContext(), "User Account Not Found", Toast.LENGTH_SHORT).show();
                printDatabase();
            }


        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Please enter user name & password to delete account",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void btnSchClicked(View view){
        String userName = un.getText().toString();
        String pww = pw.getText().toString();

        if(userName.trim().length() > 0 && pww.trim().length() > 0) {
            CustomerDetails customerDetails = dbHandler.findCustomer(userName,pww);
            if (customerDetails != null) {
                String output = dbHandler.databaseToString(userName,pww);
                txtVwUsers.setText(output);
                Toast.makeText(getApplicationContext(), "User Account found", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "User Account not found", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Please enter user name & password to search",
                    Toast.LENGTH_LONG).show();
        }

    }


    public void printDatabase() {
        String userName = un.getText().toString();
        String pww = pw.getText().toString();
        String output;
        output = dbHandler.databaseToString(userName,pww);
        txtVwUsers.setText(output);
        un.setText("");
        pw.setText("");
        email.setText("");
        mb.setText("");

    }

    public void logInBtnClicked(View view){
        Log.i(TAG,"Loginwindow button clicked");
        startActivity(new Intent(Registration.this, LoginActivity.class));

    }



}
