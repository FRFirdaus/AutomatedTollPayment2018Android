package com.example.umarramadhana.atp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umarramadhana.atp.network.AccountClient;
import com.example.umarramadhana.atp.pojo.Account;
import com.example.umarramadhana.atp.pojo.AccountAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detiltransaksi extends AppCompatActivity {
    private String DetilTanggal;
    private TextView txtnohp, txtgate, txttanggal, txtharga;
    private AccountClient accountClient = new AccountClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detiltransaksi);


        inisialisasi();

        if (getIntent().getStringExtra("MyTrans") != null) {
            DetilTanggal = getIntent().getStringExtra("MyTrans");
        }

        setDataTrans(DetilTanggal);
    }

    private void setDataTrans(final String tanggal) {
        AccountAPI accountAPI = accountClient.getAccountAPI();
        Call<Account> call = accountAPI.gettransdetil(tanggal);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                txtnohp.setText(response.body().getNohp());
                txtgate.setText(response.body().getGatename());
                txttanggal.setText(response.body().getDate());
                txtharga.setText(response.body().getHarga().toString());

                Toast.makeText(getApplicationContext(), "'" + response.body().getNohp() + "'"  + " Detail Transaksi" + " pada " + response.body().getDate(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(Detiltransaksi.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inisialisasi()
    {
        txtnohp = (TextView) findViewById(R.id.txt_nohp);
        txtgate = (TextView) findViewById(R.id.txt_gate);
        txttanggal = (TextView) findViewById(R.id.txt_tanggaltrans);
        txtharga = (TextView) findViewById(R.id.txt_harga);
    }
}
