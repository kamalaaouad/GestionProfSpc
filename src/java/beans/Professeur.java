/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import java.util.Date;
import services.ServiceSpc;

/**
 *
 * @author Toufiq
 */
public class Professeur {

    private int id;
    private String nom;
    private String prenom;
    private String tele;
    private String email;
    private Date dateemp;
    private Date newDate;
    private String sexe;
    private Specialite spc;
    private int id_spc;
    private ServiceSpc ss = new ServiceSpc();
    
    public Professeur(int id,String nom,String prenom,String tele,String email,Date date,String sexe,int id_spc){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.tele=tele;
        this.email=email;
        this.dateemp=date;
        this.sexe= sexe;
        this.id_spc = id_spc;
    }
    public Professeur(int id,String nom,String prenom,String tele,String email,Date date,String sexe,String libelle){
       
        this.nom=nom;
        this.prenom=prenom;
        this.tele=tele;
        this.email=email;
        this.dateemp=date;
        this.sexe= sexe;
       // this.id_spc = id_spc;
    }
    public Professeur(String nom,String prenom,String tele,String email,Date date,String sexe,int id_spc){
       
        this.nom=nom;
        this.prenom=prenom;
        this.tele=tele;
        this.email=email;
        this.dateemp=date;
        this.sexe= sexe;
        this.id_spc = id_spc;
    }
    public Professeur(int id,String nom,String prenom,String tele,String email,Date date,String sexe,Specialite spc){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.tele=tele;
        this.email=email;
        this.dateemp=date;
        this.sexe= sexe;
        this.spc = spc;
    }
    public Professeur(String nom,String prenom,String tele,String email,Date date,String sexe,Specialite spc){
        
        this.nom=nom;
        this.prenom=prenom;
        this.tele=tele;
        this.email=email;
        this.dateemp=date;
        this.sexe= sexe;
        this.spc=spc;
    }
    public int getId(){
        return id;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getTele(){
        return tele;
    }
    public String getEmail(){
        return email;
    }
    public Date getDate(){
        return dateemp;
    }
    public String getSexe(){
        return sexe;
    }
    public Specialite getSpecialite(){
        return spc;
    }
    public Specialite findSpecialite(){
        for(Specialite s:ss.findAll()){
            if(s.getId()==id_spc){
                return spc=s;
            }
        }
        return spc;
    }
    public String toString(){
        return this.id+" - "+this.nom+" - "+this.prenom+" - "+this.tele+" - "+this.email+" - "+this.dateemp+" - "+this.sexe+" - "+this.findSpecialite();
    }
}
