/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connexion.Connexion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import beans.User;

/**
 *
 * @author Toufiq
 */
public class UserService {
    public boolean Create(User o){
        String sql="insert into user values(null,?,?,?,?)";
            PreparedStatement ps=null;
        try {
            ps=Connexion.getInstance().getCn().prepareStatement(sql);
            ps.setString(1, o.getUsername());
            ps.setString(2, o.getEmail());
            ps.setString(3, hashPassword(o.getPassword()));
            ps.setString(4, o.getTele());
            if(ps.executeUpdate()==1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public List<User> FindAll(){
        String sql="select * from user";
            PreparedStatement ps=null;
            ResultSet rs=null;
            List<User> users = new ArrayList<>();
        try {
            ps=Connexion.getInstance().getCn().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
                users.add(new User(rs.getInt("id"),rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("tele")));
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return users;
    }
    
    public User isVerifiedToConnect(String username,String password){
        String sql="select * from user where username = ? and password =?";
            PreparedStatement ps=null;
            ResultSet rs=null;
            User user = null;
        try {
            ps=Connexion.getInstance().getCn().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2,hashPassword(password));
            rs=ps.executeQuery();
            while(rs.next())
                user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("tele"));
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return user;
    }
    private String hashPassword(String password) {
        try {
				MessageDigest md = MessageDigest.getInstance("Md5");
				md.update(password.getBytes());
				byte bytaData[] = md.digest();
				/*StringBuffer sb = new StringBuffer();
				for(int i=0;i < bytaData.length;i++) {
					sb.append(Integer.toString((bytaData[i] & 0xff) + 0x100, 16).substring(1));
				}*/
				//System.out.println("En format exact "+sb.toString());
				
				StringBuffer hexString = new StringBuffer();
				for(int i=0; i<bytaData.length; i++) {
					String hex= Integer.toHexString(0xff & bytaData[i]);
					if(hex.length() == 1) hexString.append('0');
					 hexString.append(hex);
				}
                                return hexString.toString();
				//System.out.println("En format hex :"+hexString.toString());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       // return null;
        return null;
    }

    
}
