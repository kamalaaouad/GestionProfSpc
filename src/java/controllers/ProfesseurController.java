/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.ChartDto;
import beans.Professeur;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ServiceProf;
import services.ServiceSpc;

/**
 *
 * @author Toufiq
 */
public class ProfesseurController extends HttpServlet {
    
    ServiceProf serviceProfessor = new ServiceProf();
    ServiceSpc serviceSpc = new ServiceSpc();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if ("add".equals(request.getParameter("action"))) {
            System.out.println("voila la date" + request.getParameter("date"));
            serviceProfessor.craete(new Professeur(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("tele"), request.getParameter("email"), new Date(request.getParameter("date").replace("-", "/")), request.getParameter("sexe"), Integer.parseInt(request.getParameter("spc"))));
            response.setContentType("application/json");
            List<Professeur> professeurs = new ArrayList<>();
            for (Professeur p : serviceProfessor.findAll()) {
                professeurs.add(new Professeur(p.getId(), p.getNom(), p.getPrenom(), p.getTele(), p.getEmail(), p.getDate(), p.getSexe(), p.findSpecialite()));
                
            }
            //for(Professeur p:professeurs)
            //  System.out.println(p.GetSpecialite());
            
            Gson json = new Gson();
            response.getWriter().write(json.toJson(professeurs));
            //findall(response);
        } else if ("update".equals(request.getParameter("action"))) {
            System.out.println("voila la date" + request.getParameter("date"));
            serviceProfessor.update(new Professeur(Integer.parseInt(request.getParameter("id")), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("tele"), request.getParameter("email"), new Date(request.getParameter("date").replace("-", "/")), request.getParameter("sexe"), Integer.parseInt(request.getParameter("spc"))));
            response.setContentType("application/json");
            List<Professeur> professeurs = new ArrayList<>();
            for (Professeur p : serviceProfessor.findAll()) {
                professeurs.add(new Professeur(p.getId(), p.getNom(), p.getPrenom(), p.getTele(), p.getEmail(), p.getDate(), p.getSexe(), p.findSpecialite()));
                
            }
            //for(Professeur p:professeurs)
            //  System.out.println(p.GetSpecialite());
            
            Gson json = new Gson();
            response.getWriter().write(json.toJson(professeurs));
            //findall(response);
        } else if ("delete".equals(request.getParameter("action"))) {
            serviceProfessor.Delete(serviceProfessor.findById(Integer.parseInt(request.getParameter("id"))));
            response.setContentType("application/json");
            List<Professeur> professeurs = serviceProfessor.findAll();
            Gson json = new Gson();
            response.getWriter().write(json.toJson(professeurs));
        } else if ("edit".equals(request.getParameter("action"))) {
            response.setContentType("application/json");
            Professeur professeur = serviceProfessor.findById(Integer.parseInt(request.getParameter("id")));
            //Gson json = new Gson();
            Gson json = new GsonBuilder().setDateFormat(DateFormat.SHORT, DateFormat.SHORT).create();
            System.out.println("prof" + professeur.getDate());
            response.getWriter().write(json.toJson(professeur));
        } else if ("search".equals(request.getParameter("action"))) {
            
            response.setContentType("application/json");
            List<Professeur> professeurs = new ArrayList<>();
            for (Professeur p : serviceProfessor.findProfessorBySpc(serviceSpc.findById(Integer.parseInt(request.getParameter("id"))))) {
                professeurs.add(new Professeur(p.getId(), p.getNom(), p.getPrenom(), p.getTele(), p.getEmail(), p.getDate(), p.getSexe(), p.findSpecialite()));
                
            }
            //for(Professeur p:professeurs)
            //  System.out.println(p.toString());
            
            Gson json = new Gson();
            response.getWriter().write(json.toJson(professeurs));
        } else if ("searchdate".equals(request.getParameter("action"))) {
            
            response.setContentType("application/json");
            List<Professeur> professeurs = new ArrayList<>();
            for (Professeur p : serviceProfessor.findBetweenDate(new Date(request.getParameter("date1").replace("-", "/")), new Date(request.getParameter("date2").replace("-", "/")))) {
                professeurs.add(new Professeur(p.getId(), p.getNom(), p.getPrenom(), p.getTele(), p.getEmail(), p.getDate(), p.getSexe(), p.findSpecialite()));
                
            }
            for (Professeur p : professeurs) {
                System.out.println(p.toString());
            }
            
            Gson json = new Gson();
            response.getWriter().write(json.toJson(professeurs));
        } else if ("listspc".equals(request.getParameter("action"))) {
            response.setContentType("application/json");
            //Map<String,Integer> dataTheChart = serviceProfessor.chartBySpc();
            List<ChartDto> dataTheChart = serviceProfessor.chartBySpc();
            /* Iterator listiterkey = dataTheChart.keySet().iterator();
             List<String> keyhash = new LinkedList<>();
             for(Map.Entry entrykeychart : dataTheChart.entrySet()){
             keyhash.add((String) entrykeychart.getKey());
             }*/
            Gson json = new Gson();
            response.getWriter().write(json.toJson(dataTheChart));
        } else {
            // findall(response);
            response.setContentType("application/json");
            List<Professeur> professeurs = new ArrayList<>();
            for (Professeur p : serviceProfessor.findAll()) {
                professeurs.add(new Professeur(p.getId(), p.getNom(), p.getPrenom(), p.getTele(), p.getEmail(), p.getDate(), p.getSexe(), p.findSpecialite()));
                
            }
            for (Professeur p : professeurs) {
                System.out.println(p.findSpecialite());
            }
            
            Gson json = new Gson();
            response.getWriter().write(json.toJson(professeurs));
            
        }
    }
    
    private void findall(HttpServletResponse httpResponse) throws IOException {
        httpResponse.setContentType("application/json");
        List<Professeur> professeurs = serviceProfessor.findAll();
        Gson json = new Gson();
        httpResponse.getWriter().write(json.toJson(professeurs));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
