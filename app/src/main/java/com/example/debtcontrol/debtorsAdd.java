package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class debtorsAdd extends AppCompatActivity {
    Button backMain,sumAll; EditText name,list, Total; DatabaseHelper DB;
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtors_add);

        backMain = findViewById(R.id.main);
        sumAll = findViewById(R.id.add);
        Total = findViewById(R.id.total);
        list = findViewById(R.id.List);
        name = findViewById(R.id.debtorName);
        DB = new DatabaseHelper(this);

        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });


        sumAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String SumOfItems = Total.getText().toString();
                String ItemList = list.getText().toString();
               // int id = Integer.parseInt(null);

                //Get session variable PhoneNumber
                prf = getSharedPreferences("user_details",MODE_PRIVATE);
                String sellerPhone =  prf.getString("phoneNumber",null);


                if(Name == null || SumOfItems == null || ItemList == null) {
                    Toast.makeText(getApplicationContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkInsertedData = DB.insertUserData(sellerPhone, Name, ItemList, SumOfItems);
                    if(checkInsertedData == true) {
                        Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), " Insertion Failed...", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

    }
}