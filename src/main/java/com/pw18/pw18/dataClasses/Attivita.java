package com.pw18.pw18.dataClasses;

public class Attivita {

    private String username;
    private String nomeAttivita;

    public Attivita(String user, String attivita){
        username = user;
        nomeAttivita = attivita;
    }

    public String getUsername() { return username; }
    public String getNomeAttivita() { return nomeAttivita; }

}
