package com.pw18.pw18.dataClasses;

public class Visita {
    private String pagina;
    private int visite;

    public Visita(String pag, int vis){
        pagina = pag;
        visite = vis;
    }

    public String getPagina() {return pagina;}

    public int getVisite() {return visite;}
}
