package com.example.umarramadhana.atp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.umarramadhana.atp.network.AccountClient;
import com.example.umarramadhana.atp.pojo.Account;
import com.example.umarramadhana.atp.pojo.AccountAPI;
import com.example.umarramadhana.atp.session.SessionManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private Button btnsignup, btnlogin;
    private AccountClient accountClient = new AccountClient();
    private EditText txtUsername, txtPassword;
//    private final String BASE_URL = "http://192.168.0.15";
    private final String BASE_URL = "http://192.168.43.46";
    private SessionManager session;
    private String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();

        session = new SessionManager(getApplicationContext());

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Silahkan Isi Data Account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password;

                username = txtUsername.getText().toString().trim();
                password = txtPassword.getText().toString().trim();

                if(!username.isEmpty() && !password.isEmpty()){
                    accountClient.login(username, password);
                    if (accountClient.isValidLogin()){
                        session.createLoginSession(accountClient.getMyAccount().getName(), accountClient.getMyAccount().getEmail());
                        Intent i = new Intent(getApplicationContext(), MainMenu.class);
                        i.putExtra("namauser", txtUsername.getText().toString());
                        startActivity(i);
                        finish();
                        Toast.makeText(Login.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Login.this, "Username dan password tidak cocok!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private AccountAPI getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final AccountAPI mInterfaceService = retrofit.create(AccountAPI.class);
        return mInterfaceService;
    }

    public void initialize(){
        btnsignup = (Button) findViewById(R.id.btnDAFTAR);
        btnlogin = (Button) findViewById(R.id.btnLOGIN);
        txtUsername =(EditText) findViewById(R.id.username);
        txtPassword =(EditText)findViewById(R.id.paswd);
    }
}
