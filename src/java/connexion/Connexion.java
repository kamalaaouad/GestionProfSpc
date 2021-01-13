/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Toufiq
 */
public class Connexion {
    private String url="jdbc:mysql://localhost/appartement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String uid="root";
    private String password="";
    private static Connexion instance=null;
    private Connection cn=null;
    private Connexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
           cn = (Connection) DriverManager.getConnection(url, uid, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("driver not fount");
        } catch (SQLException ex) {
            System.out.println("connection not establish");
        }
    }
    public static Connexion getInstance(){
            if(instance == null)
                return instance = new Connexion();
            return instance;
        }
    public Connection getCn(){
        return cn;
    }
}
