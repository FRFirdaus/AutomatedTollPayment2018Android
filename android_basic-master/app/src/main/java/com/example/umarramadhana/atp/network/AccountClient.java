package com.example.umarramadhana.atp.network;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.umarramadhana.atp.R;
import com.example.umarramadhana.atp.pojo.Account;
import com.example.umarramadhana.atp.pojo.AccountAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountClient {
//    private final String BASE_URL = "http://192.168.0.15";
    private final String BASE_URL = "http://192.168.43.46";
    private Retrofit retrofit;
    private AccountAPI accountAPI;
    private Account myAccount;
    private boolean isValidLogin;

    public AccountClient() {
        getAPIClient();
    }

    public AccountAPI getAPIClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        accountAPI = retrofit.create(AccountAPI.class);
        return accountAPI;
    }

    public void addAccount(Account account) {
        Call<Account> call = accountAPI.addAccount(account.getName(), account.getEmail(), account.getNoktp(), account.getAddress(),
                account.getNohp(), account.getUsername(), account.getPassword());
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addSaldo(String nohp, Integer jumlah, String lokasi) {
        Call<Account> call = accountAPI.addSaldo(nohp, jumlah, lokasi);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void topup(String nohp, Integer jumlah, String lokasi) {
        Call<Account> call = accountAPI.topup(nohp, jumlah, lokasi);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void getDataTransaksi(final Context context, final ListView listView, final String nohp, final AutoCompleteTextView suggetion_box1) {
        Call<List<Account>> call = accountAPI.getTransaksi();
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                ArrayAdapter<String> adapter;
                ArrayList<String> listv = new ArrayList<>();

                for (int i = 0; i < response.body().size(); i++) {
                    if (response.body().get(i).getNohp().equals(nohp)) {
                        listv.add(response.body().get(i).getDate());
                    }
                }

                adapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, listv);
                listView.setAdapter(adapter);
                suggetion_box1.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
            }
        });
    }

    public void login(String username, String password){
        Call<Account> call = accountAPI.login(username, password);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                isValidLogin = true;
                myAccount =  response.body();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                isValidLogin = false;
            }
        });
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public boolean isValidLogin() {
        return isValidLogin;
    }

    public AccountAPI getAccountAPI() {
        return accountAPI;
    }
}
