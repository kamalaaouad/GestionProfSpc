/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Specialite;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ServiceSpc;

/**
 *
 * @author Toufiq
 */
public class SpcController extends HttpServlet {

    ServiceSpc ss = new ServiceSpc();

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
        if (request.getParameter("action").equals("add")) {
            ss.craete(new Specialite(request.getParameter("code"), request.getParameter("libelle")));
            response.setContentType("application/json");
            List<Specialite> specialites = ss.findAll();
            Gson json = new Gson();
            response.getWriter().write(json.toJson(specialites));
        } else if (request.getParameter("action").equals("delete")) {
            ss.Delete(ss.findById(Integer.parseInt(request.getParameter("id"))));
            response.setContentType("application/json");
            List<Specialite> specialites = ss.findAll();
            Gson json = new Gson();
            response.getWriter().write(json.toJson(specialites));
        } else if (request.getParameter("action").equals("update")) {
            response.setContentType("application/json");
            Specialite spc = null;
            spc = ss.findById(Integer.parseInt(request.getParameter("id")));
            Gson json = new Gson();
            response.getWriter().write(json.toJson(spc));
        } else if (request.getParameter("action").equals("edit")) {
            response.setContentType("application/json");
            //Specialite spc=null;
            ss.update(new Specialite(Integer.parseInt(request.getParameter("id")), request.getParameter("code"), request.getParameter("libelle")));
            response.setContentType("application/json");
            List<Specialite> specialites = ss.findAll();
            Gson json = new Gson();
            response.getWriter().write(json.toJson(specialites));
            //Gson json = new Gson();
            //response.getWriter().write(json.toJson(spc));
        } else {
            response.setContentType("application/json");
            List<Specialite> specialites = ss.findAll();
            Gson json = new Gson();
            response.getWriter().write(json.toJson(specialites));
        }

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
