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
public class User {
    private int id;
    private String username;
    //private String prenom;
    private String email;
    private String Password;
    private String tele;
    public User(String username,String email, String password, String tele){
        this.username=username;
        this.email=email;
        this.Password=password;
        this.tele=tele;
    }
    public User(int id,String username,String email, String password, String tele){
        this.id=id;
        this.username=username;
        this.email=email;
        this.Password=password;
        this.tele=tele;
    }
    public int getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.Password;
    }
    public String getTele(){
        return this.tele;
    }
    public String tostring(){
        return this.id+" "+email;
    }
}
