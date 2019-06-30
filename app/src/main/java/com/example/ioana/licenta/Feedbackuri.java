package com.example.ioana.licenta;

import android.widget.RatingBar;

import java.io.Serializable;

public class Feedbackuri implements Serializable {

    String titlu;
    String text;
    String rating;
    String categorie;
    String utilizator;

public Feedbackuri(String titlu,String categorie,String text,String rating,String utilizator){
   this.setTitlu(titlu);
    this.setText(text);
    this.setRating(rating);
    this.setCategorie(categorie);
    this.setUtilizator(utilizator);

}

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(String utilizator) {
        this.utilizator = utilizator;
    }


    @Override
    public String toString() {
/*
    if(this.categorie=="1")
    {  this.categorie="Desert";}
    else if(this.categorie=="2")
    { this.categorie="Fel principal";}
  else if(this.categorie=="3")
    {  this.categorie="Supe";}
*/
        return "Titlul retetei: "+this.titlu+"\n"+"\n"+"Comentariu: "+ this.text+"\n"+"\n"+"Rating: "+this.rating+"/5"+"\n"+"\n"+"Utilizator: "+this.utilizator ;
      // + "Categoria: "+this.categorie+"\n"+"\n"
}
}
