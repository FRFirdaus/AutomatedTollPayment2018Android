package com.example.umarramadhana.atp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umarramadhana.atp.network.AccountClient;
import com.example.umarramadhana.atp.pojo.Account;
import com.example.umarramadhana.atp.pojo.AccountAPI;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenu extends AppCompatActivity {
    private Button  btnHISTORY, btnrefresh;
    private TextView txtharga, txtnohp, txtnoktp, txtemail, txtsaldopay, txtnama, txtsaldoprov, txtgate;
    private AccountClient accountClient = new AccountClient();
    private String username, nohp, jumlah;
    private LinearLayout lay_nama, lay_email, lay_nohp, lay_noktp;
    private Context context = this;
    private MediaPlayer mediaPlayer, pulsa;
//    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initiallayout();
        initialbutton();
        mediaPlayer = MediaPlayer.create(this, R.raw.saldo);
        pulsa = MediaPlayer.create(this, R.raw.pulsa);//inisialisasi music/sound
        if (getIntent().getStringExtra("namauser") != null) {
            username = getIntent().getStringExtra("namauser");
        }


        setDataMember(username);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setDataMember(username);
                        setdatagate(txtgate.getText().toString());
                    }
                });
            }
            // perulangan dilakukan dengan jeda 1 detik sekali
        }, 500, 500);

//        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);



        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((Integer.valueOf(txtsaldopay.getText().toString()) - Integer.valueOf(txtharga.getText().toString()) < 0 ) && ((Integer.valueOf(txtsaldoprov.getText().toString()) - Integer.valueOf(txtharga.getText().toString()) < 0))) {
//                                new Timer().scheduleAtFixedRate(new TimerTask() {
//                                    @Override
//                                    public void run() {
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
                                                mediaPlayer.start();
//                                            }
//
//                                        });
//                                    }
//                                    // perulangan dilakukan dengan jeda 1 detik sekali
//                                }, 500, 500);

                            } else if ((Integer.valueOf(txtsaldopay.getText().toString()) - Integer.valueOf(txtharga.getText().toString()) < 0 ) && ((Integer.valueOf(txtsaldoprov.getText().toString()) - Integer.valueOf(txtharga.getText().toString()) >= 0))){
                                    pulsa.start();
                            }
                        }
                });
            }
            // perulangan dilakukan dengan jeda 1 detik sekali
        }, 500, 500);

