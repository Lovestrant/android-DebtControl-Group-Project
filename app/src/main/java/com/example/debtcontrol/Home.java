package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    Button addDebtors, EditDebtors,logout; TextView displaySessionvar; SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addDebtors = findViewById(R.id.addDebtor);
        EditDebtors = findViewById(R.id.editDebtors);
        logout = findViewById(R.id.logout);
        displaySessionvar = findViewById(R.id.DisplaySession);
//
//        SessionManagement sessionManagement = new SessionManagement(Home.this);
//        String sellerPhone = sessionManagement.getSession();
        prf = getSharedPreferences("user_details",MODE_PRIVATE);

        displaySessionvar.setText(" Hello, Mr/mrs: "+prf.getString("phoneNumber",null)+
                "\n You can log out below:");

        addDebtors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, debtorsAdd.class);
                startActivity(intent);
            }

        });

        EditDebtors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, editDebtors.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                //Direct To Main Activity

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Log out success", Toast.LENGTH_SHORT).show();
            }
        });



    }
}