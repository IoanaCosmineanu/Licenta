package com.example.ioana.licenta;

import java.io.Serializable;

public class Retete implements Serializable {

    private String numereteta,nr,elemente,instructiuni;


    public Retete(String numereteta,String nr,String elemente,String instructiuni)

    {
     this.setNumereteta(numereteta);
     this.setNr(nr);
     this.setElemente(elemente);
     this.setInstructiuni(instructiuni);
    }




    public String getNumereteta() {

        return numereteta;
    }

    public void setNumereteta(String numereteta) {

        this.numereteta = numereteta;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getElemente() {
        return elemente;
    }

    public void setElemente(String elemente) {
        this.elemente = elemente;
    }

    public String getInstructiuni() {
        return instructiuni;
    }

    public void setInstructiuni(String instructiuni) {
        this.instructiuni = instructiuni;
    }

    @Override
    public String toString() {
        return "Titlu: "+this.numereteta+"\n"+"Numar ingrediente: "+this.nr+"\n"+"Ingrediente: "+ this.elemente ;
    }
}

