/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.ChartDto;
import beans.Professeur;
import beans.Specialite;
import connexion.Connexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ma.projet.dao.IDao;

/**
 *
 * @author Toufiq
 */
public class ServiceProf implements IDao<Professeur> {

    @Override
    public boolean craete(Professeur o) {
        String sql = "insert into professeur values(null,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = Connexion.getInstance().getCn().prepareStatement(sql);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getTele());
            ps.setString(4, o.getEmail());
            ps.setTimestamp(5, new Timestamp(o.getDate().getTime()));
            ps.setString(6, o.getSexe());
            ps.setInt(7, o.findSpecialite().getId());
            System.out.println(o.getDate().getTime());
            System.out.println(new Timestamp(o.getDate().getTime()));
            ps.setTimestamp(8, new Timestamp(o.getDate().getTime()));
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("create error" + o.getSpecialite().getId());
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Professeur o) {
        String sql = "update professeur set nom=  ?,prenom = ? , tele = ?, email = ?, dateEmb = ?,sexe=?,id_specialite=? where id=?";
        PreparedStatement ps = null;
        try {
            ps = Connexion.getInstance().getCn().prepareStatement(sql);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getTele());
            ps.setString(4, o.getEmail());
            ps.setTimestamp(5, new Timestamp(o.getDate().getTime()));
            ps.setString(6, o.getSexe());
            ps.setInt(7, o.findSpecialite().getId());
            ps.setInt(8, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Delete(Professeur o) {
        String sql = "Delete from professeur where id=?";
        PreparedStatement ps = null;
        try {
            ps = Connexion.getInstance().getCn().prepareStatement(sql);
            ps.setInt(1, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("errors delete");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Professeur findById(int i) {
        String sql = "select * from professeur where id=?";
        PreparedStatement ps = null;
        Professeur machine = null;
        ResultSet rs = null;
        try {
            ps = Connexion.getInstance().getCn().prepareStatement(sql);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            if (rs.next()) {
                machine = new Professeur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tele"), rs.getString("email"), new Date(rs.getTimestamp("dateEmb").getTime()), rs.getString("sexe"), rs.getInt("id_specialite"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return machine;
    }

    @Override
    public List<Professeur> findAll() {
        String sql = "select * from professeur";
        PreparedStatement ps = null;
        List<Professeur> profeeseurs = new ArrayList<>();
        ResultSet rs = null;
        try {
            ps = Connexion.getInstance().getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                profeeseurs.add(new Professeur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tele"), rs.getString("email"), new Date(rs.getTimestamp("dateEmb").getTime()), rs.getString("sexe"), rs.getInt("id_specialite")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return profeeseurs;
    }

    public List<Professeur> findAllJoint() {
        String sql = "SELECT p.id,p.nom,p.prenom,p.tele,p.email,p.dateEmb,p.sexe,s.libelle FROM professeur p JOIN specialite s ON p.id_specialite=s.id";
        PreparedStatement ps = null;
        List<Professeur> profeeseurs = new ArrayList<>();
        ResultSet rs = null;
        try {
            ps = Connexion.getInstance().getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                profeeseurs.add(new Professeur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tele"), rs.getString("email"), rs.getDate("dateEmb"), rs.getString("sexe"), rs.getString("libelle")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return profeeseurs;
    }
    
    public List<Professeur> findProfessorBySpc(Specialite s){
		String sql="select * from professeur where id_specialite=?";
		PreparedStatement ps=null;
		List<Professeur> professeurs= new ArrayList<>();
		ResultSet rs=null;
		try {
			ps=Connexion.getInstance().getCn().prepareStatement(sql);
			ps.setInt(1, s.getId());
			rs=ps.executeQuery();
			while(rs.next())
			professeurs.add(new Professeur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tele"), rs.getString("email"),new Date(rs.getTimestamp("dateEmb").getTime()), rs.getString("sexe"), rs.getInt("id_specialite")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return professeurs;
		
	}
    
    public List<Professeur> findBetweenDate (java.util.Date d1, java.util.Date d2) {
		String sql="select * from professeur where dateEmb > ? and dateEmb < ?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Professeur> professeurs= new LinkedList<>();
		try {
			ps=Connexion.getInstance().getCn().prepareStatement(sql);
                        ps.setTimestamp(1, new Timestamp(d1.getTime()));
                        ps.setTimestamp(2, new Timestamp(d2.getTime()));
			rs=ps.executeQuery();
			while(rs.next())
				professeurs.add(new Professeur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tele"), rs.getString("email"),new Date(rs.getTimestamp("dateEmb").getTime()), rs.getString("sexe"), rs.getInt("id_specialite")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return professeurs;
	}
    
    public List<ChartDto> chartBySpc(){
        String sql="SELECT COUNT(professeur.id),specialite.libelle FROM professeur,specialite WHERE professeur.id_specialite=specialite.id GROUP BY id_specialite";
		PreparedStatement ps=null;
		ResultSet rs=null;
		      Map<String,Integer> professeurs= new HashMap<>();
                      List<ChartDto> charts = new ArrayList<>();
		try {
			ps=Connexion.getInstance().getCn().prepareStatement(sql);
			rs=ps.executeQuery();
                        
                        while(rs.next()) {
				// professeurs.put(rs.getString("specialite.libelle"),rs.getInt("COUNT(professeur.id)"));
                               charts.add(new ChartDto(rs.getString("specialite.libelle"), rs.getInt("COUNT(professeur.id)")));
                        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return charts;
    }
    
}
