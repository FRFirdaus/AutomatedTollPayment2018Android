package com.example.umarramadhana.atp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Account {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("noktp")
    private String noktp;
    @SerializedName("address")
    private String address;
    @SerializedName("nohp")
    private String nohp;
    @SerializedName("nohpprov")
    private String nohpprov;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("saldo")
    private Integer saldo;
    @SerializedName("saldoprov")
    private Integer saldoprov;
    @SerializedName("harga")
    private Integer harga;
    @SerializedName("lokasi")
    private String lokasi;


    @SerializedName("nohptrans")
    private String nohptrans;
    @SerializedName("gatename")
    private String gatename;
    @SerializedName("tanggal")
    private String date;


    public Account() {
    }

    public Account(String nohpprov, Integer saldoprov) {
        super();
        this.nohpprov = nohpprov;
        this.saldoprov = saldoprov;
    }

    public Account(String name, String email, String noktp, String address, String nohp, String username, String password) {
        this.name = name;
        this.email = email;
        this.noktp = noktp;
        this.address = address;
        this.nohp = nohp;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getSaldoprov() {
        return saldoprov;
    }

    public void setSaldoprov(Integer saldoprov) {
        this.saldoprov = saldoprov;
    }

    public String getNohpprov() {
        return nohpprov;
    }

    public void setNohpprov(String nohpprov) {
        this.nohpprov = nohpprov;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGatename() {
        return gatename;
    }

    public void setGatename(String gatename) {
        this.gatename = gatename;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

}