//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (Integer.valueOf(txtsaldopay.getText().toString()) - Integer.valueOf(txtharga.getText().toString()) >= 0) {
//                                new Timer().scheduleAtFixedRate(new TimerTask() {
//                                    @Override
//                                    public void run() {
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                mediaPlayer.stop();
//                                            }
//
//                                        });
//                                    }
//                                    // perulangan dilakukan dengan jeda 1 detik sekali
//                                }, 500, 500);
//                        } else if (Integer.valueOf(txtsaldoprov.getText().toString()) - Integer.valueOf(txtharga.getText().toString()) >= 0) {
//                            new Timer().scheduleAtFixedRate(new TimerTask() {
//                                @Override
//                                public void run() {
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            mediaPlayer.stop();
//                                        }
//
//                                    });
//                                }
//                                // perulangan dilakukan dengan jeda 1 detik sekali
//                            }, 500, 500);
//                        }
//
//                        }
//                });
//            }
//            // perulangan dilakukan dengan jeda 1 detik sekali
//        }, 500, 500);

    }

    private void setDataMember(final String username) {
        AccountAPI accountAPI = accountClient.getAccountAPI();
        Call<Account> call = accountAPI.getuserdetil(username);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                txtnama.setText(response.body().getName());
                txtnohp.setText(response.body().getNohp());
                txtnoktp.setText(response.body().getNoktp());
                txtemail.setText(response.body().getEmail());
                txtsaldopay.setText(response.body().getSaldo().toString());
                txtgate.setText(response.body().getLokasi());
                setdataprovider(response.body().getNohp());

            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
//                Toast.makeText(MainMenu.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setdatagate(final String gatename) {
        AccountAPI accountAPI = accountClient.getAccountAPI();
        Call<Account> call = accountAPI.gethargagate(gatename);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {

                txtharga.setText(response.body().getHarga().toString());


            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
//                Toast.makeText(MainMenu.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setdataprovider(final String nohpprov) {
        AccountAPI accountAPI = accountClient.getAccountAPI();
        Call<Account> call = accountAPI.getproviderdetil(nohpprov);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                txtsaldoprov.setText(response.body().getSaldoprov().toString());
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
//                Toast.makeText(MainMenu.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void initialbutton()
    {
//        btnBAYAR.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                jumlah = txtharga.getText().toString();
//                final Integer jumlah_saldo = Integer.valueOf(jumlah);
//                final String accountNoHp = txtnohp.getText().toString();
//                final String lokasi = txtgate.getText().toString();
//                if (txtgate.getText().equals("none")) {
//                    Toast.makeText(getApplicationContext(), "diluar jangkauan gate", Toast.LENGTH_SHORT).show();
//                }else{
//                    if (Integer.valueOf(txtsaldopay.getText().toString()) - Integer.valueOf(jumlah_saldo) < 0) {
//                        if (Integer.valueOf(txtsaldoprov.getText().toString()) - Integer.valueOf(jumlah_saldo) > 0) {
//                            Toast.makeText(getApplicationContext(), "Saldo Payment Tidak Mencukupi", Toast.LENGTH_SHORT).show();
//                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                            builder.setTitle("Pay with Pulsa");
//                            builder.setMessage("apakah anda ingin membayar dengan pulsa?");
//                            builder.setIcon(R.drawable.topup);
//                            // Membuat tombol negativ
//                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            });
//                            //Membuat tombol positif
//                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // Bila pilih ok, maka muncul toast
//                                    accountClient.topup(accountNoHp, jumlah_saldo, lokasi);
//                                    new Handler().postDelayed(new Runnable() {
//
//
//                                        @Override
//                                        public void run() {
//                                            // TODO Auto-generated method stub
//                                            setDataMember(username);
//
//
//                                            //jeda selesai Splashscreen
//                                            this.finish();
//                                        }
//
//                                        private void finish() {
//                                            // TODO Auto-generated method stub
//
//                                        }
//                                    }, 500);
//                                    Toast.makeText(getApplicationContext(), "Berhasil Melakukan Top Up Saldo", Toast.LENGTH_SHORT).show();
//
//                                }
//                            });
//                            builder.show();
//                        }else{
//                            Toast.makeText(getApplicationContext(), "Saldo Payment & Pulsa Tidak Mencukupi silahkan menuju gate lain", Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (Integer.valueOf(txtsaldopay.getText().toString()) - Integer.valueOf(jumlah_saldo) > 0) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                        builder.setTitle("Pembayaran Tol");
//                        builder.setMessage("apakah anda ingin melakukan pembayaran?");
//                        builder.setIcon(R.drawable.pay);
//                        // Membuat tombol negativ
//                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                        //Membuat tombol positif
//                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                                 Bila pilih ok, maka muncul toast
//                                accountClient.addSaldo(accountNoHp, jumlah_saldo, lokasi);
//                                new Handler().postDelayed(new Runnable() {
//
//
//                                    @Override
//                                    public void run() {
//                                        // TODO Auto-generated method stub
//                                        setDataMember(username);
//
//
//                                        //jeda selesai Splashscreen
//                                        this.finish();
//                                    }
//
//                                    private void finish() {
//                                        // TODO Auto-generated method stub
//
//                                    }
//                                }, 500);
////                setdataprovider(nohp);
//                                Toast.makeText(getApplicationContext(), "Berhasil Membayar Tol", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                        builder.show();
//                    }
//
//                }
//            }
//        });

//        btnTOPUP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Integer jumlah;
//                    final Integer jumlah_saldo = Integer.valueOf(edttopup.getSelectedItem().toString());
//                    final String accountNoHp = txtnohp.getText().toString();
//                    jumlah = Integer.valueOf(txtsaldoprov.getText().toString()) - Integer.valueOf(edttopup.getSelectedItem().toString());
//                if (Integer.valueOf(txtsaldoprov.getText().toString()) - Integer.valueOf(edttopup.getSelectedItem().toString()) < 0) {
//                    Toast.makeText(getApplicationContext(), "Saldo Pulsa Tidak Mencukupi", Toast.LENGTH_SHORT).show();
//                }else {
//
//                }
//            }
//        });


        btnHISTORY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Histroypayment.class);
                intent.putExtra("nomorhp", txtnohp.getText().toString());
                startActivity(intent);
            }
        });

        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Logout");
                builder.setMessage("apakah anda ingin Log out?");
                builder.setIcon(R.drawable.logout);
                // Membuat tombol negativ
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //Membuat tombol positif
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//
                        finish();
                        Intent intent = new Intent(MainMenu.this, Login.class);
                        startActivity(intent);
//                setdataprovider(nohp);

                    }
                });
                builder.show();

            }
        });

        lay_nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        lay_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        lay_noktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        lay_nohp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
            }
        });
    }

    private void initiallayout()
    {
        lay_nama = (LinearLayout) findViewById(R.id.lay_nama);
        lay_email = (LinearLayout) findViewById(R.id.lay_email);
        lay_nohp = (LinearLayout) findViewById(R.id.lay_nohp);
        lay_noktp = (LinearLayout) findViewById(R.id.lay_noktp);
        btnrefresh = (Button) findViewById(R.id.btn_refresh);
        btnHISTORY = (Button) findViewById(R.id.btn_history);
        txtnama = (TextView) findViewById(R.id.txt_nama);
        txtnoktp = (TextView) findViewById(R.id.txt_noktp);
        txtemail = (TextView) findViewById(R.id.txt_email);
        txtsaldopay = (TextView) findViewById(R.id.txt_saldopayment);
        txtsaldoprov = (TextView) findViewById(R.id.txt_saldoprovider);
        txtnohp = (TextView) findViewById(R.id.txt_nohp);
        txtgate = (TextView) findViewById(R.id.txt_gate);
        txtharga = (TextView) findViewById(R.id.txt_hargatol);
    }

}
