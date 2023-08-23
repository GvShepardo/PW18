package com.pw18.pw18;
import java.io.Serializable;
public class User implements Serializable {
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String tel;
    private String type;

    // Costruttore vuoto


    public User() {
    }

    public User(String username, String nome, String cognome, String email, String tel, String type) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.tel = tel;
        this.type = type;
    }

    // Metodi getter e setter per le proprietà
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


