package com.example.umarramadhana.atp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.umarramadhana.atp.network.AccountClient;

import java.util.ArrayList;

public class Histroypayment extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> arr = new ArrayList<>();
    private AccountClient accountClient = new AccountClient();
    private String nohp;
    private AutoCompleteTextView suggetion_box1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histroypayment);

        inisialisasi();
        if (getIntent().getStringExtra("nomorhp") != null) {
            nohp = getIntent().getStringExtra("nomorhp");
        }
        accountClient.getDataTransaksi(getApplicationContext(), listview, nohp, suggetion_box1);
        for (int i = 0; i < listview.getCount(); i++){
            arr.add(listview.getAdapter().getItem(i).toString());
        }
    }

    private void inisialisasi()
    {
        listview = (ListView) findViewById(R.id.listView);
        suggetion_box1 = (AutoCompleteTextView) findViewById(R.id.edtsearch);

        suggetion_box1.setTextColor(Color.WHITE);
        suggetion_box1.setHintTextColor(Color.WHITE);
        suggetion_box1.setDropDownBackgroundResource(R.drawable.buatkiki1);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent sendData = new Intent(Histroypayment.this, Detiltransaksi.class);
                sendData.putExtra("MyTrans", listview.getItemAtPosition(i).toString().trim());
                startActivity(sendData);
            }
        });

        suggetion_box1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent sendData = new Intent(Histroypayment.this, Detiltransaksi.class);
                sendData.putExtra("MyTrans", listview.getItemAtPosition(i).toString().trim());
                startActivity(sendData);
            }
        });
    }
}
