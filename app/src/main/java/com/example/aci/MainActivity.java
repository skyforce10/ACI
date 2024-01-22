package com.example.aci;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private RunTimePermission runTimePermission;
    private EditText inputUsername;
    private TextInputLayout lbl_username;
    private String g_username;
    private Button btn_login;
    private static final int RC_SIGN_IN = 123; // Request code for Google Sign-In
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername=findViewById(R.id.inputUsername);
        btn_login=findViewById(R.id.btn_login);
        lbl_username=findViewById(R.id.lbl_username);

        CreateCopyRightText();
        getpermission();

        g_username=getUsername(this);
        if(g_username.equals("")) {
            g_username = getUserNameFromGoogleAccount();
        }
        inputUsername.setText(g_username);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputUsername.getText().toString().trim().equals("")) {
                    lbl_username.setErrorEnabled(true);
                    lbl_username.setError("Please enter your name");
                }else{
                    lbl_username.setErrorEnabled(false);
                    saveUsername(getApplicationContext(),inputUsername.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
    private void CreateCopyRightText() {
        TextView copyrightTextView = findViewById(R.id.copyrightTextView); // Replace with your actual TextView ID

        // Get the current year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // Set the text for the TextView
        copyrightTextView.setText("\u00a9 " + currentYear + "-" + (currentYear - 1) + " created by ACI");
    }
    private void getpermission() {

        runTimePermission = new RunTimePermission(this);
        runTimePermission.requestPermission(new String[]{
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.BLUETOOTH,
                android.Manifest.permission.BLUETOOTH_ADMIN,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                android.Manifest.permission.INTERNET,
                android.Manifest.permission.ACCESS_WIFI_STATE,
                android.Manifest.permission.ACCESS_NETWORK_STATE,
                android.Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.VIBRATE,
                android.Manifest.permission.INSTALL_SHORTCUT,
                android.Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.CAMERA
        }, new RunTimePermission.RunTimePermissionListener() {

            @Override
            public void permissionGranted() {
                new Thread(() -> {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            }

            @Override
            public void permissionDenied() {

                runTimePermission.finish();
            }
        });
    }
    public static void saveUsername(Context context, String username) {
        SharedPreferences preferences = context.getSharedPreferences("ACI_PREF",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_name", username);
        editor.apply(); // or editor.commit() for immediate application of changes
    }
    public static String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("ACI_PREF",
                Context.MODE_PRIVATE);
        return preferences.getString("user_name", "");
    }
    private String getUserNameFromGoogleAccount(){
        final AccountManager manager = AccountManager.get(this);
        final Account[] accounts = manager.getAccountsByType("com.google");
        final int size = accounts.length;
        String[] names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = accounts[i].name;
        }
        String resultString="";
        if(names.length>0) {
            String username = names[0].replace("@gmail.com", "");
            resultString = username.replaceAll("[^a-zA-Z]", " ");
        }
        return resultString;
    }
}