package com.example.umarramadhana.atp.pojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountAPI {
    @POST("/atp/user/tambahuser.php")
    @FormUrlEncoded
    Call<Account> addAccount(@Field("name") String name, @Field("email") String email, @Field("noktp") String noktp,
                             @Field("address") String address, @Field("nohp") String nohp, @Field("username") String username,
                             @Field("password") String password);

    @POST("/atp/user/tambahsaldo.php")
    @FormUrlEncoded
    Call<Account> addSaldo(@Field("nohp") String nohp, @Field("jumlah") Integer jumlah, @Field("lokasi") String lokasi);

    @POST("/atp/user/topup.php")
    @FormUrlEncoded
    Call<Account> topup(@Field("nohp") String nohp, @Field("jumlah") Integer jumlah, @Field("lokasi") String lokasi);

    @POST("/atp/user/login.php")
    @FormUrlEncoded
    Call<Account> login(@Field("username") String username, @Field("password") String password);

    @POST("/atp/user/tampiluser.php")
    @FormUrlEncoded
    Call<Account> getuserdetil(@Field("username") String username);

    @POST("/atp/user/tampilprovider.php")
    @FormUrlEncoded
    Call<Account> getproviderdetil(@Field("nohpprov") String nohpprov);

    @GET("/atp/user/gettransaksi.php")
    Call<List<Account>> getTransaksi();

    @POST("/atp/user/tampiltrans.php")
    @FormUrlEncoded
    Call<Account> gettransdetil(@Field("tanggal") String tanggal);

    @POST("/atp/user/tampilgate.php")
    @FormUrlEncoded
    Call<Account> gethargagate(@Field("gatename") String gatename);
}
