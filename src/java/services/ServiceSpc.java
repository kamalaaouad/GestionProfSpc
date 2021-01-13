/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.Specialite;
import connexion.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ma.projet.dao.IDao;

/**
 *
 * @author Toufiq
 */
public class ServiceSpc implements IDao<Specialite>{

    @Override
    public boolean craete(Specialite o) {
       String sql="insert into specialite values(null,?,?)";
		PreparedStatement ps=null;
		try {
			ps=Connexion.getInstance().getCn().prepareStatement(sql);
			ps.setString(1, o.getCode());
			ps.setString(2, o.getLibelle());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
    }

    @Override
    public boolean update(Specialite o) {
        String sql="update specialite set code=? , libelle=? where id=?";
		PreparedStatement ps=null;
		try {
			ps=Connexion.getInstance().getCn().prepareStatement(sql);
			ps.setString(1,o.getCode());
			ps.setString(2, o.getLibelle());
			ps.setInt(3, o.getId());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }

    @Override
    public boolean Delete(Specialite o) {
       String sql="Delete from specialite where id=?";
		PreparedStatement ps=null;
		try {
			ps=Connexion.getInstance().getCn().prepareStatement(sql);
			ps.setInt(1, o.getId());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			System.out.println("errors delete");
			e.printStackTrace();
		}
		return false;
    }

    @Override
    public Specialite findById(int i) {
       String sql="select * from specialite where id=?";
		PreparedStatement ps=null;
		Specialite specialit=null;
		ResultSet rs=null;
		try {
			ps=Connexion.getInstance().getCn().prepareStatement(sql);
			ps.setInt(1, i);
			rs=ps.executeQuery();
			if(rs.next())
			specialit=new Specialite(rs.getInt("id"),rs.getString("code"),rs.getString("libelle"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return specialit;
    }

    @Override
    public List<Specialite> findAll() {
       String sql="select * from specialite";
		PreparedStatement ps=null;
		List<Specialite> specialites=new ArrayList<>();;
		ResultSet rs=null;
		try {
			ps=Connexion.getInstance().getCn().prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
				specialites.add(new Specialite(rs.getInt("id"),rs.getString("code"),rs.getString("libelle")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return specialites;
    }
    
}
