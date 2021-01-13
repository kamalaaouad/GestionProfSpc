/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Toufiq
 */
public class Specialite {
    private int id;
    private String code;
    private String libelle;
    
    public Specialite(int id, String code, String libelle ){
        this.id=id;
        this.code=code;
        this.libelle=libelle;
    }
    public Specialite( String code, String libelle ){
        this.code=code;
        this.libelle=libelle;
    }
    public int getId(){
        return id;
    }
    public String getCode(){
        return code;
    }
    public String getLibelle(){
        return libelle;
    }
    public String toString(){
        return id +", "+ code +", "+libelle;
    }
}
