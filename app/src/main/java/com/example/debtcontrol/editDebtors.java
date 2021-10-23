package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class editDebtors extends AppCompatActivity {
    Button Main; TextView DataView; DatabaseHelper DB;
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_debtors);

        Main = findViewById(R.id.main);
        DataView = findViewById(R.id.DataView);
        Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editDebtors.this, Home.class);
                startActivity(intent);
            }
        });
        //get the object of the Database helper
        DB = new DatabaseHelper(this);
        StringBuffer buffer = new StringBuffer();
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        String phoneNumber = prf.getString("phoneNumber",null);

        Cursor checkData = DB.getdata(phoneNumber);
        if(checkData!= null && checkData.getCount() >0) {
            String DataId = checkData.getString(0);
            String DebtName = checkData.getString(2);
            String ListOfDebt = checkData.getString(3);
            String TotalDebt = checkData.getString(4);
            //Code to load content to buffer

            buffer.append("Name:" +DebtName +"\n");
            buffer.append("List:" +ListOfDebt +"\n");
            buffer.append("Total:" +TotalDebt +"\n\n");
//            buffer.append("Edit:" +TotalDebt +"\n\n");

            DataView.setText(buffer);

        }



    }
}